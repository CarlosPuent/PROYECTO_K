package com.proyectoInventario_Kodigo.proyecto_Inventario.repository;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Brand;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findByCode(String code);
    Optional<Brand> findByName(String name);

    @Modifying
    @Transactional
    @Query("DELETE FROM Brand b WHERE b.code = :code")
    void deleteByCode(String code);
}
