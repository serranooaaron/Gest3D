package com.impresion3d.gest3d.model;

import lombok.Data;

@Data // Build Getters & Setters by Lombok
public class PiezaDTO {


//    private List<Impresiones> impresiones;

    private Long impresora;

    private String nombre;
    private int calidad;
    private String archivo_gcode;

}
