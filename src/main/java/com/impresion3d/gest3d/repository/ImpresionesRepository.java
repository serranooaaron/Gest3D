package com.impresion3d.gest3d.repository;

import com.impresion3d.gest3d.model.Impresion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImpresionesRepository extends JpaRepository< Impresion, Long>{
    
    Optional<Impresion> findImpresionesByNombre(String nombre);
    
}
