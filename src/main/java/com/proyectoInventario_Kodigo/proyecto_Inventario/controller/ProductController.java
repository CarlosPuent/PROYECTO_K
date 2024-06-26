package com.proyectoInventario_Kodigo.proyecto_Inventario.controller;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ProductRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ProductResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Product Management", description = "APIs for managing products")
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Get all products", description = "Returns a list of all products")
    @GetMapping
    public List<ProductResponse> findAllProducts() {
        return productService.findAll();
    }

    @Operation(summary = "Get product by ID", description = "Returns a product by its ID")
    @GetMapping("/id/{id}")
    public ProductResponse findProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @Operation(summary = "Get product by code", description = "Returns a product by its code")
    @GetMapping("/code/{code}")
    public ProductResponse findProductByCode(@PathVariable String code) {
        return productService.findByCode(code);
    }

    @Operation(summary = "Get product by name", description = "Returns a product by its name")
    @GetMapping("/name/{name}")
    public ProductResponse findProductByName(@PathVariable String name) {
        return productService.findByName(name);
    }

    @Operation(summary = "Get products by category code", description = "Returns a list of products by category code")
    @GetMapping("/category/{CategoryCode}")
    public List<ProductResponse> findAllByCategoryCode(@PathVariable String CategoryCode) {
        return productService.findAllByCategoryCode(CategoryCode);
    }

    @Operation(summary = "Get products by brand code", description = "Returns a list of products by brand code")
    @GetMapping("/brand/{BrandCode}")
    public List<ProductResponse> findAllByBrandCode(@PathVariable String BrandCode) {
        return productService.findAllByBrandCode(BrandCode);
    }

    @Operation(summary = "Get product by model code", description = "Returns a product by its model code")
    @GetMapping("/model/{ModelCode}")
    public ProductResponse findAllProductByModelCode(@PathVariable String ModelCode) {
        return productService.findProductByModelCode(ModelCode);
    }

    @Operation(summary = "Create a new product", description = "Creates a new product")
    @PostMapping
    public ResponseEntity<ProductResponse> saveProduct(@Valid @RequestBody ProductRequest request) {
        ProductResponse productResponse = productService.save(request);
        return ResponseEntity.created(URI.create("/api/v1/products/" + productResponse.getId())).body(productResponse);
    }

    @Operation(summary = "Update a product", description = "Updates an existing product")
    @PutMapping("{code}")
    public ProductResponse updateProduct(@PathVariable String code, @Valid @RequestBody ProductRequest request) {
        return productService.update(code, request);
    }

    @Operation(summary = "Delete a product", description = "Deletes a product by its code")
    @DeleteMapping("{code}")
    public void deleteProductByCode(@PathVariable String code) {
        productService.deleteByCode(code);
    }
}