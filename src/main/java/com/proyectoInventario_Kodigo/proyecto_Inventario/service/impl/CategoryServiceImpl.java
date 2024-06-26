package com.proyectoInventario_Kodigo.proyecto_Inventario.service.impl;

import com.proyectoInventario_Kodigo.proyecto_Inventario.exceptions.*;
import com.proyectoInventario_Kodigo.proyecto_Inventario.mapper.CategoryMapper;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.CategoryRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.CategoryResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Category;
import com.proyectoInventario_Kodigo.proyecto_Inventario.repository.CategoryRepository;
import com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import static com.proyectoInventario_Kodigo.proyecto_Inventario.utils.ErrorCatalog.*;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse findById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toCategoryResponse)
                .orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public CategoryResponse findByCode(String code) {
        return categoryRepository.findByCode(code)
                .map(categoryMapper::toCategoryResponse)
                .orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public CategoryResponse findByName(String name) {
        return categoryRepository.findByName(name)
                .map(categoryMapper::toCategoryResponse)
                .orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toCategoryResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse save(CategoryRequest request) {

        if (categoryRepository.findByName(request.getName()).isPresent()) {
            throw new DuplicateCategoryNameException(DUPLICATE_CATEGORY_NAME);
        }

        Category category = categoryMapper.toCategory(request);
        category.setCode(generateCategoryCode());

        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toCategoryResponse(savedCategory);
    }

    @Override
    public CategoryResponse update(String code, CategoryRequest request) {
        return categoryRepository.findByCode(code)
                .map(category -> {
                    if (!category.getName().equals(request.getName())) {
                        categoryRepository.findByName(request.getName())
                                .ifPresent(existingCategory -> {
                                    if (!existingCategory.getCode().equals(request.getName())) {
                                        throw new DuplicateCategoryNameException(DUPLICATE_CATEGORY_NAME);
                                    }
                                });
                    }

                    categoryMapper.updateCategoryFromRequest(request, category);
                    return categoryRepository.save(category);
                })
                .map(categoryMapper::toCategoryResponse)
                .orElseThrow(CategoryNotFoundException::new);
    }

    private String generateCategoryCode() {
        String prefix = "CATG";
        String suffix = String.format("%04d", categoryRepository.count() + 1);
        return prefix + suffix;
    }

    @Override
    public void deleteByCode(String code) {
        categoryRepository.findByCode(code)
                .orElseThrow(CategoryNotFoundException::new);

        categoryRepository.deleteByCode(code);
    }
}

