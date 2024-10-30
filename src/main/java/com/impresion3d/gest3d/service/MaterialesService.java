package com.impresion3d.gest3d.service;

import com.impresion3d.gest3d.model.Material;
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

    public List<Material> getMateriales() {
        return materialesRepository.findAll();
    }


    public Optional<Material> getMateriales(Long id) {
        return materialesRepository.findById(id);
    }


    public void create(Material material) { materialesRepository.save(material); }

    public Material update(Long id, Material material) {
        if (materialesRepository.existsById(id)) {
            return materialesRepository.save(material);
        }
        return null;
    }

    public void createValidado(Material material) {  //Se genera validación la busqueda de impresiones.

        Optional<Material> res = materialesRepository.findMaterialesByNombre(material.getNombre());
        if (res.isPresent()) {
            throw new IllegalStateException("Ya existe el producto.");
        }
        materialesRepository.save(material);
    }
    public void updateValidado(Material material) {
        // Verifica si el material con el mismo nombre ya existe y no es el mismo que el que estamos actualizando
        Optional<Material> res = materialesRepository.findMaterialesByNombre(material.getNombre());

        if (res.isPresent() && !res.get().getId().equals(material.getId())) {
            throw new IllegalStateException("Ya existe un producto con ese nombre.");
        }

        // Actualiza el material si la validación pasa
        materialesRepository.save(material);
    }

    public void delete(Long id) {
        materialesRepository.deleteById(id);
    }
}
