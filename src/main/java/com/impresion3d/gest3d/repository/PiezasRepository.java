package com.impresion3d.gest3d.repository;

import com.impresion3d.gest3d.model.Pieza;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PiezasRepository extends JpaRepository <Pieza, Long>{

    Optional<Pieza> findPiezasByNombre(String nombre);

}
