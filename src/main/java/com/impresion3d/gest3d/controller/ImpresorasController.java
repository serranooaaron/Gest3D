package com.impresion3d.gest3d.controller;


import com.impresion3d.gest3d.model.*;
import com.impresion3d.gest3d.service.ImpresorasService;
import com.impresion3d.gest3d.service.PiezasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

 // Cuando se utiliza @Controller, es para vistas HTML - En cambio @RestController es para APIREST, devolver
 // Tipo JSON o XML
@Controller
@RequestMapping("/impresoras")
public class ImpresorasController {

    @Autowired
    private ImpresorasService impresorasService;

    @Autowired
    private PiezasService piezasService;

    @GetMapping("/api")
    public List<Impresora> getAll (){ // API VIEW
        return impresorasService.getImpresoras();
    }
    @GetMapping("/api/{id}") // API VIEW BY ID
    public Optional<Impresora> getById (@PathVariable("id") Long id){
        return impresorasService.getImpresoras(id);
    }
    @GetMapping // HTML View
    public String MostrarImpresoras(Model model){
        List<Impresora> impresoras = impresorasService.getImpresoras();
        List<Pieza> piezas = piezasService.getPiezas();
        model.addAttribute("piezas", piezas);
        model.addAttribute("impresoras", impresoras);
        return "impresoras/index";
    }

     @GetMapping("/create")
     public String MostrarPagCrearImpresora (Model model){ // Mediante este Get, podremos visualizar cada uno de los models en el index "Impresiones"

         ImpresoraDTO impresoraDTO = new ImpresoraDTO();
         model.addAttribute("impresoraDTO", impresoraDTO);
         List<Pieza> piezas = piezasService.getPiezas();
         model.addAttribute("piezas", piezas);
         return "/impresoras/crearImpresora.html";

     }

    @PostMapping("/create")
    public String Create(@ModelAttribute ImpresoraDTO impresoraDTO, BindingResult resultado){

        if(resultado.hasErrors()){ return "impresoras/crearImpresora";}

        Impresora impresora = new Impresora();
        impresora.setNombre(impresoraDTO.getNombre());
        impresora.setMarca(impresoraDTO.getMarca());
        impresora.setAlto_imp(impresoraDTO.getAlto_imp());
        impresora.setAncho_imp(impresoraDTO.getAncho_imp());
        impresora.setLargo_imp(impresoraDTO.getLargo_imp());
        impresora.setModelo(impresoraDTO.getModelo());
        impresora.setHoras_uso(impresoraDTO.getHoras_uso());
        impresora.setConsumo_hora(impresoraDTO.getConsumo_hora());
        impresora.setUltimo_mantenimiento(impresoraDTO.getUltimo_mantenimiento());

        impresorasService.createValidado(impresora);
        return "redirect:/impresoras";
    }

    @PostMapping("/api") //API CREATE
    public void create(@RequestBody Impresora impresora){
        impresorasService.create(impresora);
    }

//
//    @PostMapping("/createValidado")
//    public void RegistrarImpresoras(@RequestBody Impresora impresora){
//        this.impresorasService.createValidado(impresora);
//    }





    @PostMapping("/edit")
    public String mostrarPagEditar(Model model, @RequestParam long id){

        try {
            Impresora impresora = impresorasService.getImpresoras(id).get();
            model.addAttribute("impresora",impresora);
            ImpresoraDTO impresoraDTO = new ImpresoraDTO();

            impresora.setNombre(impresoraDTO.getNombre());
            impresora.setMarca(impresoraDTO.getMarca());
            impresora.setAlto_imp(impresoraDTO.getAlto_imp());
            impresora.setAncho_imp(impresoraDTO.getAncho_imp());
            impresora.setLargo_imp(impresoraDTO.getLargo_imp());
            impresora.setModelo(impresoraDTO.getModelo());
            impresora.setHoras_uso(impresoraDTO.getHoras_uso());
            impresora.setConsumo_hora(impresoraDTO.getConsumo_hora());

            model.addAttribute("impresoraDTO", impresoraDTO);
        }catch (Exception e){
            System.out.println("Excepcion: " + e.getMessage());
            return "redirect:/impresoras";
        }
        return "impresoras/editarImpresora";
    }

     @PostMapping("/edit/{id}")
     public String actualizarImpresora(Model model, @ModelAttribute ImpresoraDTO impresoraDTO, @RequestParam long id, BindingResult resultado){

         if(resultado.hasErrors()){return "impresoras/editarImpresora";}
         Impresora impresora = impresorasService.getImpresoras(id).orElseThrow(() -> new RuntimeException("Impresora No encontrada"));

         impresora.setNombre(impresoraDTO.getNombre());
         impresora.setMarca(impresoraDTO.getMarca());
         impresora.setAlto_imp(impresoraDTO.getAlto_imp());
         impresora.setAncho_imp(impresoraDTO.getAncho_imp());
         impresora.setLargo_imp(impresoraDTO.getLargo_imp());
         impresora.setModelo(impresoraDTO.getModelo());
         impresora.setHoras_uso(impresoraDTO.getHoras_uso());
         impresora.setConsumo_hora(impresoraDTO.getConsumo_hora());

         impresorasService.createValidado(impresora);
         return "redirect:/impresoras";
     }


    @DeleteMapping("/api/{id}") // API DELETE
    public void delete(@PathVariable("id") Long id){
        impresorasService.delete(id);
    }

    @GetMapping("/delete")
    public String eliminarImpresora(@RequestParam Long id){
        try{
            Impresora impresora = impresorasService.getImpresoras(id).get();
            impresorasService.delete(impresora.getId());
        } catch(Exception e){
            System.out.println("Excepcion: " + e.getMessage());
        }
        return "redirect:/impresoras";
    }

}
