package com.proyectoInventario_Kodigo.proyecto_Inventario.service.impl;

import com.proyectoInventario_Kodigo.proyecto_Inventario.exceptions.*;
import com.proyectoInventario_Kodigo.proyecto_Inventario.mapper.BrandMapper;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.BrandRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.BrandResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Brand;
import com.proyectoInventario_Kodigo.proyecto_Inventario.repository.BrandRepository;
import com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces.BrandService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import static com.proyectoInventario_Kodigo.proyecto_Inventario.utils.ErrorCatalog.*;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Override
    public BrandResponse findById(Long id) {
        return brandRepository.findById(id)
                .map(brandMapper::toBrandResponse)
                .orElseThrow(BrandNotFoundException::new);
    }

    @Override
    public BrandResponse findByCode(String code) {
        return brandRepository.findByCode(code)
                .map(brandMapper::toBrandResponse)
                .orElseThrow(BrandNotFoundException::new);
    }

    @Override
    public BrandResponse findByName(String name) {
        return brandRepository.findByName(name)
                .map(brandMapper::toBrandResponse)
                .orElseThrow(BrandNotFoundException::new);
    }

    @Override
    public List<BrandResponse> findAll() {
        return brandRepository.findAll()
                .stream()
                .map(brandMapper::toBrandResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BrandResponse saveBrand(BrandRequest request) {

        if (brandRepository.findByName(request.getName()).isPresent()) {
            throw new DuplicateBrandNameException(DUPLICATE_BRAND_NAME);
        }

        Brand brand = brandMapper.toBrand(request);
        brand.setCode(generateBrandCode());

        Brand savedBrand = brandRepository.save(brand);
        return brandMapper.toBrandResponse(savedBrand);
    }

    @Override
    @Transactional
    public BrandResponse updateBrand(String code, BrandRequest request) {
        return brandRepository.findByCode(code)
                .map(brand -> {
                    if (!brand.getName().equals(request.getName())) {
                        brandRepository.findByName(request.getName())
                                .ifPresent(existingBrand -> {
                                    if (!existingBrand.getName().equals(request.getName())) {
                                        throw new DuplicateBrandNameException(DUPLICATE_BRAND_NAME);
                                    }
                                });
                    }

                    brandMapper.updateBrandFromRequest(request, brand);
                    return brandRepository.save(brand);
                })
                .map(brandMapper::toBrandResponse)
                .orElseThrow(BrandNotFoundException::new);
    }

    private String generateBrandCode() {
        String prefix = "BRN";
        String suffix = String.format("%04d", brandRepository.count() + 1);
        return prefix + suffix;
    }

    @Override
    public void deleteByCode(String code) {
        brandRepository.findByCode(code)
                .orElseThrow(BrandNotFoundException::new);

        brandRepository.deleteByCode(code);
    }
}

