package com.impresion3d.gest3d.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Entity // JPA CLASS - Identifica con BD
@Data // GETTERS & SETTERS By Lombok
@NoArgsConstructor// Constructor sin argumentos/var By Lombok
@AllArgsConstructor// Constructor automatico de todos los argumentos By Lombok
@Table
public class Pieza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToMany(mappedBy = "pieza")
//    private List<Impresiones> impresiones;

    @ManyToOne
    @JoinColumn(name = "impresoras_id")
    private Impresora impresora;

    private String nombre;
    private int calidad;

    // @Lob
    private String archivo_gcode;


}
