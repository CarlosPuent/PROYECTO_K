package com.proyectoInventario_Kodigo.proyecto_Inventario.mapper;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.CategoryRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.CategoryResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toCategoryResponse(Category category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    Category toCategory(CategoryRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    void updateCategoryFromRequest(CategoryRequest request, @MappingTarget Category category);
}


