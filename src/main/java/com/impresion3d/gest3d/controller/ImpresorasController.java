package com.impresion3d.gest3d.controller;

import com.impresion3d.gest3d.model.Impresiones;
import com.impresion3d.gest3d.model.Impresoras;
import com.impresion3d.gest3d.service.ImpresorasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "impresoras")
public class ImpresorasController {

    @Autowired
    private ImpresorasService impresorasService;

    @GetMapping
    public List<Impresoras> getAll (){
        return impresorasService.getImpresoras();
    }

    @GetMapping("/{id}")
    public Optional<Impresoras> getById (@PathVariable("id") Long id){
        return impresorasService.getImpresoras(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        impresorasService.delete(id);
    }

    @PostMapping
    public void create(@RequestBody Impresoras impresoras){
        impresorasService.create(impresoras);
    }


    @PostMapping("/createValidado")
    public void RegistrarImpresoras(@RequestBody Impresoras impresoras){
        this.impresorasService.createValidado(impresoras);
    }





}
