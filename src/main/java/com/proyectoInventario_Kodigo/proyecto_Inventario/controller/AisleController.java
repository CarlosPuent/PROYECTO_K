package com.proyectoInventario_Kodigo.proyecto_Inventario.controller;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.AisleRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.AisleResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces.AisleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Aisle Management", description = "APIs for managing aisles")
@RestController
@RequestMapping("/api/v1/aisles")
@RequiredArgsConstructor
public class AisleController {

    private final AisleService aisleService;

    @Operation(summary = "Get all aisles", description = "Returns a list of all aisles")
    @GetMapping
    public List<AisleResponse> getAllAisle() {
        return aisleService.findAll();
    }

    @Operation(summary = "Get aisle by name", description = "Returns an aisle by its name")
    @GetMapping("/name/{name}")
    public AisleResponse findByName(@PathVariable String name) {
        return aisleService.findByName(name);
    }

    @Operation(summary = "Create a new aisle", description = "Creates a new aisle")
    @PostMapping
    public ResponseEntity<AisleResponse> saveAisle(@Valid @RequestBody AisleRequest aisleRequest){
        AisleResponse aisleResponse = aisleService.save(aisleRequest);
        return ResponseEntity.created(URI.create("/api/v1/aisles/" + aisleResponse.getId())).body(aisleResponse);
    }

    @Operation(summary = "Update an aisle", description = "Updates an existing aisle")
    @PutMapping("/{id}")
    public AisleResponse updateAisle(@PathVariable Long id, @Valid @RequestBody AisleRequest aisleRequest) {
        return aisleService.update(id, aisleRequest);
    }

    @Operation(summary = "Delete an aisle", description = "Deletes an aisle by its ID")
    @DeleteMapping("/{id}")
    public void deleteAisle(@PathVariable Long id) {
        aisleService.deleteById(id);
    }
}