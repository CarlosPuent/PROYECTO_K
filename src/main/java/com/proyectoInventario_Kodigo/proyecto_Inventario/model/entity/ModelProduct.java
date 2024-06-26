package com.proyectoInventario_Kodigo.proyecto_Inventario.model.entity;

import com.proyectoInventario_Kodigo.proyecto_Inventario.auditable.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "models", uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})})
public class ModelProduct extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String code;

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelProduct)) return false;
        ModelProduct model = (ModelProduct) o;
        return Objects.equals(id, model.id) && Objects.equals(code, model.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code);
    }
}