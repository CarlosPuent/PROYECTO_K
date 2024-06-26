package com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReceiveDetailResponse {

    private Long id;
    private ProductResponse product;
    private int quantity;
}
