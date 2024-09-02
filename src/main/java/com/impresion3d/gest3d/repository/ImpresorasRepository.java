package com.impresion3d.gest3d.repository;

import com.impresion3d.gest3d.model.Impresoras;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImpresorasRepository extends JpaRepository< Impresoras, Long>{

    Optional<Impresoras> findImpresorasByNombre(String nombre);

}
