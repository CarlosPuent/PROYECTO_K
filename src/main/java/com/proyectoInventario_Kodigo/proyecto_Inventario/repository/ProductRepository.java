package com.proyectoInventario_Kodigo.proyecto_Inventario.repository;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Brand;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Category;
import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>{

    List<Product> findAllByCategoriesContains(Category category);
    List<Product> findAllByBrandsContains(Brand brand);

    Optional<Product> findByCode(String code);
    Optional<Product> findByName(String name);

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.categories LEFT JOIN FETCH p.brands LEFT JOIN FETCH p.models WHERE p.name = :name")
    Optional<Product> findByNameWithRelations(@Param("name") String name);

    @Modifying
    @Transactional
    @Query("DELETE FROM Product p WHERE p.code = :code")
    void deleteByCode(@Param("code") String code);

}

