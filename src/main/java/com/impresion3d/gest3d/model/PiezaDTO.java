package com.impresion3d.gest3d.model;

import lombok.Data;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data // Build Getters & Setters by Lombok
public class PiezaDTO {


//    private List<Impresiones> impresiones;

    private Long impresora;

    private String nombre;
    private int calidad;


    private String archivo_gcode;



}
