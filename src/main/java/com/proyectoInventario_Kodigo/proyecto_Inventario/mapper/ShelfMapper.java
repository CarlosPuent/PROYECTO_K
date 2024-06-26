package com.proyectoInventario_Kodigo.proyecto_Inventario.mapper;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ShelfRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ShelfResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Shelf;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ShelfMapper {

    ShelfResponse toShelfResponse(Shelf shelf);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "aisles", ignore = true)
    Shelf toShelf(ShelfRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "aisles", ignore = true)
    void updateShelfFromRequest(ShelfRequest request, @MappingTarget Shelf shelf);
}
