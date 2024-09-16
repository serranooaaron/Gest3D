package com.impresion3d.gest3d.model;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // JPA CLASS - Identifica con BD
@Data // GETTERS & SETTERS By Lombok
@NoArgsConstructor// Constructor sin argumentos/var By Lombok
@AllArgsConstructor// Constructor automatico de todos los argumentos By Lombok
@Table
public class Impresion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Pieza_id")
    private Pieza pieza;

    @ManyToOne
    @JoinColumn(name = "Rollo_id")
    private Rollo rollo;

    private String nombre;
    private LocalDateTime fechaImpresion;
    private float tiempo;
    private float peso;
    private float costo_kwh;
    private double costo_pieza;

}
