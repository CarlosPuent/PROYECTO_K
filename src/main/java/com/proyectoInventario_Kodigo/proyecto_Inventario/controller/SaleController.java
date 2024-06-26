package com.proyectoInventario_Kodigo.proyecto_Inventario.controller;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.SaleRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.SaleResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces.SaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Sale Management", description = "APIs for managing sales")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sales")
public class SaleController {

    private final SaleService saleService;

    @Operation(summary = "Create a new sale", description = "Creates a new sale")
    @PostMapping
    public ResponseEntity<SaleResponse> createSale(@Valid @RequestBody SaleRequest request) {
        SaleResponse saleResponse = saleService.createSale(request);
        return ResponseEntity.created(URI.create("/api/v1/sales/" + saleResponse.getId())).body(saleResponse);
    }

    @Operation(summary = "Update a sale", description = "Updates an existing sale")
    @PutMapping("/{saleId}")
    public ResponseEntity<SaleResponse> updateSale(@PathVariable Long saleId, @Valid @RequestBody SaleRequest request) {
        SaleResponse saleResponse = saleService.updateSale(saleId, request);
        return ResponseEntity.ok(saleResponse);
    }

    @Operation(summary = "Delete a sale", description = "Deletes a sale by its ID")
    @DeleteMapping("/{saleId}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long saleId) {
        saleService.deleteSale(saleId);
        return ResponseEntity.noContent().build();
    }
}