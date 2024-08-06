package com.impresion3d.gest3d.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table
public class Rollos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rollo_id;
    private String color;
    private double peso_gr;
    private double costo;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Materiales material_id;

    public Rollos(Long rollo_id, String nombre, String color, double peso_gr, double costo, Materiales material_id) {
        this.rollo_id = rollo_id;
        this.nombre = nombre;
        this.color = color;
        this.peso_gr = peso_gr;
        this.costo = costo;
        this.material_id = material_id;
    }
    
    
}
