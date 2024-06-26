package com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ModelProductResponse {

    private Long id;
    private String name;
}
