package com.proyectoInventario_Kodigo.proyecto_Inventario.mapper;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.PurchaseDetailRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.PurchaseRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.PurchaseResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Purchase;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.PurchaseDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {

    PurchaseResponse toResponse(Purchase purchase);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "details", ignore = true)
    Purchase toEntity(PurchaseRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    PurchaseDetail toDetailEntity(PurchaseDetailRequest detailRequest);

}
