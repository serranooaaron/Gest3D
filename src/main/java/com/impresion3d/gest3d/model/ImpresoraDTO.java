package com.impresion3d.gest3d.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data // Build Getters & Setters by Lombok
public class ImpresoraDTO {

    private String nombre;
    private String marca;
    private String modelo;
    private float alto_imp;
    private float ancho_imp;
    private float largo_imp;
    private double consumo_hora;
    private double horas_uso;
    private Integer ultimo_mantenimiento;

    private Long pieza;
}
