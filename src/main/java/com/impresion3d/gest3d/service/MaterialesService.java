package com.impresion3d.gest3d.service;

import com.impresion3d.gest3d.model.Materiales;
import com.impresion3d.gest3d.model.Piezas;
import com.impresion3d.gest3d.repository.MaterialesRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialesService {
    @Autowired
    private final MaterialesRepository materialesRepository;
    
    @Autowired
    public MaterialesService(MaterialesRepository materialesRepository) {
        this.materialesRepository = materialesRepository;
    }

    public List<Materiales> getMateriales() {
        return materialesRepository.findAll();
    }


    public Optional<Materiales> getMateriales(Long id) {
        return materialesRepository.findById(id);
    }


    public void create(Materiales materiales) { materialesRepository.save(materiales); }

    public Materiales update(Long id, Materiales materiales) {
        if (materialesRepository.existsById(id)) {
            return materialesRepository.save(materiales);
        }
        return null;
    }

    public void createValidado(Materiales materiales) {  //Se genera validación la busqueda de impresiones.

        Optional<Materiales> res = materialesRepository.findMaterialesByNombre(materiales.getNombre());
        if (res.isPresent()) {
            throw new IllegalStateException("Ya existe el producto.");
        }
        materialesRepository.save(materiales);
    }


    public void delete(Long id) {
        materialesRepository.deleteById(id);
    }
}
