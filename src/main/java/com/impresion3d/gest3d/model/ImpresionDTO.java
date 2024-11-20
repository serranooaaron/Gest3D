package com.impresion3d.gest3d.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data // Build Getters & Setters by Lombok
public class ImpresionDTO {

    @NotEmpty(message = "Inserte nombre de la pieza")
    private String nombre;
    // private LocalDateTime fechaImpresion;
    @NotNull(message = "Inserte duracion de impresion")
    private float tiempo;
    @NotNull(message = "Inserte peso de impresion")
    private float peso;
    @NotNull(message = "Inserte costo de kwh de impresion")
    private float costo_kwh;
    @NotNull(message = "Inserte costo de pieza de impresion")
    private double costo_pieza;


    private Long pieza;
    private Long rollo;



}
