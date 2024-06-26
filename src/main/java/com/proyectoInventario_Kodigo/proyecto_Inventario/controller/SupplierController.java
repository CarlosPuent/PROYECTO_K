package com.proyectoInventario_Kodigo.proyecto_Inventario.controller;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.SupplierRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.SupplierResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Supplier Management", description = "APIs for managing suppliers")
@RestController
@RequestMapping("/api/v1/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @Operation(summary = "Get all suppliers", description = "Returns a list of all suppliers")
    @GetMapping
    public List<SupplierResponse> getAllSupplier() {
        return supplierService.findAll();
    }

    @Operation(summary = "Get supplier by ID", description = "Returns a supplier by its ID")
    @GetMapping("/{id}")
    public SupplierResponse findById(@PathVariable Long id) {
        return supplierService.findById(id);
    }

    @Operation(summary = "Get supplier by code", description = "Returns a supplier by its code")
    @GetMapping("/code/{code}")
    public SupplierResponse findByCode(@PathVariable String code) {
        return supplierService.findByCode(code);
    }

    @Operation(summary = "Get supplier by name", description = "Returns a supplier by its name")
    @GetMapping("/name/{name}")
    public SupplierResponse findByName(@PathVariable String name) {
        return supplierService.findByName(name);
    }

    @Operation(summary = "Create a new supplier", description = "Creates a new supplier")
    @PostMapping
    public ResponseEntity<SupplierResponse> saveSupplier(@Valid @RequestBody SupplierRequest supplierRequest) {
        SupplierResponse supplierResponse = supplierService.save(supplierRequest);
        return ResponseEntity.created(URI.create("/api/v1/suppliers/" + supplierResponse.getId())).body(supplierResponse);
    }

    @Operation(summary = "Update a supplier", description = "Updates an existing supplier")
    @PutMapping("/{code}")
    public SupplierResponse updateSupplier(@PathVariable String code, @Valid @RequestBody SupplierRequest supplierRequest) {
        return supplierService.update(code, supplierRequest);
    }

    @Operation(summary = "Delete a supplier", description = "Deletes a supplier by its code")
    @DeleteMapping("/{code}")
    public void deleteSupplier(@PathVariable String code) {
        supplierService.deleteByCode(code);
    }
}