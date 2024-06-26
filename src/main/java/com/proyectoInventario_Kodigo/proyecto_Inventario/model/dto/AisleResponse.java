package com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto;

import lombok.Builder;
import lombok.Getter;
import java.util.Set;

@Getter
@Builder
public class AisleResponse {

    private Long id;
    private String name;
    private Set<ShelfResponse> shelves;
}

