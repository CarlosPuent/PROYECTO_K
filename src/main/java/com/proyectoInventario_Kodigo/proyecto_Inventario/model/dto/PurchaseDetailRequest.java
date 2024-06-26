package com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto;

import com.proyectoInventario_Kodigo.proyecto_Inventario.utils.ProductStatus;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDetailRequest {

    private Long productId;

    @NotBlank(message = "The field name cannot be empty")
    private String name;

    private String description;

    @NotNull(message = "The field price cannot be null")
    @DecimalMin("0.0")
    private BigDecimal price;

    @NotNull(message = "The field status cannot be null")
    private ProductStatus status;

    @NotEmpty(message = "The field categoryIds cannot be empty or null")
    private Set<Long> categoryIds;

    @NotEmpty(message = "The field brandIds cannot be empty or null")
    private Set<Long> brandIds;

    @NotEmpty(message = "The field models cannot be empty or null")
    private Set<ModelProductRequest> models;

    @NotNull(message = "The field quantity cannot be null")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private int quantity;
}
