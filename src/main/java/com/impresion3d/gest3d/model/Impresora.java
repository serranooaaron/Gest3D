package com.impresion3d.gest3d.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // JPA CLASS - Identifica con BD
@Data // GETTERS & SETTERS By Lombok
@NoArgsConstructor// Constructor sin argumentos/var By Lombok
@AllArgsConstructor// Constructor automatico de todos los argumentos By Lombok
@Table
public class Impresora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "pieza_id")
    private Pieza pieza;

    private String marca;
    private String modelo;
    private float alto_imp;
    private float ancho_imp;
    private float largo_imp;
    private double consumo_hora;
    private double horas_uso;
    private Integer ultimo_mantenimiento;



}
