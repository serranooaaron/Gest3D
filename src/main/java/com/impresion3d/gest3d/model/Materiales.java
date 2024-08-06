package com.impresion3d.gest3d.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table
public class Materiales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long material_id;
    private String nombre;
    private String descripcion;
    private double metros_k;
    private String tipouso;
    private double resistencia;
    
    
    public Materiales(Long material_id, String nombre, String descripcion, double metros_k, String tipouso, double resistencia) {
        this.material_id = material_id;
        this.nombre=nombre;
        this.descripcion = descripcion;
        this.metros_k = metros_k;
        this.tipouso = tipouso;
        this.resistencia = resistencia;
    }
    
    
}
