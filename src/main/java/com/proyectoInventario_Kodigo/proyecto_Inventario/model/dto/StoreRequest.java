package com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreRequest {

    @NotBlank(message = "The field name cannot be null or empty")
    private String name;

    @NotBlank(message = "The field address cannot be null or empty")
    private String address;

    @NotBlank(message = "The field phone cannot be null or empty")
    private String phone;

    @NotEmpty(message = "The aisles cannot be empty or null")
    private Set<AisleRequest> aisles;
}

