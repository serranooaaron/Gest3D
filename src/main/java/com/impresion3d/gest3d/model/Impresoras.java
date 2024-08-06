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
public class Impresoras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IDimpresora;
    private String nom_impresora;

    @ManyToOne
    @JoinColumn(name = "pieza_id")
    private Piezas pieza_id;

    private String marca;
    private String modelo;
    private float alto_imp;
    private float ancho_imp;
    private float largo_imp;
    private double consumo_hora;
    private double horas_uso;
    private int ultimo_mantenimiento;

    public Impresoras(Long IDimpresora, String nom_impresora, Piezas pieza_id, String marca, String modelo, float alto_imp, float ancho_imp, float largo_imp, double consumo_hora, double horas_uso, int ultimo_mantenimiento) {
        this.IDimpresora = IDimpresora;
        this.nom_impresora = nom_impresora;
        this.pieza_id = pieza_id;
        this.marca = marca;
        this.modelo = modelo;
        this.alto_imp = alto_imp;
        this.ancho_imp = ancho_imp;
        this.largo_imp = largo_imp;
        this.consumo_hora = consumo_hora;
        this.horas_uso = horas_uso;
        this.ultimo_mantenimiento = ultimo_mantenimiento;
    }
    
    
    
}
