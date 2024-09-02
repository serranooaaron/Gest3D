package com.impresion3d.gest3d.service;

import com.impresion3d.gest3d.model.Impresiones;
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


    public List<Impresiones> getImpresiones() {
        return impresionesRepository.findAll();
    }


    public Optional<Impresiones> getImpresiones(Long id) {
       return impresionesRepository.findById(id);
    }


    public void create(Impresiones impresiones){ impresionesRepository.save(impresiones); }

    public Impresiones update(Long id, Impresiones impresiones) {
        if (impresionesRepository.existsById(id)) {
            return impresionesRepository.save(impresiones);
        }
        return null;
    }

    public void createValidado(Impresiones impresiones) {  //Se genera valida la busqueda de impresiones.

        Optional<Impresiones> res = impresionesRepository.findImpresionesByNombre(impresiones.getNombre());
        if (res.isPresent()) {
            throw new IllegalStateException("Ya existe el producto.");
        }
        impresionesRepository.save(impresiones);
    }


    public void delete(Long id) {
        impresionesRepository.deleteById(id);
    }

}
