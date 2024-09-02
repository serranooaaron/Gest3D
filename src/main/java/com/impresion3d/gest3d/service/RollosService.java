package com.impresion3d.gest3d.service;

import com.impresion3d.gest3d.model.Impresiones;
import com.impresion3d.gest3d.model.Rollos;
import com.impresion3d.gest3d.repository.ImpresionesRepository;
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



    public List<Rollos> getRollos() {
        return rollosRepository.findAll();
    }


    public Optional<Rollos> getRollos(Long id) {
        return rollosRepository.findById(id);
    }


    public void create(Rollos rollos) { rollosRepository.save(rollos); }

    public Rollos update(Long id, Rollos rollos) {
        if (rollosRepository.existsById(id)) {
            return rollosRepository.save(rollos);
        }
        return null;
    }

    public void createValidado(Rollos rollos) {  //Se genera valida la busqueda de impresiones.

        Optional<Rollos> res = rollosRepository.findRollosByNombre(rollos.getNombre());
        if (res.isPresent()) {
            throw new IllegalStateException("Ya existe el producto.");
        }
        rollosRepository.save(rollos);
    }


    public void delete(Long id) {
        rollosRepository.deleteById(id);
    }

}
