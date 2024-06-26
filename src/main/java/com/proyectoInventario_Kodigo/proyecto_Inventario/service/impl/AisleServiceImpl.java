package com.proyectoInventario_Kodigo.proyecto_Inventario.service.impl;

import com.proyectoInventario_Kodigo.proyecto_Inventario.exceptions.*;
import com.proyectoInventario_Kodigo.proyecto_Inventario.mapper.AisleMapper;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.AisleRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.AisleResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Aisle;
import com.proyectoInventario_Kodigo.proyecto_Inventario.repository.AisleRepository;
import com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces.AisleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import static com.proyectoInventario_Kodigo.proyecto_Inventario.utils.ErrorCatalog.DUPLICATE_AISLE_NAME;

@Service
@RequiredArgsConstructor
public class AisleServiceImpl implements AisleService {

    private final AisleRepository aisleRepository;
    private final AisleMapper aisleMapper;

    @Override
    public List<AisleResponse> findAll() {
        return aisleRepository.findAll()
                .stream()
                .map(aisleMapper::toAisleResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AisleResponse findByName(String name) {
        return aisleRepository.findByName(name)
                .map(aisleMapper::toAisleResponse)
                .orElseThrow(AisleNotFoundException::new);
    }

    @Override
    @Transactional
    public AisleResponse save(AisleRequest request) {
        if (aisleRepository.findByName(request.getName()).isPresent()) {
            throw new DuplicateAisleNameException(DUPLICATE_AISLE_NAME);
        }

        Aisle aisle = aisleMapper.toAisle(request);
        Aisle savedAisle = aisleRepository.save(aisle);
        return aisleMapper.toAisleResponse(savedAisle);
    }

    @Override
    @Transactional
    public AisleResponse update(Long id, AisleRequest request) {
        return aisleRepository.findById(id)
                .map(existingAisle -> {
                    String newName = request.getName();

                    if (!existingAisle.getName().equals(newName)) {
                        aisleRepository.findByName(newName)
                                .ifPresent(existingAisleWithSameName -> {
                                    throw new DuplicateAisleNameException(DUPLICATE_AISLE_NAME);
                                });
                    }

                    aisleMapper.updateAisleFromRequest(request, existingAisle);
                    Aisle updatedAisle = aisleRepository.save(existingAisle);
                    return aisleMapper.toAisleResponse(updatedAisle);
                })
                .orElseThrow(AisleNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        if(aisleRepository.findById(id).isEmpty()){
            throw new AisleNotFoundException();
        }

        aisleRepository.deleteById(id);
    }
}
