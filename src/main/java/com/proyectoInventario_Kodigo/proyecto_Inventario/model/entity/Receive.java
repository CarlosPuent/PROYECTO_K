package com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity;

import com.proyectoInventario_Kodigo.proyecto_Inventario.auditable.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "receives")
public class Receive extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "receive", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReceiveDetail> details = new HashSet<>();
}
