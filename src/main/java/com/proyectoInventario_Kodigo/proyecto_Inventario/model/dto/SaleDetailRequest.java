package com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleDetailRequest {

    @NotNull(message = "The field productId cannot be null")
    private Long productId;

    @NotNull(message = "The field quantity cannot be null")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private int quantity;

    @NotNull(message = "The field salePrice cannot be null")
    @DecimalMin("0.0")
    private BigDecimal salePrice;
}
