package com.impresion3d.gest3d.service;

import com.impresion3d.gest3d.model.Piezas;
import com.impresion3d.gest3d.repository.PiezasRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PiezasService extends GenericService <Piezas, Long> {
     @Autowired
    private final PiezasRepository piezasRepository;

     @Autowired
    public PiezasService(PiezasRepository piezasRepository) {
        this.piezasRepository = piezasRepository;
    }

    @Override
    public List<Piezas> getAll() {
        return piezasRepository.findAll();
    }

    @Override
    public Piezas getById(Long id) {
        return piezasRepository.findById(id).orElse(null);
    }

    @Override
    public Piezas create(Piezas pieza) {
        return piezasRepository.save(pieza);
    }

    @Override
    public Piezas update(Long id, Piezas pieza) {
        if (piezasRepository.existsById(id)) {
            return piezasRepository.save(pieza);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        piezasRepository.deleteById(id);
    }
    
//   public void newPiezas(Piezas piezas) {
//    Optional<Piezas> res = piezasRepository.findPiezasByName(piezas.getNombre_pieza());
//    if (res.isPresent()) {
//        throw new IllegalStateException("Ya existe el producto.");
//    }
//    piezasRepository.save(piezas); 
//}    
}
