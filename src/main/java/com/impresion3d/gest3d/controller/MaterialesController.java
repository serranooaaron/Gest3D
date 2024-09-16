package com.impresion3d.gest3d.controller;

import com.impresion3d.gest3d.model.*;
import com.impresion3d.gest3d.service.MaterialesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "materiales")
public class MaterialesController {

    @Autowired
    private MaterialesService materialesService;

    @GetMapping ("/api") // Obtener Lista materiales por api
    public List<Material> getAll (){
        return materialesService.getMateriales();
    }
    @GetMapping("/api/{id}")  // Obtener material por id api
    public Optional<Material> getById (@PathVariable("id") Long id){
        return materialesService.getMateriales(id);
    }

    @GetMapping({"","/"})
    public String mostrarPaginaMateriales(Model model){
        List<Material> materiales = materialesService.getMateriales();
        model.addAttribute("materiales", materiales);
        return "materiales/index";
    }

    @PostMapping("/api/create") // Create REST por API
    public void create(@RequestBody Material material){
        materialesService.create(material);
    }


    @PostMapping("api/createValidado")
    public void RegistrarMateriales(@RequestBody Material material){
        this.materialesService.createValidado(material);
    }

    @GetMapping("/create")
    public String mostrarPagCrear(Model model){
        MaterialDTO materialDTO = new MaterialDTO();
        model.addAttribute("materialDTO", materialDTO);
        return "/materiales/crearMaterial" ;
    }
    @PostMapping("/create")
    public String crearMaterial(@ModelAttribute @Validated MaterialDTO materialDTO, BindingResult r){
        if (r.hasErrors()){ return "/materiales/crearMaterial";}
        Material material = new Material();


        materialDTO.setNombre(material.getNombre());
        materialDTO.setTipoUso(material.getTipoUso());
        materialDTO.setDescripcion(material.getDescripcion());
        materialDTO.setMetros_k(material.getMetros_k());
        materialDTO.setResistencia(material.getResistencia());

        materialesService.create(material);

        return "redirect:/materiales";
    }

    @PostMapping("/edit")
    public String mostrarPagEditar(Model model, @RequestParam long id){
        try {
            Material material = materialesService.getMateriales(id).orElseThrow(() -> new RuntimeException("Material no encontrado"));
            MaterialDTO materialDTO = new MaterialDTO();
            // Asignar valores a DTO
            materialDTO.setNombre(material.getNombre());
            materialDTO.setTipoUso(material.getTipoUso());
            materialDTO.setDescripcion(material.getDescripcion());
            materialDTO.setResistencia(material.getResistencia());
            materialDTO.setMetros_k(material.getMetros_k());
            model.addAttribute("materialDTO", materialDTO);
        } catch (Exception e) {
            System.out.println("Excepción: " + e.getMessage());
            return "redirect:/materiales";
        }
        return "materiales/editarMaterial";
    }


    @PostMapping("/edit/{id}")
    public String actualizarMaterial(Model model, @ModelAttribute MaterialDTO materialDTO, @RequestParam long id, BindingResult resultado){

        if(resultado.hasErrors()){return "materiales/editarMaterial";}
        Material material = materialesService.getMateriales(id).orElseThrow(() -> new RuntimeException("Impresora No encontrada"));

        material.setNombre(materialDTO.getNombre());
        material.setTipoUso(materialDTO.getTipoUso());
        material.setDescripcion(materialDTO.getDescripcion());
        material.setResistencia(materialDTO.getResistencia());
        material.setMetros_k(materialDTO.getMetros_k());;

        materialesService.updateValidado(material);
        return "redirect:/materiales";
    }


    @DeleteMapping("/api/{id}") // Delete por API
    public void delete(@PathVariable("id") Long id){
        materialesService.delete(id);
    }

    @GetMapping("/delete")
    public String eliminarMaterial(long id) {
        try {
            Material material = materialesService.getMateriales(id).get();
            materialesService.delete(material.getId());
        } catch (Exception e) {
            System.out.println("Excepción: " + e.getMessage());
        }
        return "redirect:/materiales";
    }

}
