package com.impresion3d.gest3d.controller;

import com.impresion3d.gest3d.model.Impresiones;
import com.impresion3d.gest3d.model.Rollos;
import com.impresion3d.gest3d.service.RollosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "rollos")
public class RollosController {

    @Autowired
    private RollosService rollosService;

    @GetMapping
    public List<Rollos> getAll (){
        return RollosService.getRollos();
    }

    @GetMapping("/{id}")
    public Optional<Rollos> getById (@PathVariable("id") Long id){
        return RollosService.getRollos(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        RollosService.delete(id);
    }

    @PostMapping
    public void create(@RequestBody Rollos rollos){
        RollosService.create(rollos);
    }


    @PostMapping("/createValidado")
    public void RegistrarImpresiones(@RequestBody Impresiones impresiones){
        this.impresionesService.createValidado(impresiones);
    }





}
