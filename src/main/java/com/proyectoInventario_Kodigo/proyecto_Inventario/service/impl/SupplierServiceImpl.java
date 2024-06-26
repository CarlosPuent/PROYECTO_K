package com.proyectoInventario_Kodigo.proyecto_Inventario.service.impl;

import com.proyectoInventario_Kodigo.proyecto_Inventario.exceptions.*;
import com.proyectoInventario_Kodigo.proyecto_Inventario.mapper.SupplierMapper;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.SupplierRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.SupplierResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Supplier;
import com.proyectoInventario_Kodigo.proyecto_Inventario.repository.SupplierRepository;
import com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces.SupplierService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import static com.proyectoInventario_Kodigo.proyecto_Inventario.utils.ErrorCatalog.*;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Override
    public SupplierResponse findById(Long id) {
        return supplierRepository.findById(id)
                .map(supplierMapper::toSupplierResponse)
                .orElseThrow(SupplierNotFoundException::new);
    }

    @Override
    public SupplierResponse findByCode(String code) {
        return supplierRepository.findByCode(code)
                .map(supplierMapper::toSupplierResponse)
                .orElseThrow(SupplierNotFoundException::new);
    }

    @Override
    public SupplierResponse findByName(String name) {
        return supplierRepository.findByName(name)
                .map(supplierMapper::toSupplierResponse)
                .orElseThrow(SupplierNotFoundException::new);
    }

    @Override
    @Transactional
    public SupplierResponse save(SupplierRequest request) {
        if (supplierRepository.findByName(request.getName()).isPresent()) {
            throw new DuplicateSupplierNameException(DUPLICATE_SUPPLIER_NAME);
        }

        Supplier supplier = supplierMapper.toEntity(request);
        supplier.setCode(generateSupplierCode());

        Supplier savedSupplier = supplierRepository.save(supplier);
        return supplierMapper.toSupplierResponse(savedSupplier);
    }

    @Override
    @Transactional
    public SupplierResponse update(String code, SupplierRequest request) {
        return supplierRepository.findByCode(code)
                .map(supplier -> {

                    if (!supplier.getName().equals(request.getName())) {
                        supplierRepository.findByName(request.getName())
                                .ifPresent(existingSupplier -> {
                                    if (!existingSupplier.getCode().equals(request.getName())) {
                                        throw new DuplicateSupplierNameException(DUPLICATE_SUPPLIER_NAME);
                                    }
                                });
                    }

                    supplier.setName(request.getName());
                    supplier.setAddress(request.getAddress());
                    supplier.setPhone(request.getPhone());

                    return supplierRepository.save(supplier);
                })
                .map(supplierMapper::toSupplierResponse)
                .orElseThrow(SupplierNotFoundException::new);
    }

    @Override
    public List<SupplierResponse> findAll() {
        return supplierRepository.findAll()
                .stream()
                .map(supplierMapper::toSupplierResponse)
                .collect(Collectors.toList());
    }

    private String generateSupplierCode() {
        String prefix = "SUPL";
        String suffix = String.format("%04d", supplierRepository.count() + 1);
        return prefix + suffix;
    }

    @Override
    public void deleteByCode(String code) {
        if(supplierRepository.findByCode(code).isEmpty()){
            throw new SupplierNotFoundException();
        }
        supplierRepository.deleteByCode(code);
    }
}

