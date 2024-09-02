package com.impresion3d.gest3d.service;

import com.impresion3d.gest3d.model.Piezas;
import com.impresion3d.gest3d.model.Rollos;
import com.impresion3d.gest3d.repository.PiezasRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PiezasService  {
     @Autowired
    private final PiezasRepository piezasRepository;

     @Autowired
    public PiezasService(PiezasRepository piezasRepository) {
        this.piezasRepository = piezasRepository;
    }


    public List<Piezas> getPiezas() {
        return piezasRepository.findAll();
    }


    public Optional<Piezas> getPiezas(Long id) {
        return piezasRepository.findById(id);
    }


    public void create(Piezas piezas) { piezasRepository.save(piezas); }

    public Piezas update(Long id, Piezas piezas) {
        if (piezasRepository.existsById(id)) {
            return piezasRepository.save(piezas);
        }
        return null;
    }

    public void createValidado(Piezas piezas) {  //Se genera valida la busqueda de impresiones.

        Optional<Piezas> res = piezasRepository.findPiezasByNombre(piezas.getNombre());
        if (res.isPresent()) {
            throw new IllegalStateException("Ya existe el producto.");
        }
        piezasRepository.save(piezas);
    }


    public void delete(Long id) {
        piezasRepository.deleteById(id);
    }
}
