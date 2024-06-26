package com.proyectoInventario_Kodigo.proyecto_Inventario.mapper;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.AisleRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ShelfRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.StoreRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.StoreResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Aisle;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Shelf;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StoreMapper {

    StoreResponse toStoreResponse(Store store);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "aisles", ignore = true)
    @Mapping(target = "code", ignore = true)
    Store toStore(StoreRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stores", ignore = true)
    Aisle toAisle(AisleRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "aisles", ignore = true)
    Shelf toShelf(ShelfRequest request);
}

