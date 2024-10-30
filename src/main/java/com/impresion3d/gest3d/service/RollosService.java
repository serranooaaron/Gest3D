package com.impresion3d.gest3d.service;

import com.impresion3d.gest3d.model.Rollo;
import com.impresion3d.gest3d.repository.RollosRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RollosService  {
    @Autowired
    private final RollosRepository rollosRepository;
    
    @Autowired
    public RollosService(RollosRepository rollosRepository) {
        this.rollosRepository = rollosRepository;
    }



    public List<Rollo> getRollos() {
        return rollosRepository.findAll();
    }


    public Optional<Rollo> getRollos(Long id) {
        return rollosRepository.findById(id);
    }


    public void create(Rollo rollo) { rollosRepository.save(rollo); }

    public Rollo update(Long id, Rollo rollo) {
        if (rollosRepository.existsById(id)) {
            return rollosRepository.save(rollo);
        }
        return null;
    }

    public void createValidado(Rollo rollo) {  //Se genera valida la busqueda de impresiones.

        Optional<Rollo> res = rollosRepository.findRollosByNombre(rollo.getNombre());
        if (res.isPresent()) {
            throw new IllegalStateException("Ya existe el producto.");
        }
        rollosRepository.save(rollo);
    }


    public void delete(Long id) {
        rollosRepository.deleteById(id);
    }

}
