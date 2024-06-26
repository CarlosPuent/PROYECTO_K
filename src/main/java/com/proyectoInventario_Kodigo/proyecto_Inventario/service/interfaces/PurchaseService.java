package com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.*;

public interface PurchaseService {

    PurchaseResponse createPurchase(PurchaseRequest request);

    PurchaseResponse updatePurchase(Long purchaseId, PurchaseRequest request);

    void deletePurchase(Long purchaseId);
}
