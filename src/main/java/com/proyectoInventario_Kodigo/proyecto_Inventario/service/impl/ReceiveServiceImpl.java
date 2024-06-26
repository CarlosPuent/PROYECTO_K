package com.proyectoInventario_Kodigo.proyecto_Inventario.service.impl;

import com.proyectoInventario_Kodigo.proyecto_Inventario.exceptions.*;
import com.proyectoInventario_Kodigo.proyecto_Inventario.mapper.ReceiveMapper;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ReceiveDetailRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ReceiveRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ReceiveResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.*;
import com.proyectoInventario_Kodigo.proyecto_Inventario.repository.*;
import com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces.ReceiveService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReceiveServiceImpl implements ReceiveService {

    private final ReceiveRepository receiveRepository;
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final AisleRepository aisleRepository;
    private final ShelfRepository shelfRepository;
    private final ProductStoreExistenceRepository productStoreExistenceRepository;
    private final ReceiveMapper receiveMapper;

    private static final Logger logger = LoggerFactory.getLogger(ReceiveServiceImpl.class);

    @Override
    @Transactional
    public ReceiveResponse receiveProductsInStore(Long storeId, ReceiveRequest request) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(StoreNotFoundException::new);

        Receive receive = new Receive();
        receive.setStore(store);
        receive.setCode(generateReceiveCode());

        Set<ReceiveDetail> details = request.getDetails().stream().map(detailRequest -> {
            Product product = productRepository.findById(detailRequest.getProductId())
                    .orElseThrow(ProductNotFoundException::new);

            Aisle aisle = aisleRepository.findById(detailRequest.getAisleId())
                    .orElseThrow(AisleNotFoundException::new);

            Shelf shelf = shelfRepository.findById(detailRequest.getShelfId())
                    .orElseThrow(ShelfNotFoundException::new);

            ProductStoreExistence productStoreExistence = new ProductStoreExistence();
            productStoreExistence.setProduct(product);
            productStoreExistence.setStore(store);
            productStoreExistence.setAisle(aisle);
            productStoreExistence.setShelf(shelf);
            productStoreExistence.setQuantity(detailRequest.getQuantity());

            productStoreExistenceRepository.save(productStoreExistence);

            ReceiveDetail receiveDetail = new ReceiveDetail();
            receiveDetail.setProduct(product);
            receiveDetail.setQuantity(detailRequest.getQuantity());
            receiveDetail.setReceive(receive);
            return receiveDetail;
        }).collect(Collectors.toSet());

        receive.setDetails(details);
        Receive savedReceive = receiveRepository.save(receive);

        return receiveMapper.toResponse(savedReceive);
    }

    @Override
    @Transactional
    public ReceiveResponse updateReceive(Long receiveId, Long storeId, ReceiveRequest request) {
        Receive receive = receiveRepository.findById(receiveId)
                .orElseThrow(ReceiveNotFoundException::new);

        Store store = storeRepository.findById(storeId)
                .orElseThrow(StoreNotFoundException::new);

        receive.setStore(store);

        Set<ReceiveDetail> details = new HashSet<>();

        for (ReceiveDetailRequest detailRequest : request.getDetails()) {
            Product product = productRepository.findById(detailRequest.getProductId())
                    .orElseThrow(ProductNotFoundException::new);

            Aisle aisle = aisleRepository.findById(detailRequest.getAisleId())
                    .orElseThrow(AisleNotFoundException::new);

            Shelf shelf = shelfRepository.findById(detailRequest.getShelfId())
                    .orElseThrow(ShelfNotFoundException::new);

            // Eliminar existencias antiguas
            productStoreExistenceRepository.deleteByProductAndStoreAndAisleAndShelf(product, store, aisle, shelf);

            // Crear nueva existencia
            ProductStoreExistence productStoreExistence = new ProductStoreExistence();
            productStoreExistence.setProduct(product);
            productStoreExistence.setStore(store);
            productStoreExistence.setAisle(aisle);
            productStoreExistence.setShelf(shelf);
            productStoreExistence.setQuantity(detailRequest.getQuantity());

            productStoreExistenceRepository.save(productStoreExistence);

            ReceiveDetail receiveDetail = new ReceiveDetail();
            receiveDetail.setProduct(product);
            receiveDetail.setQuantity(detailRequest.getQuantity());
            receiveDetail.setReceive(receive);
            details.add(receiveDetail);
        }

        receive.getDetails().clear();
        receive.getDetails().addAll(details);

        Receive updatedReceive = receiveRepository.save(receive);

        return receiveMapper.toResponse(updatedReceive);
    }

    @Override
    @Transactional
    public void deleteReceive(Long receiveId) {
        Receive receive = receiveRepository.findById(receiveId)
                .orElseThrow(ReceiveNotFoundException::new);

        for (ReceiveDetail detail : receive.getDetails()) {
            Product product = detail.getProduct();
            List<ProductStoreExistence> existences = productStoreExistenceRepository.findByProduct(product);

            for (ProductStoreExistence existence : existences) {
                Store store = existence.getStore();
                Aisle aisle = existence.getAisle();
                Shelf shelf = existence.getShelf();

                productStoreExistenceRepository.deleteByProductAndStoreAndAisleAndShelf(product, store, aisle, shelf);
            }
        }

        receiveRepository.delete(receive);
    }

    private String generateReceiveCode() {
        String prefix = "REC";
        String suffix = String.format("%04d", receiveRepository.count() + 1);
        return prefix + suffix;
    }
}

