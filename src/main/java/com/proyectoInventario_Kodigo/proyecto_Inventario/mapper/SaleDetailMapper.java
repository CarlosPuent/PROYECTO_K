package com.proyectoInventario_Kodigo.proyecto_Inventario.mapper;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.SaleDetailRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.SaleDetailResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.SaleDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface SaleDetailMapper {

    @Mapping(target = "total", source = "total")
    SaleDetailResponse toResponse(SaleDetail detail);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sale", ignore = true)
    SaleDetail toEntity(SaleDetailRequest dto);
}
