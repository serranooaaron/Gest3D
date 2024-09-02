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
@Table // Conecta Entity + BD - Se puede instanciar nombre de Tabla ej: {="rollosTable"} o utilizara por defecto nombre clase.
public class Rollos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String color;
    private double peso_gr;
    private double costo;


//    @OneToMany(mappedBy = "rollo")
//    private List<Impresiones> impresiones;

    @ManyToOne
    @JoinColumn(name = "Material_id")
    private Materiales material;


}
