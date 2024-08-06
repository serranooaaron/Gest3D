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
public class Piezas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pieza_id;

    @ManyToOne
    @JoinColumn(name = "IDimpresora")
    private Impresoras IDimpresora;

    private String nombre_pieza;
    private int calidad;
    private char archivo_gcode;

    public Piezas(Long pieza_id, Impresoras IDimpresora, String nombre_pieza, int calidad, char archivo_gcode) {
        this.pieza_id = pieza_id;
        this.IDimpresora = IDimpresora;
        this.nombre_pieza = nombre_pieza;
        this.calidad = calidad;
        this.archivo_gcode = archivo_gcode;
    }
    
    
}
