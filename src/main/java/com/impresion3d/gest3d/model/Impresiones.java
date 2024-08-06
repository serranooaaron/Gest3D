package com.impresion3d.gest3d.model;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table
public class Impresiones {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long impresion_id;
    private String nombre;
    
    @ManyToOne
    @JoinColumn(name = "pieza_id")
    private Piezas pieza;

    @ManyToOne
    @JoinColumn(name = "rollo_id")
    private Rollos rollo;

    private LocalDateTime fechaImpresion;
    private float tiempo;
    private float peso;
    private float costo_kwh;
    private double costo_pieza;

    public Impresiones(Long impresion_id, String nombre , Piezas pieza, Rollos rollo, LocalDateTime fechaImpresion, float tiempo, float peso, float costo_kwh, double costo_pieza) {
        this.impresion_id = impresion_id;
        this.nombre= nombre;
        this.pieza = pieza;
        this.rollo = rollo;
        this.fechaImpresion = fechaImpresion;
        this.tiempo = tiempo;
        this.peso = peso;
        this.costo_kwh = costo_kwh;
        this.costo_pieza = costo_pieza;
    }
    

}
