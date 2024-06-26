package com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiveDetailRequest {

    @NotNull(message = "The field productId cannot be null")
    private Long productId;

    @NotNull(message = "The field aisleId cannot be null")
    private Long aisleId;

    @NotNull(message = "The field shelfId cannot be null")
    private Long shelfId;

    @NotNull(message = "The field quantity cannot be null")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private int quantity;
}
