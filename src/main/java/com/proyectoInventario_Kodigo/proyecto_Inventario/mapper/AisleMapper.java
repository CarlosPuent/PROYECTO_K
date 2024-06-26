package com.proyectoInventario_Kodigo.proyecto_Inventario.mapper;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.AisleRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.AisleResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Aisle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AisleMapper {

    AisleResponse toAisleResponse(Aisle aisle);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stores", ignore = true)
    Aisle toAisle(AisleRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stores", ignore = true)
    void updateAisleFromRequest(AisleRequest request, @MappingTarget Aisle aisle);
}
