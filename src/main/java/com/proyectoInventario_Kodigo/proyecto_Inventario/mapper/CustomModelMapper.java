package com.proyectoInventario_Kodigo.proyecto_Inventario.mapper;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ModelProductRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.ModelProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomModelMapper {
    @Mapping(target = "code", ignore = true)
    ModelProduct toEntity(ModelProductRequest dto);

    ModelProductRequest toDto(ModelProduct entity);
}

