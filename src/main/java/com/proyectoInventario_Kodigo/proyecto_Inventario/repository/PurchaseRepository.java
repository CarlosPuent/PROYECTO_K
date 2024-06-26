package com.proyectoInventario_Kodigo.proyecto_Inventario.repository;

import com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {


}

