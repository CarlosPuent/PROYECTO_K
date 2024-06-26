package com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto;

import com.proyectoInventario_Kodigo.proyecto_Inventario.utils.MovementType;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleResponse {

    private Long id;
    private String code;
    private MovementType type;
    private StoreResponse store;
    private Set<SaleDetailResponse> details;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}