package com.proyectoInventario_Kodigo.proyecto_Inventario.controller;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.PurchaseRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.PurchaseResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Purchase Management", description = "APIs for managing purchases")
@RestController
@RequestMapping("/api/v1/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Operation(summary = "Create a new purchase", description = "Creates a new purchase")
    @PostMapping
    public ResponseEntity<PurchaseResponse> createPurchase(@Valid @RequestBody PurchaseRequest request) {
        PurchaseResponse response = purchaseService.createPurchase(request);
        return ResponseEntity.created(URI.create("/api/v1/purchases/" + response.getId())).body(response);
    }

    @Operation(summary = "Update a purchase", description = "Updates an existing purchase")
    @PutMapping("/{purchaseId}")
    public PurchaseResponse updatePurchase(@PathVariable Long purchaseId, @Valid @RequestBody PurchaseRequest request) {
        return purchaseService.updatePurchase(purchaseId, request);
    }

    @Operation(summary = "Delete a purchase", description = "Deletes a purchase by its ID")
    @DeleteMapping("/{purchaseId}")
    public void deletePurchase(@PathVariable Long purchaseId) {
        purchaseService.deletePurchase(purchaseId);
    }
}