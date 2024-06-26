package com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto;

import com.proyectoInventario_Kodigo.proyecto_Inventario.utils.MovementType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleRequest {

    @NotNull(message = "The store id cannot be null")
    private Long storeId;

    @NotNull(message = "The field type cannot be null")
    private MovementType type;

    @NotEmpty(message = "The field details cannot be empty or null")
    private Set<SaleDetailRequest> details;

}
