package com.impresion3d.gest3d.repository;

import com.impresion3d.gest3d.model.Materiales;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MaterialesRepository extends JpaRepository <Materiales, Long>{
    Optional<Materiales> findMaterialesByNombre(String nombre);

}
