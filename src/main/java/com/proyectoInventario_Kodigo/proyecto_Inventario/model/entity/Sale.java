package com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity;

import com.proyectoInventario_Kodigo.proyecto_Inventario.auditable.Auditable;
import com.proyectoInventario_Kodigo.proyecto_Inventario.utils.MovementType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "sales")
public class Sale extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private MovementType type;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SaleDetail> details = new HashSet<>();

}
