package com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.BrandRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.BrandResponse;
import java.util.List;

public interface BrandService {

    BrandResponse findById(Long id);
    BrandResponse findByCode(String code);
    BrandResponse findByName(String name);

    // CRUD
    BrandResponse saveBrand(BrandRequest request);
    List<BrandResponse> findAll();
    BrandResponse updateBrand(String code, BrandRequest request);
    void deleteByCode(String code);
}
