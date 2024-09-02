package com.impresion3d.gest3d.controller;

import com.impresion3d.gest3d.model.Impresiones;
import com.impresion3d.gest3d.repository.PiezasRepository;
import com.impresion3d.gest3d.service.ImpresionesService;
import java.util.List;
import java.util.Optional;

import com.impresion3d.gest3d.service.RollosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "impresiones")
public class ImpresionesController{

    @Autowired
    private ImpresionesService impresionesService;

    @Autowired
    private RollosService rollosService;

    @Autowired
    private PiezasRepository piezasRepository;

    @GetMapping
    public List<Impresiones> getAll (){
       return impresionesService.getImpresiones();
    }

    @GetMapping("/{id}")
    public Optional<Impresiones> getById (@PathVariable ("id") Long id){
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
