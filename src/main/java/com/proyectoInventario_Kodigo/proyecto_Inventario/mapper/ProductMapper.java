package com.proyectoInventario_Kodigo.proyecto_Inventario.mapper;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ProductRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ProductResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Product;
import com.proyectoInventario_Kodigo.proyecto_Inventario.utils.ProductStatus;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, BrandMapper.class, CustomModelMapper.class})
public interface ProductMapper {

    @Mapping(target = "status", source = "status")
    ProductResponse toResponse(Product product);

    @Mapping(target = "status", source = "status")
    Product toEntity(ProductRequest dto);

    default String mapStatus(ProductStatus status) {
        return status != null ? status.name() : null;
    }

    default ProductStatus mapStatus(String status) {
        try {
            return status != null ? ProductStatus.valueOf(status) : null;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Estado desconocido: " + status);
        }
    }
}

