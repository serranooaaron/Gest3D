package com.impresion3d.gest3d.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data // Build Getters & Setters by Lombok
public class ImpresionDTO {

    private String nombre;
    private LocalDateTime fechaImpresion;
    private float tiempo;
    private float peso;
    private float costo_kwh;
    private double costo_pieza;

    private Long pieza;
    private Long rollo;



}
