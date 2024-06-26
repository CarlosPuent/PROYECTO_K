package com.proyectoInventario_Kodigo.proyecto_Inventario.service.impl;

import com.proyectoInventario_Kodigo.proyecto_Inventario.exceptions.*;
import com.proyectoInventario_Kodigo.proyecto_Inventario.mapper.ProductMapper;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ProductRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ProductResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.*;
import com.proyectoInventario_Kodigo.proyecto_Inventario.repository.BrandRepository;
import com.proyectoInventario_Kodigo.proyecto_Inventario.repository.CategoryRepository;
import com.proyectoInventario_Kodigo.proyecto_Inventario.repository.ModelProductRepository;
import com.proyectoInventario_Kodigo.proyecto_Inventario.repository.ProductRepository;
import com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces.ProductService;
import com.proyectoInventario_Kodigo.proyecto_Inventario.utils.ProductStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import static com.proyectoInventario_Kodigo.proyecto_Inventario.utils.ErrorCatalog.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ModelProductRepository modelProductRepository;

    @Override
    public ProductResponse findById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toResponse)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public ProductResponse findByCode(String code) {
        return productRepository.findByCode(code)
                .map(productMapper::toResponse)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public ProductResponse findByName(String name) {
        return productRepository.findByName(name)
                .map(productMapper::toResponse)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public List<ProductResponse> findAllByCategoryCode(String categoryCode) {
        Category category = categoryRepository.findByCode(categoryCode)
                .orElseThrow(CategoryNotFoundException::new);

        return productRepository.findAllByCategoriesContains(category).stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> findAllByBrandCode(String brandCode) {
        Brand brand = brandRepository.findByCode(brandCode)
                .orElseThrow(BrandNotFoundException::new);
        return productRepository.findAllByBrandsContains(brand).stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse findProductByModelCode(String modelCode) {
        ModelProduct model = modelProductRepository.findByCode(modelCode)
                .orElseThrow(ModelNotFoundException::new);

        Product product = model.getProduct();
        return productMapper.toResponse(product);
    }

    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductResponse save(ProductRequest request) {
        if (productRepository.findByName(request.getName()).isPresent()) {
            throw new DuplicateProductNameException(DUPLICATE_PRODUCT_NAME);
        }

        Set<Category> categories = new HashSet<>(categoryRepository.findAllById(request.getCategoryIds()));
        if (categories.size() != request.getCategoryIds().size()) {
            throw new CategoryNotFoundException();
        }

        Set<Brand> brands = new HashSet<>(brandRepository.findAllById(request.getBrandIds()));
        if (brands.size() != request.getBrandIds().size()) {
            throw new BrandNotFoundException();
        }

        Product product = productMapper.toEntity(request);
        product.setCode(generateProductCode());
        product.setCategories(categories);
        product.setBrands(brands);
        product.setStatus(ProductStatus.ACTIVO);

        AtomicInteger modelCounter = new AtomicInteger((int) modelProductRepository.count());

        Set<ModelProduct> models = request.getModels().stream().map(modelRequest -> {
            ModelProduct model = new ModelProduct();
            model.setCode(generateModelCode(modelCounter.incrementAndGet()));
            model.setName(modelRequest.getName());
            model.setProduct(product);
            model.setCreatedAt(LocalDateTime.now());
            return model;
        }).collect(Collectors.toCollection(LinkedHashSet::new));

        product.setModels(models);

        Product savedProduct = productRepository.save(product);
        return productMapper.toResponse(savedProduct);
    }

    @Override
    @Transactional
    public ProductResponse update(String code, ProductRequest request) {
        Product existingProduct = productRepository.findByCode(code)
                .orElseThrow(ProductNotFoundException::new);

        if (!existingProduct.getName().equals(request.getName())) {
            validateUniqueName(request.getName());
        }

        Set<Category> categories = new HashSet<>(categoryRepository.findAllById(request.getCategoryIds()));
        if (categories.size() != request.getCategoryIds().size()) {
            throw new CategoryNotFoundException();
        }

        Set<Brand> brands = new HashSet<>(brandRepository.findAllById(request.getBrandIds()));
        if (brands.size() != request.getBrandIds().size()) {
            throw new BrandNotFoundException();
        }

        AtomicInteger modelCounter = new AtomicInteger((int) modelProductRepository.count());

        Set<ModelProduct> models = request.getModels().stream().map(modelRequest -> {
            ModelProduct model = new ModelProduct();
            model.setCode(generateModelCode(modelCounter.incrementAndGet()));
            model.setName(modelRequest.getName());
            model.setProduct(existingProduct);
            model.setCreatedAt(LocalDateTime.now());
            return model;
        }).collect(Collectors.toSet());

        existingProduct.getModels().clear();
        existingProduct.getModels().addAll(models);
        existingProduct.setName(request.getName());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setCategories(categories);
        existingProduct.setBrands(brands);
        existingProduct.setStatus(ProductStatus.ACTIVO);

        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toResponse(updatedProduct);
    }

    @Override
    @Transactional
    public void deleteByCode(String code) {
        Product product = productRepository.findByCode(code)
                .orElseThrow(ProductNotFoundException::new);

        productRepository.delete(product);
    }

    private void validateUniqueCodeAndName(String code, String name) {
        if (productRepository.findByCode(code).isPresent()) {
            throw new DuplicateProductCodeException(DUPLICATE_PRODUCT_CODE);
        }

        if (productRepository.findByName(name).isPresent()) {
            throw new DuplicateProductNameException(DUPLICATE_PRODUCT_NAME);
        }
    }

    private void validateUniqueName(String name) {
        if (productRepository.findByName(name).isPresent()) {
            throw new DuplicateProductNameException(DUPLICATE_PRODUCT_NAME);
        }
    }

    private String generateProductCode() {
        String prefix = "PRD";
        String suffix = String.format("%04d", productRepository.count() + 1);
        return prefix + suffix;
    }

    private String generateModelCode(int count) {
        String prefix = "MDL";
        String suffix = String.format("%04d", count);
        return prefix + suffix;
    }
}

