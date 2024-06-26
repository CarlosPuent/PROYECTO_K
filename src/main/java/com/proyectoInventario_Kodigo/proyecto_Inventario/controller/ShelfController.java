package com.proyectoInventario_Kodigo.proyecto_Inventario.controller;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ShelfRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ShelfResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces.ShelfService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Shelf Management", description = "APIs for managing shelfs")
@RestController
@RequestMapping("/api/v1/shelfs")
@RequiredArgsConstructor
public class ShelfController {

    private final ShelfService shelfService;

    @Operation(summary = "Get all shelfs", description = "Returns a list of all shelfs")
    @GetMapping
    public List<ShelfResponse> getAllShelf() {
        return shelfService.findAll();
    }

    @Operation(summary = "Get shelf by name", description = "Returns a shelf by its name")
    @GetMapping("/name/{name}")
    public ShelfResponse findByName(@PathVariable String name) {
        return shelfService.findByName(name);
    }

    @Operation(summary = "Create a new shelf", description = "Creates a new shelf")
    @PostMapping
    public ResponseEntity<ShelfResponse> saveShelf(@Valid @RequestBody ShelfRequest shelfRequest) {
        ShelfResponse shelfResponse = shelfService.save(shelfRequest);
        return ResponseEntity.created(URI.create("/api/v1/shelfs/" + shelfResponse.getId())).body(shelfResponse);
    }

    @Operation(summary = "Update a shelf", description = "Updates an existing shelf")
    @PutMapping("/{id}")
    public ShelfResponse updateShelf(@PathVariable Long id, @Valid @RequestBody ShelfRequest shelfRequest) {
        return shelfService.update(id, shelfRequest);
    }

    @Operation(summary = "Delete a shelf", description = "Deletes a shelf by its ID")
    @DeleteMapping("/{id}")
    public void deleteShelf(@PathVariable Long id) {
        shelfService.deleteById(id);
    }
}