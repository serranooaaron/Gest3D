package com.impresion3d.gest3d.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data // Build Getters & Setters by Lombok
public class ImpresoraDTO {

    @NotEmpty(message = "Inserte nombre de la impresora")
    private String nombre;
    @NotEmpty(message = "Inserte marca de la impresora")
    private String marca;
    @NotEmpty(message = "Inserte modelo de la impresora")
    private String modelo;
    @NotNull(message = "Inserte alto de impresora")
    private float alto_imp;
    @NotNull(message = "Inserte ancho de impresora")
    private float ancho_imp;
    @NotNull(message = "Inserte largo de impresora")
    private float largo_imp;
    @NotNull(message = "Inserte consumo por hora de la impresora")
    private double consumo_hora;
    @NotNull(message = "Inserte las horas de uso que tiene la impresora")
    private double horas_uso;
    @NotNull(message = "Inserte ultimo mantenimiento")
    private Integer ultimo_mantenimiento;

    private Long pieza;
}
