package com.proyectoInventario_Kodigo.proyecto_Inventario.service.impl;

import com.proyectoInventario_Kodigo.proyecto_Inventario.exceptions.DuplicateStoreNameException;
import com.proyectoInventario_Kodigo.proyecto_Inventario.exceptions.StoreNotFoundException;
import com.proyectoInventario_Kodigo.proyecto_Inventario.mapper.AisleMapper;
import com.proyectoInventario_Kodigo.proyecto_Inventario.mapper.ShelfMapper;
import com.proyectoInventario_Kodigo.proyecto_Inventario.mapper.StoreMapper;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.StoreRequest;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.dto.StoreResponse;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Aisle;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Shelf;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Store;
import com.proyectoInventario_Kodigo.proyecto_Inventario.repository.AisleRepository;
import com.proyectoInventario_Kodigo.proyecto_Inventario.repository.ShelfRepository;
import com.proyectoInventario_Kodigo.proyecto_Inventario.repository.StoreRepository;
import com.proyectoInventario_Kodigo.proyecto_Inventario.service.interfaces.StoreService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static com.proyectoInventario_Kodigo.proyecto_Inventario.utils.ErrorCatalog.*;

@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final ShelfRepository shelfRepository;
    private final AisleRepository aisleRepository;
    private final StoreMapper storeMapper;
    private final AisleMapper aisleMapper;
    private final ShelfMapper shelfMapper;

    @Override
    public StoreResponse findById(Long id) {
        return storeRepository.findById(id)
                .map(storeMapper::toStoreResponse)
                .orElseThrow(StoreNotFoundException::new);
    }

    @Override
    public StoreResponse findByCode(String code) {
        return storeRepository.findByCode(code)
                .map(storeMapper::toStoreResponse)
                .orElseThrow(StoreNotFoundException::new);
    }

    @Override
    public StoreResponse findByName(String name) {
        return storeRepository.findByName(name)
                .map(storeMapper::toStoreResponse)
                .orElseThrow(StoreNotFoundException::new);
    }

    @Override
    public List<StoreResponse> findAll() {
        return storeRepository.findAll()
                .stream()
                .map(storeMapper::toStoreResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StoreResponse save(StoreRequest request) {
        if (storeRepository.findByName(request.getName()).isPresent()) {
            throw new DuplicateStoreNameException(DUPLICATE_STORE_NAME);
        }

        Store store = storeMapper.toStore(request);
        store.setCode(generateStoreCode());

        Set<Aisle> aisles = request.getAisles().stream().map(aisleRequest -> {
            Aisle aisle = aisleMapper.toAisle(aisleRequest);
            Set<Shelf> shelves = aisleRequest.getShelves().stream()
                    .map(shelfMapper::toShelf)
                    .collect(Collectors.toSet());
            shelfRepository.saveAll(shelves);  // Guardar las estanterías primero
            aisle.setShelves(shelves);
            return aisle;
        }).collect(Collectors.toSet());

        aisleRepository.saveAll(aisles);  // Guardar los pasillos antes de asignarlos a la tienda
        store.setAisles(aisles);

        Store savedStore = storeRepository.save(store);
        return storeMapper.toStoreResponse(savedStore);
    }

    @Override
    @Transactional
    public StoreResponse update(String code, StoreRequest request) {
        return storeRepository.findByCode(code)
                .map(store -> {
                    if (!store.getName().equals(request.getName())) {
                        if (storeRepository.findByName(request.getName()).isPresent()) {
                            throw new DuplicateStoreNameException(DUPLICATE_STORE_NAME);
                        }
                    }

                    store.setName(request.getName());
                    store.setAddress(request.getAddress());
                    store.setPhone(request.getPhone());

                    Set<Aisle> aisles = request.getAisles().stream().map(aisleRequest -> {
                        Aisle aisle = aisleMapper.toAisle(aisleRequest);
                        Set<Shelf> shelves = aisleRequest.getShelves().stream()
                                .map(shelfMapper::toShelf)
                                .collect(Collectors.toSet());
                        shelfRepository.saveAll(shelves);  // Guardar las estanterías primero
                        aisle.setShelves(shelves);
                        return aisle;
                    }).collect(Collectors.toSet());

                    aisleRepository.saveAll(aisles);  // Guardar los pasillos antes de asignarlos a la tienda
                    store.setAisles(aisles);

                    return storeRepository.save(store);
                })
                .map(storeMapper::toStoreResponse)
                .orElseThrow(StoreNotFoundException::new);
    }

    private String generateStoreCode() {
        String prefix = "STOR";
        String suffix = String.format("%04d", storeRepository.count() + 1);
        return prefix + suffix;
    }

    @Override
    @Transactional
    public void deleteByCode(String code) {
        if (storeRepository.findByCode(code).isEmpty()) {
            throw new StoreNotFoundException();
        }
        storeRepository.deleteByCode(code);
    }
}
