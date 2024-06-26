package com.proyectoInventario_Kodigo.proyecto_Inventario.repository;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCode(String code);
    Optional<Category> findByName(String name);

    @Modifying
    @Transactional
    @Query("DELETE FROM Category c WHERE c.code = :code")
    void deleteByCode(@Param("code") String code);
}
