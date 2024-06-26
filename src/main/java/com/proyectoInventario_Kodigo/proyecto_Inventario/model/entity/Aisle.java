package com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity;

import com.proyectoInventario_Kodigo.proyecto_Inventario.auditable.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "aisles")
public class Aisle extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "aisles")
    private Set<Store> stores = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "aisle_shelves",
            joinColumns = @JoinColumn(name = "aisle_id"),
            inverseJoinColumns = @JoinColumn(name = "shelf_id")
    )
    private Set<Shelf> shelves = new HashSet<>();
}

