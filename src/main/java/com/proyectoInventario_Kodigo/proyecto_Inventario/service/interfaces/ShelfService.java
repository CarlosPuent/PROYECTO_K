package com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ShelfRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ShelfResponse;
import java.util.List;

public interface ShelfService {

    List<ShelfResponse> findAll();
    ShelfResponse findByName(String name);
    ShelfResponse save(ShelfRequest request);
    ShelfResponse update(Long id, ShelfRequest request);
    void deleteById(Long id);
}
