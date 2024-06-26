package com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto;

import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleDetailResponse {

    private Long id;
    private ProductResponse product;
    private int quantity;
    private BigDecimal salePrice;
    private BigDecimal total;
}
