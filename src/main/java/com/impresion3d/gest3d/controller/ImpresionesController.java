package com.impresion3d.gest3d.controller;

import com.impresion3d.gest3d.model.Impresiones;
import com.impresion3d.gest3d.service.ImpresionesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/impresiones")
public class ImpresionesController{

    private final ImpresionesService impresionesService;

    
    @Autowired
    public ImpresionesController(ImpresionesService impresionesService){
        this.impresionesService = impresionesService;
    }

    
    @GetMapping
    public List<Impresiones> getImpresiones(){
        return this.impresionesService.getAll();
    }
    
    @PostMapping
    public void registrarimpresiones(@RequestBody Impresiones impresiones){
        this.impresionesService.newImpresiones(impresiones);
    }
    
}
