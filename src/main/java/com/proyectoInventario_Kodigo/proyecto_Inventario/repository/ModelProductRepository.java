package com.proyectoInventario_Kodigo.proyecto_Inventario.repository;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.ModelProduct;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ModelProductRepository extends JpaRepository<ModelProduct, Integer> {

    Optional<ModelProduct> findByCode(String code);
    Optional<ModelProduct> findByNameAndProduct(String name, Product product);
    long countByName(String name);
    boolean existsByCode(String code);

}
