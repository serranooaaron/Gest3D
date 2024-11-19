package com.impresion3d.gest3d.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data // Build Getters & Setters by Lombok
public class MaterialDTO {

    @NotEmpty(message = "Ingresa nombre")
    private String nombre;
    private String descripcion;
    private double metros_k;
    private String tipoUso;
    private double resistencia;


}
