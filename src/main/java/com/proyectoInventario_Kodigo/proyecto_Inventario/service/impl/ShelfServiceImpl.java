package com.proyectoInventario_Kodigo.proyecto_Inventario.service.impl;

import com.proyectoInventario_Kodigo.proyecto_Inventario.exceptions.AisleNotFoundException;
import com.proyectoInventario_Kodigo.proyecto_Inventario.exceptions.DuplicateAisleNameException;
import com.proyectoInventario_Kodigo.proyecto_Inventario.exceptions.DuplicateShelfNameException;
import com.proyectoInventario_Kodigo.proyecto_Inventario.exceptions.ShelfNotFoundException;
import com.proyectoInventario_Kodigo.proyecto_Inventario.mapper.ShelfMapper;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.AisleRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.AisleResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ShelfRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.ShelfResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Aisle;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Shelf;
import com.proyectoInventario_Kodigo.proyecto_Inventario.repository.ShelfRepository;
import com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces.ShelfService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import static com.proyectoInventario_Kodigo.proyecto_Inventario.utils.ErrorCatalog.*;

@Service
@RequiredArgsConstructor
public class ShelfServiceImpl implements ShelfService {

    private final ShelfRepository shelfRepository;
    private final ShelfMapper shelfMapper;

    @Override
    public List<ShelfResponse> findAll() {

        return shelfRepository.findAll()
                .stream()
                .map(shelfMapper::toShelfResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ShelfResponse findByName(String name) {

        return shelfRepository.findByName(name)
                .map(shelfMapper::toShelfResponse)
                .orElseThrow(ShelfNotFoundException::new);
    }

    @Override
    public ShelfResponse save(ShelfRequest request) {

        if (shelfRepository.findByName(request.getName()).isPresent()) {
            throw new DuplicateShelfNameException(DUPLICATE_SHELF_NAME);
        }

        Shelf shelf = shelfMapper.toShelf(request);
        Shelf savedShelf = shelfRepository.save(shelf);
        return shelfMapper.toShelfResponse(savedShelf);
    }

    @Override
    @Transactional
    public ShelfResponse update(Long id, ShelfRequest request) {
        return shelfRepository.findById(id)
                .map(existingShelf -> {
                    String newName = request.getName();

                    if (!existingShelf.getName().equals(newName)) {
                        shelfRepository.findByName(newName)
                                .ifPresent(existingShelfWithSameName -> {
                                    throw new DuplicateShelfNameException(DUPLICATE_SHELF_NAME);
                                });
                    }

                    shelfMapper.updateShelfFromRequest(request, existingShelf);
                    Shelf updatedShelf = shelfRepository.save(existingShelf);
                    return shelfMapper.toShelfResponse(updatedShelf);
                })
                .orElseThrow(AisleNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        if(shelfRepository.findById(id).isEmpty()){
            throw new ShelfNotFoundException();
        }

        shelfRepository.deleteById(id);

    }
}
