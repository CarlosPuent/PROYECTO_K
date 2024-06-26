package com.proyectoInventario_Kodigo.proyecto_Inventario.repository;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProductStoreExistenceRepository extends JpaRepository<ProductStoreExistence, Long> {

    List<ProductStoreExistence> findByProduct(Product product);

    void deleteByProductAndStoreAndAisleAndShelf(Product product, Store store, Aisle aisle, Shelf shelf);

    Optional<ProductStoreExistence> findByProductAndStore(Product product, Store store);
}
