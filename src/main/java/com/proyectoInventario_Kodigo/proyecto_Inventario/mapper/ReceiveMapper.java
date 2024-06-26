package com.proyectoInventario_Kodigo.proyecto_Inventario.mapper;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ReceiveRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ReceiveResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Receive;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReceiveMapper {

    ReceiveResponse toResponse(Receive receive);

    Receive toEntity(ReceiveRequest request);
}
