package com.proyectoInventario_Kodigo.proyecto_Inventario.mapper;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.SaleRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.SaleResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {StoreMapper.class, SaleDetailMapper.class})
public interface SaleMapper {

    SaleResponse toResponse(Sale sale);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "details", ignore = true)
    Sale toEntity(SaleRequest dto);
}
