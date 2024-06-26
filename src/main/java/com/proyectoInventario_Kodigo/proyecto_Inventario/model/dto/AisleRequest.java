package com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AisleRequest {

    @NotBlank(message = "The field name cannot be null or empty")
    private String name;

    @NotEmpty(message = "The field shelves cannot be empty or null")
    private Set<ShelfRequest> shelves;
}
