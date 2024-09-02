package com.impresion3d.gest3d.controller;

import com.impresion3d.gest3d.model.Impresiones;
import com.impresion3d.gest3d.model.Materiales;
import com.impresion3d.gest3d.service.MaterialesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "materiales")
public class MaterialesController {

    @Autowired
    private MaterialesService materialesService;

    @GetMapping
    public List<Materiales> getAll (){
        return materialesService.getMateriales();
    }

    @GetMapping("/{id}")
    public Optional<Materiales> getById (@PathVariable("id") Long id){
        return materialesService.getMateriales(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        materialesService.delete(id);
    }

    @PostMapping
    public void create(@RequestBody Materiales materiales){
        materialesService.create(materiales);
    }


    @PostMapping("/createValidado")
    public void RegistrarMateriales(@RequestBody Materiales materiales){
        this.materialesService.createValidado(materiales);
    }





}
