package com.impresion3d.gest3d.service;

import com.impresion3d.gest3d.model.Impresion;
import com.impresion3d.gest3d.repository.ImpresionesRepository;
import java.util.List;
import java.util.Optional;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImpresionesService{
    
    @Autowired
    private final ImpresionesRepository impresionesRepository;

    @Autowired
    public ImpresionesService(ImpresionesRepository impresionesRepository) {
        this.impresionesRepository = impresionesRepository;
    }


    public List<Impresion> getImpresiones() {
        return impresionesRepository.findAll();
    }


    public Optional<Impresion> getImpresiones(Long id) {
       return impresionesRepository.findById(id);
    }


    public void create(Impresion impresiones){ impresionesRepository.save(impresiones); }

    public Impresion update(Long id, Impresion impresion) {
        if (impresionesRepository.existsById(id)) {
            return impresionesRepository.save(impresion);
        }
        return null;
    }

    public void createValidado(Impresion impresion) {  //Se genera valida la busqueda de impresiones.

        Optional<Impresion> res = impresionesRepository.findImpresionesByNombre(impresion.getNombre());
        if (res.isPresent()) {
            throw new IllegalStateException("Ya existe el producto.");
        }
        impresionesRepository.save(impresion);
    }


    public void delete(Long id) {
        impresionesRepository.deleteById(id);
    }

}
