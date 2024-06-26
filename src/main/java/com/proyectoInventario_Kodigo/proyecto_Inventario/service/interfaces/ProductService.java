package com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ProductRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ProductResponse;
import java.util.List;

public interface ProductService {

    ProductResponse findById(Long id);
    ProductResponse findByCode(String code);
    ProductResponse findByName(String name);

    List<ProductResponse> findAllByCategoryCode(String categoryCode);
    List<ProductResponse> findAllByBrandCode(String brandCode);
    ProductResponse findProductByModelCode(String modelCode);

    // CRUD
    ProductResponse save(ProductRequest request);
    List<ProductResponse> findAll();
    ProductResponse update(String code, ProductRequest request);
    void deleteByCode(String code);
}

