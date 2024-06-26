package com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreResponse {

    private Long id;
    private String code;
    private String name;
    private String address;
    private String phone;
    private Set<AisleResponse> aisles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

