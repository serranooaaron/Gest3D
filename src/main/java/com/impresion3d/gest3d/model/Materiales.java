package com.impresion3d.gest3d.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // JPA CLASS - Identifica con BD
@Data // GETTERS & SETTERS By Lombok
@NoArgsConstructor// Constructor sin argumentos/var By Lombok
@AllArgsConstructor// Constructor automatico de todos los argumentos By Lombok
@Table
public class Materiales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private double metros_k;
    private String tipouso;
    private double resistencia;

}
