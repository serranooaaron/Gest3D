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
        return rollosService.getRollos();
    }

    @GetMapping("/{id}")
    public Optional<Rollos> getById (@PathVariable("id") Long id){
        return rollosService.getRollos(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        rollosService.delete(id);
    }

    @PostMapping
    public void create(@RequestBody Rollos rollos){
        rollosService.create(rollos);
    }


    @PostMapping("/createValidado")
    public void RegistrarRollos(@RequestBody Rollos rollos){
        this.rollosService.createValidado(rollos);
    }





}
