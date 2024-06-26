package com.proyectoInventario_Kodigo.proyecto_Inventario.controller;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.BrandRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.BrandResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Brand Management", description = "APIs for managing brands")
@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @Operation(summary = "Get all brands", description = "Returns a list of all brands")
    @GetMapping
    public List<BrandResponse> getAllBrands() {
        return brandService.findAll();
    }

    @Operation(summary = "Get brand by ID", description = "Returns a brand by its ID")
    @GetMapping("/{id}")
    public BrandResponse findById(@PathVariable Long id) {
        return brandService.findById(id);
    }

    @Operation(summary = "Get brand by code", description = "Returns a brand by its code")
    @GetMapping("/byCode/{code}")
    public BrandResponse findByCode(@PathVariable String code) {
        return brandService.findByCode(code);
    }

    @Operation(summary = "Get brand by name", description = "Returns a brand by its name")
    @GetMapping("/byName/{name}")
    public BrandResponse findByName(@PathVariable String name) {
        return brandService.findByName(name);
    }

    @Operation(summary = "Create a new brand", description = "Creates a new brand")
    @PostMapping
    public ResponseEntity<BrandResponse> saveBrand(@Valid @RequestBody BrandRequest brandRequest) {
        BrandResponse brandResponse = brandService.saveBrand(brandRequest);
        return ResponseEntity.created(URI.create("/api/v1/brands/" + brandResponse.getId())).body(brandResponse);
    }

    @Operation(summary = "Update a brand", description = "Updates an existing brand")
    @PutMapping("/{code}")
    public BrandResponse updateBrand(@PathVariable String code, @Valid @RequestBody BrandRequest brandRequest) {
        return brandService.updateBrand(code, brandRequest);
    }

    @Operation(summary = "Delete a brand", description = "Deletes a brand by its code")
    @DeleteMapping("/{code}")
    public void deleteBrand(@PathVariable String code) {
        brandService.deleteByCode(code);
    }
}