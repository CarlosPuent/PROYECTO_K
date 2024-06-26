package com.proyectoInventario_Kodigo.proyecto_Inventario.repository;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Supplier;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long>{

    Optional<Supplier> findByCode(String code);
    Optional<Supplier> findByName(String name);

    @Query("SELECT s FROM Supplier s WHERE s.id = :id")
    Optional<Supplier> findById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Supplier s WHERE s.code = :code")
    void deleteByCode(@Param("code") String code);
}
