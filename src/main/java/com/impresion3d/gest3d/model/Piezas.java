package com.impresion3d.gest3d.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity // JPA CLASS - Identifica con BD
@Data // GETTERS & SETTERS By Lombok
@NoArgsConstructor// Constructor sin argumentos/var By Lombok
@AllArgsConstructor// Constructor automatico de todos los argumentos By Lombok
@Table
public class Piezas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToMany(mappedBy = "pieza")
//    private List<Impresiones> impresiones;

    @ManyToOne
    @JoinColumn(name = "impresoras_id")
    private Impresoras impresoras;

    private String nombre;
    private int calidad;
    private char archivo_gcode;


}
