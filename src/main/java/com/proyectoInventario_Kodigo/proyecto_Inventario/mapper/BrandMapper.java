package com.proyectoInventario_Kodigo.proyecto_Inventario.mapper;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.BrandRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.BrandResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    BrandResponse toBrandResponse(Brand brand);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    Brand toBrand(BrandRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    void updateBrandFromRequest(BrandRequest request, @MappingTarget Brand brand);
}



