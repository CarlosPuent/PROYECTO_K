package com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiveRequest {

    @NotEmpty(message = "The details cannot be empty or null")
    private Set<ReceiveDetailRequest> details;
}
