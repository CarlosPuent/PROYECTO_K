package com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.SupplierResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.SupplierRequest;

import java.util.List;

public interface SupplierService {

    SupplierResponse findById(Long id);
    SupplierResponse findByCode(String code);
    SupplierResponse findByName(String name);

    // CRUD
    SupplierResponse save(SupplierRequest request);
    List<SupplierResponse> findAll();
    SupplierResponse update(String code, SupplierRequest request);
    void deleteByCode(String code);

}
