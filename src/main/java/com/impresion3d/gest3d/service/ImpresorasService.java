package com.impresion3d.gest3d.service;

import com.impresion3d.gest3d.model.Material;
import com.impresion3d.gest3d.repository.ImpresorasRepository;
import com.impresion3d.gest3d.model.Impresora;
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

    public List<Impresora> getImpresoras() {
        return impresorasRepository.findAll();
    }


    public Optional<Impresora> getImpresoras(Long id) {
        return impresorasRepository.findById(id);
    }


    public void create(Impresora impresora) { impresorasRepository.save(impresora); }

    public Impresora update(Long id, Impresora impresora) {
        if (impresorasRepository.existsById(id)) {
            return impresorasRepository.save(impresora);
        }
        return null;
    }

    public void createValidado(Impresora impresora) {  //Se genera validación la busqueda de impresiones.

        Optional<Impresora> res = impresorasRepository.findImpresorasByNombre(impresora.getNombre());
        if (res.isPresent()) {
            throw new IllegalStateException("Ya existe el producto.");
        }
        impresorasRepository.save(impresora);
    }


    public void delete(Long id) {
        impresorasRepository.deleteById(id);
    }
}
