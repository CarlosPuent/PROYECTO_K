package com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
public class PurchaseResponse {

    private Long id;
    private String code;
    private SupplierResponse supplier;
    private Set<PurchaseDetailResponse> details;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
