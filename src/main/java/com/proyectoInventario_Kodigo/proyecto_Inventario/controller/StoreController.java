package com.proyectoInventario_Kodigo.proyecto_Inventario.controller;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.StoreRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.StoreResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Store Management", description = "APIs for managing stores")
@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @Operation(summary = "Get all stores", description = "Returns a list of all stores")
    @GetMapping
    public List<StoreResponse> getAllStores() {
        return storeService.findAll();
    }

    @Operation(summary = "Get store by ID", description = "Returns a store by its ID")
    @GetMapping("/{id}")
    public StoreResponse findById(@PathVariable Long id) {
        return storeService.findById(id);
    }

    @Operation(summary = "Get store by code", description = "Returns a store by its code")
    @GetMapping("/code/{code}")
    public StoreResponse findByCode(@PathVariable String code) {
        return storeService.findByCode(code);
    }

    @Operation(summary = "Get store by name", description = "Returns a store by its name")
    @GetMapping("/name/{name}")
    public StoreResponse findByName(@PathVariable String name) {
        return storeService.findByName(name);
    }

    @Operation(summary = "Create a new store", description = "Creates a new store")
    @PostMapping
    public ResponseEntity<StoreResponse> saveStore(@Valid @RequestBody StoreRequest storeRequest) {
        StoreResponse storeResponse = storeService.save(storeRequest);
        return ResponseEntity.created(URI.create("/api/v1/stores/" + storeResponse.getId())).body(storeResponse);
    }

    @Operation(summary = "Update a store", description = "Updates an existing store")
    @PutMapping("/{code}")
    public StoreResponse updateStore(@PathVariable String code, @Valid @RequestBody StoreRequest storeRequest) {
        return storeService.update(code, storeRequest);
    }

    @Operation(summary = "Delete a store", description = "Deletes a store by its code")
    @DeleteMapping("/{code}")
    public void deleteStore(@PathVariable String code) {
        storeService.deleteByCode(code);
    }
}
