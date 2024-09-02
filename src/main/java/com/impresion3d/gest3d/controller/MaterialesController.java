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
    public List<Impresiones> getAll (){
        return impresionesService.getImpresiones();
    }

    @GetMapping("/{id}")
    public Optional<Impresiones> getById (@PathVariable("id") Long id){
        return impresionesService.getImpresiones(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        impresionesService.delete(id);
    }

    @PostMapping
    public void create(@RequestBody Impresiones impresiones){
        impresionesService.create(impresiones);
    }


    @PostMapping("/createValidado")
    public void RegistrarImpresiones(@RequestBody Impresiones impresiones){
        this.impresionesService.createValidado(impresiones);
    }





}
