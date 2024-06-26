package com.proyectoInventario_Kodigo.proyecto_Inventario.repository;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Aisle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AisleRepository extends JpaRepository<Aisle, Long> {

    Optional<Aisle> findByName(String name);
}
