package com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.CategoryRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.CategoryResponse;
import java.util.List;

public interface CategoryService {

    CategoryResponse findById(Long id);
    CategoryResponse findByCode(String code);
    CategoryResponse findByName(String name);

    // CRUD
    CategoryResponse save(CategoryRequest request);
    List<CategoryResponse> findAll();
    CategoryResponse update(String code, CategoryRequest request);
    void deleteByCode(String code);
}
