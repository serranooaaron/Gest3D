package com.impresion3d.gest3d.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data // Build Getters & Setters by Lombok
public class PiezaDTO {


//    private List<Impresiones> impresiones;

    private Long impresora;

    @NotEmpty(message = "Inserte nombre de la pieza")
    private String nombre;

    @Min(value = 0 , message = "La calidad debe estar entre 0 y 5 - Siendo 0 la mas baja y 5 la mas alta")
    @Max(value = 5 , message = "La calidad debe estar entre 0 y 5 - Siendo 0 la mas baja y 5 la mas alta")
    @NotNull(message = "Inserte calidad")
    private int calidad;

    @NotEmpty(message = "Inserte el archivo .GCODE")
    private String archivo_gcode;



}
