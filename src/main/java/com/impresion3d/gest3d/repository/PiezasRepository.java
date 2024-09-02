package com.impresion3d.gest3d.repository;

import com.impresion3d.gest3d.model.Piezas;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PiezasRepository extends JpaRepository <Piezas, Long>{

    Optional<Piezas> findPiezasByNombre(String nombre);

}
