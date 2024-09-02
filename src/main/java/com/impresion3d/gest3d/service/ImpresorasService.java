package com.impresion3d.gest3d.service;

import com.impresion3d.gest3d.model.Materiales;
import com.impresion3d.gest3d.repository.ImpresorasRepository;
import com.impresion3d.gest3d.model.Impresoras;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImpresorasService{
    @Autowired
    private final ImpresorasRepository impresorasRepository;

    @Autowired
    public ImpresorasService(ImpresorasRepository impresorasRepository) {
        this.impresorasRepository = impresorasRepository;
    }

    public List<Impresoras> getImpresoras() {
        return impresorasRepository.findAll();
    }


    public Optional<Impresoras> getImpresoras(Long id) {
        return impresorasRepository.findById(id);
    }


    public void create(Impresoras impresoras) { impresorasRepository.save(impresoras); }

    public Impresoras update(Long id, Impresoras impresoras) {
        if (impresorasRepository.existsById(id)) {
            return impresorasRepository.save(impresoras);
        }
        return null;
    }

    public void createValidado(Impresoras impresoras) {  //Se genera validación la busqueda de impresiones.

        Optional<Impresoras> res = impresorasRepository.findImpresorasByNombre(impresoras.getNombre());
        if (res.isPresent()) {
            throw new IllegalStateException("Ya existe el producto.");
        }
        impresorasRepository.save(impresoras);
    }


    public void delete(Long id) {
        impresorasRepository.deleteById(id);
    }
}
