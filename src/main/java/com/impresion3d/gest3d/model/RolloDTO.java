package com.impresion3d.gest3d.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data // Build Getters & Setters by Lombok
public class RolloDTO {

    private String nombre;
    private String color;
    private double peso_gr;
    private double costo;
//
//    private List<Impresiones> impresiones;

    private Long material;

}
