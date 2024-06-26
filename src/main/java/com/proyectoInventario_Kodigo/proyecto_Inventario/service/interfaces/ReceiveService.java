package com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ReceiveRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ReceiveResponse;

public interface ReceiveService {

    ReceiveResponse receiveProductsInStore(Long storeId, ReceiveRequest request);

    ReceiveResponse updateReceive(Long receiveId, Long storeId, ReceiveRequest request);

    void deleteReceive(Long receiveId);
}
