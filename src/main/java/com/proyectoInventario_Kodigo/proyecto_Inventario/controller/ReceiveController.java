package com.proyectoInventario_Kodigo.proyecto_Inventario.controller;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ReceiveRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ReceiveResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces.ReceiveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Receive Management", description = "APIs for managing product receipts in stores")
@RestController
@RequestMapping("/api/v1/receives")
@RequiredArgsConstructor
public class ReceiveController {

    private final ReceiveService receiveService;

    @Operation(summary = "Receive products in store", description = "Receives products in a specified store")
    @PostMapping("/store/{storeId}")
    public ResponseEntity<ReceiveResponse> receiveProductsInStore(@PathVariable Long storeId, @Valid @RequestBody ReceiveRequest request) {
        ReceiveResponse response = receiveService.receiveProductsInStore(storeId, request);
        return ResponseEntity.created(URI.create("/api/v1/receives/" + response.getId())).body(response);
    }

    @Operation(summary = "Update receive", description = "Updates an existing product receipt in a specified store")
    @PutMapping("/{receiveId}/store/{storeId}")
    public ReceiveResponse updateReceive(@PathVariable Long receiveId, @PathVariable Long storeId, @Valid @RequestBody ReceiveRequest request) {
        return receiveService.updateReceive(receiveId, storeId, request);
    }

    @Operation(summary = "Delete receive", description = "Deletes a product receipt by its ID")
    @DeleteMapping("/{receiveId}")
    public void deleteReceive(@PathVariable Long receiveId) {
        receiveService.deleteReceive(receiveId);
    }
}

