package com.impresion3d.gest3d.service;

import com.impresion3d.gest3d.model.Rollos;
import com.impresion3d.gest3d.repository.RollosRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RollosService extends GenericService <Rollos, Long> {
    @Autowired
    private final RollosRepository rollosRepository;
    
    @Autowired
    public RollosService(RollosRepository rollosRepository) {
        this.rollosRepository = rollosRepository;
    }
    
    @Override
    public List<Rollos> getAll() {
        return rollosRepository.findAll();
    }

    @Override
    public Rollos getById(Long id) {
        return rollosRepository.findById(id).orElse(null);
    }

    @Override
    public Rollos create(Rollos rollos) {
        return rollosRepository.save(rollos);
    }

    @Override
    public Rollos update(Long id, Rollos rollos ) {
        if (rollosRepository.existsById(id)) {
            return rollosRepository.save(rollos);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        rollosRepository.deleteById(id);
    }
    
//    public void newRollos(Rollos rollos){
//    
//        Optional<Rollos> res = rollosRepository.findRollosByName(rollos.getNombre());
//        if(res.isPresent()){
//            throw new IllegalStateException("Ya existe el producto.");
//        }
//        rollosRepository.save(rollos);
//    }
    
}
