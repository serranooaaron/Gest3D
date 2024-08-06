package com.impresion3d.gest3d.service;

import com.impresion3d.gest3d.model.Impresiones;
import com.impresion3d.gest3d.repository.ImpresionesRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImpresionesService extends GenericService <Impresiones, Long>{
    
    @Autowired
    private final ImpresionesRepository impresionesRepository;

    @Autowired
    public ImpresionesService(ImpresionesRepository impresionesRepository) {
        this.impresionesRepository = impresionesRepository;
    }

    @Override
    public List<Impresiones> getAll() {
        return impresionesRepository.findAll();
    }

    @Override
    public Impresiones getById(Long id) {
        return impresionesRepository.findById(id).orElse(null);
    }

    @Override
    public Impresiones create(Impresiones impresiones) {
        return impresionesRepository.save(impresiones);
    }

    @Override
    public Impresiones update(Long id, Impresiones impresiones) {
        if (impresionesRepository.existsById(id)) {
            return impresionesRepository.save(impresiones);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        impresionesRepository.deleteById(id);
    }
    

    public void newImpresiones(Impresiones impresiones) {
       
        Optional<Impresiones> res = impresionesRepository.findImpresionesByNombre(impresiones.getNombre());
        if (res.isPresent()) {
            throw new IllegalStateException("Ya existe el producto.");
        }
        impresionesRepository.save(impresiones); 
    }

}
