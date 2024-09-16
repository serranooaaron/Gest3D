package com.impresion3d.gest3d.service;

import com.impresion3d.gest3d.model.Pieza;
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


    public List<Pieza> getPiezas() {
        return piezasRepository.findAll();
    }


    public Optional<Pieza> getPiezas(Long id) {
        return piezasRepository.findById(id);
    }


    public void create(Pieza pieza) { piezasRepository.save(pieza); }

    public Pieza update(Long id, Pieza pieza) {
        if (piezasRepository.existsById(id)) {
            return piezasRepository.save(pieza);
        }
        return null;
    }

    public void createValidado(Pieza pieza) {  //Se genera valida la busqueda de impresiones.

        Optional<Pieza> res = piezasRepository.findPiezasByNombre(pieza.getNombre());
        if (res.isPresent()) {
            throw new IllegalStateException("Ya existe el producto.");
        }
        piezasRepository.save(pieza);
    }


    public void delete(Long id) {
        piezasRepository.deleteById(id);
    }
}
