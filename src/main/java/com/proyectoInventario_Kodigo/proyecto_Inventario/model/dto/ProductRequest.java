package com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto;

import com.proyectoInventario_Kodigo.proyecto_Inventario.utils.ProductStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

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

}