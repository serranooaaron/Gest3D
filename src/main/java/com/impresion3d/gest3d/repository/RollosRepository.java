package com.impresion3d.gest3d.repository;

import com.impresion3d.gest3d.model.Rollo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RollosRepository extends JpaRepository <Rollo, Long> {

    Optional<Rollo> findRollosByNombre(String nombre);
}
