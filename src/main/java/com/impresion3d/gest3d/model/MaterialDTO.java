package com.impresion3d.gest3d.model;

import jakarta.persistence.*;
import lombok.Data;

@Data // Build Getters & Setters by Lombok
public class MaterialDTO {


    private String nombre;
    private String descripcion;
    private double metros_k;
    private String tipoUso;
    private double resistencia;


}
