package com.proyectoInventario_Kodigo.proyecto_Inventario.service.impl;

import com.proyectoInventario_Kodigo.proyecto_Inventario.exceptions.InsufficientStockException;
import com.proyectoInventario_Kodigo.proyecto_Inventario.exceptions.ProductNotFoundException;
import com.proyectoInventario_Kodigo.proyecto_Inventario.exceptions.SaleNotFoundException;
import com.proyectoInventario_Kodigo.proyecto_Inventario.exceptions.StoreNotFoundException;
import com.proyectoInventario_Kodigo.proyecto_Inventario.mapper.SaleMapper;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.SaleRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.SaleResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.*;
import com.proyectoInventario_Kodigo.proyecto_Inventario.repository.ProductRepository;
import com.proyectoInventario_Kodigo.proyecto_Inventario.repository.ProductStoreExistenceRepository;
import com.proyectoInventario_Kodigo.proyecto_Inventario.repository.SaleRepository;
import com.proyectoInventario_Kodigo.proyecto_Inventario.repository.StoreRepository;
import com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces.SaleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final ProductStoreExistenceRepository productStoreExistenceRepository;
    private final SaleMapper saleMapper;

    private static final Logger logger = LoggerFactory.getLogger(SaleServiceImpl.class);

    @Override
    @Transactional
    public SaleResponse createSale(SaleRequest request) {
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(StoreNotFoundException::new);

        Sale sale = new Sale();
        sale.setType(request.getType());
        sale.setStore(store);
        sale.setCode(generateSaleCode());

        Set<SaleDetail> details = request.getDetails().stream().map(detailRequest -> {
            Product product = productRepository.findById(detailRequest.getProductId())
                    .orElseThrow(ProductNotFoundException::new);

            ProductStoreExistence existence = productStoreExistenceRepository
                    .findByProductAndStore(product, store)
                    .orElseThrow(ProductNotFoundException::new);

            if (existence.getQuantity() < detailRequest.getQuantity()) {
                throw new InsufficientStockException();
            }

            existence.setQuantity(existence.getQuantity() - detailRequest.getQuantity());
            productStoreExistenceRepository.save(existence);

            SaleDetail detail = new SaleDetail();
            detail.setProduct(product);
            detail.setQuantity(detailRequest.getQuantity());
            detail.setSalePrice(detailRequest.getSalePrice());
            detail.setSale(sale);
            detail.setTotal(detail.getSalePrice().multiply(new BigDecimal(detail.getQuantity())));
            return detail;
        }).collect(Collectors.toSet());

        sale.setDetails(details);
        Sale savedSale = saleRepository.save(sale);

        return saleMapper.toResponse(savedSale);
    }

    @Override
    @Transactional
    public SaleResponse updateSale(Long saleId, SaleRequest request) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(SaleNotFoundException::new);

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(StoreNotFoundException::new);

        sale.setType(request.getType());
        sale.setStore(store);

        Set<SaleDetail> details = request.getDetails().stream().map(detailRequest -> {
            Product product = productRepository.findById(detailRequest.getProductId())
                    .orElseThrow(ProductNotFoundException::new);

            ProductStoreExistence existence = productStoreExistenceRepository
                    .findByProductAndStore(product, store)
                    .orElseThrow(ProductNotFoundException::new);

            if (existence.getQuantity() < detailRequest.getQuantity()) {
                throw new InsufficientStockException();
            }

            existence.setQuantity(existence.getQuantity() - detailRequest.getQuantity());
            productStoreExistenceRepository.save(existence);

            SaleDetail detail = new SaleDetail();
            detail.setProduct(product);
            detail.setQuantity(detailRequest.getQuantity());
            detail.setSalePrice(detailRequest.getSalePrice());
            detail.setSale(sale);
            detail.setTotal(detail.getSalePrice().multiply(new BigDecimal(detail.getQuantity())));
            return detail;
        }).collect(Collectors.toSet());

        sale.getDetails().clear();
        sale.getDetails().addAll(details);

        Sale updatedSale = saleRepository.save(sale);

        return saleMapper.toResponse(updatedSale);
    }

    @Override
    @Transactional
    public void deleteSale(Long saleId) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(SaleNotFoundException::new);

        for (SaleDetail detail : sale.getDetails()) {
            Product product = detail.getProduct();
            Store store = sale.getStore();
            ProductStoreExistence existence = productStoreExistenceRepository
                    .findByProductAndStore(product, store)
                    .orElseThrow(ProductNotFoundException::new);

            existence.setQuantity(existence.getQuantity() + detail.getQuantity());
            productStoreExistenceRepository.save(existence);
        }

        saleRepository.delete(sale);
    }

    private String generateSaleCode() {
        String prefix = "SAL";
        String suffix = String.format("%04d", saleRepository.count() + 1);
        return prefix + suffix;
    }
}
