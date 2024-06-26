package com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.SaleRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.SaleResponse;

public interface SaleService {

    SaleResponse createSale(SaleRequest request);

    SaleResponse updateSale(Long saleId, SaleRequest request);

    void deleteSale(Long saleId);

}
