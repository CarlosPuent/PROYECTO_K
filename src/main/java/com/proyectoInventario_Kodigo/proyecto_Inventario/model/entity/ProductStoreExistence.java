package com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity;

import com.proyectoInventario_Kodigo.proyecto_Inventario.auditable.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_store_existence")
public class ProductStoreExistence extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne
    @JoinColumn(name = "aisle_id", nullable = false)
    private Aisle aisle;

    @ManyToOne
    @JoinColumn(name = "shelf_id", nullable = false)
    private Shelf shelf;

    @Column(nullable = false)
    private int quantity;
}
