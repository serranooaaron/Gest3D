package com.impresion3d.gest3d.controller;

import com.impresion3d.gest3d.model.Impresion;
import com.impresion3d.gest3d.model.Rollo;
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
    public List<Rollo> getAll (){
        return rollosService.getRollos();
    }

    @GetMapping("/{id}")
    public Optional<Rollo> getById (@PathVariable("id") Long id){
        return rollosService.getRollos(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        rollosService.delete(id);
    }

    @PostMapping
    public void create(@RequestBody Rollo rollo){
        rollosService.create(rollo);
    }


    @PostMapping("/createValidado")
    public void RegistrarRollos(@RequestBody Rollo rollo){
        this.rollosService.createValidado(rollo);
    }





}
