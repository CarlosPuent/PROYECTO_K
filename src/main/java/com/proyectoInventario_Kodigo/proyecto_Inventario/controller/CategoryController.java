package com.proyectoInventario_Kodigo.proyecto_Inventario.controller;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.CategoryRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.CategoryResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Category Management", description = "APIs for managing categories")
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Get all categories", description = "Returns a list of all categories")
    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        return categoryService.findAll();
    }

    @Operation(summary = "Get category by ID", description = "Returns a category by its ID")
    @GetMapping("/{id}")
    public CategoryResponse findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @Operation(summary = "Get category by code", description = "Returns a category by its code")
    @GetMapping("/code/{code}")
    public CategoryResponse findByCode(@PathVariable String code) {
        return categoryService.findByCode(code);
    }

    @Operation(summary = "Get category by name", description = "Returns a category by its name")
    @GetMapping("/name/{name}")
    public CategoryResponse findByName(@PathVariable String name) {
        return categoryService.findByName(name);
    }

    @Operation(summary = "Create a new category", description = "Creates a new category")
    @PostMapping
    public ResponseEntity<CategoryResponse> saveCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryService.save(categoryRequest);
        return ResponseEntity.created(URI.create("/api/v1/categories/" + categoryResponse.getId())).body(categoryResponse);
    }

    @Operation(summary = "Update a category", description = "Updates an existing category")
    @PutMapping("/{code}")
    public CategoryResponse updateCategory(@PathVariable String code, @Valid @RequestBody CategoryRequest categoryRequest) {
        return categoryService.update(code, categoryRequest);
    }

    @Operation(summary = "Delete a category", description = "Deletes a category by its code")
    @DeleteMapping("/{code}")
    public void deleteCategory(@PathVariable String code) {
        categoryService.deleteByCode(code);
    }
}