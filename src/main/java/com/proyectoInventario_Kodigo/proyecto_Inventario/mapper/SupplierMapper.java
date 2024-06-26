package com.proyectoInventario_Kodigo.proyecto_Inventario.mapper;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.SupplierRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.SupplierResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierResponse toSupplierResponse(Supplier supplier);

    @Mapping(target = "id", ignore = true)
    Supplier toEntity(SupplierRequest request);
}

