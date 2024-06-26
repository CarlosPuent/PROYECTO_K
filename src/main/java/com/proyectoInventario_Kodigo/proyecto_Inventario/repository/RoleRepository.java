package com.proyectoInventario_Kodigo.proyecto_Inventario.repository;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    @Query("SELECT r FROM RoleEntity r WHERE r.name=?1")
    Optional<RoleEntity> findByName(String name);
}
