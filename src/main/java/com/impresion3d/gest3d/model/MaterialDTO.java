package com.impresion3d.gest3d.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data // Build Getters & Setters by Lombok
public class MaterialDTO {

    @NotEmpty(message = "Ingresa nombre del material")
    private String nombre;

    @Size(min=10, message = "La descripción debe tener al menos 10 caracteres")
    @Size(max = 2000, message = "La descripción tiene que tener menos de 2000 caracteres")
    @NotEmpty(message = "Inserte descripcion del material")
    private String descripcion;

    @NotEmpty(message = "Inserte tipo de uso")
    private String tipoUso;

    @NotNull(message = "Inserte metros por kilo")
    private double metros_k;

    @NotNull(message = "Inserte resistencia del material")
    private double resistencia;


}
