package com.impresion3d.gest3d.controller;

import com.impresion3d.gest3d.model.Impresiones;
import com.impresion3d.gest3d.model.Piezas;
import com.impresion3d.gest3d.service.PiezasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "piezas")
public class PiezasController {
    @Autowired
    private PiezasService piezasService;

    @GetMapping
    public List<Piezas> getAll (){
        return piezasService.getPiezas();
    }

    @GetMapping("/{id}")
    public Optional<Piezas> getById (@PathVariable("id") Long id){
        return piezasService.getPiezas(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        piezasService.delete(id);
    }

    @PostMapping
    public void create(@RequestBody Piezas piezas){
        piezasService.create(piezas);
    }


    @PostMapping("/createValidado")
    public void RegistrarPiezas(@RequestBody Piezas piezas){
        this.piezasService.createValidado(piezas);
    }






}
