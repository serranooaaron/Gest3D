package com.impresion3d.gest3d.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data // Build Getters & Setters by Lombok
public class RolloDTO {

    @NotEmpty(message = "Ingrese nombre del rollo")
    private String nombre;
    @NotEmpty(message = "Ingrese color del rollo")
    private String color;
    @NotNull(message = "Ingrese peso en gramos del rollo")
    private double peso_gr;
    @NotEmpty(message = "Ingrese el valor del rollo")
    private double costo;
//
//    private List<Impresiones> impresiones;

    private Long material;

}
