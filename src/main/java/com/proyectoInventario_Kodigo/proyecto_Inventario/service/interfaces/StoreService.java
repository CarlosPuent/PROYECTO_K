package com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.StoreRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.StoreResponse;

import java.util.List;

public interface StoreService {

    StoreResponse findById(Long id);
    StoreResponse findByCode(String code);
    StoreResponse findByName(String name);

    // CRUD
    StoreResponse save(StoreRequest request);
    List<StoreResponse> findAll();
    StoreResponse update(String code, StoreRequest request);
    void deleteByCode(String code);

}
