package com.impresion3d.gest3d.controller;

import com.impresion3d.gest3d.model.*;
import com.impresion3d.gest3d.service.ImpresionesService;
import java.util.List;
import java.util.Optional;
import com.impresion3d.gest3d.service.PiezasService;
import com.impresion3d.gest3d.service.RollosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/impresiones")
public class ImpresionesController{

    @Autowired
    private ImpresionesService impresionesService;

    @Autowired
    private RollosService rollosService;

    @Autowired
    private PiezasService piezasService;

    @GetMapping({"","/"})
    public String getAll (Model model){ // Mediante este Get, podremos visualizar cada uno de los models en el index "Impresiones"
        List<Impresion> impresiones = impresionesService.getImpresiones();
        model.addAttribute("impresiones", impresiones);
        List<Rollo> rollo = rollosService.getRollos();
        model.addAttribute("rollos", rollo);
        List<Pieza> pieza = piezasService.getPiezas();
        model.addAttribute("piezas", pieza);
        return "impresiones/index";
    }
    @GetMapping("/create")
    public String MostrarPagCrearImpresion (Model model){ // Mediante este Get, podremos visualizar cada uno de los models en el index "Impresiones"

        ImpresionDTO impresionDTO = new ImpresionDTO();
        model.addAttribute("impresionDTO", impresionDTO);
        List<Rollo> rollos = rollosService.getRollos();
        model.addAttribute("rollos", rollos);
        List<Pieza> piezas = piezasService.getPiezas();
        model.addAttribute("piezas", piezas);
        return "/impresiones/crearImpresion.html";

    }

    @PostMapping("/create")
    public String create(@ModelAttribute @Validated ImpresionDTO impresionDTO, BindingResult resultado, Model model) {
        if (resultado.hasErrors()) {
            List<Rollo> rollos = rollosService.getRollos();
            List<Pieza> piezas = piezasService.getPiezas();
            model.addAttribute("rollos", rollos);
            model.addAttribute("piezas", piezas);
            return "/impresiones/crearImpresion.html";
        }
        Impresion impresion = new Impresion();
        Optional<Rollo> rollo = rollosService.getRollos(impresionDTO.getRollo());
        Optional<Pieza> pieza = piezasService.getPiezas(impresionDTO.getPieza());

        if (rollo.isPresent() && pieza.isPresent()) {
            impresion.setRollo(rollo.get());
            impresion.setPieza(pieza.get());
            // Set other fields
            impresionesService.createValidado(impresion);
            return "redirect:/impresiones";
        }
        // Handle the case where rollo or pieza is not found
        return "redirect:/impresiones/create";
    }
    //    @PostMapping("/createValidado")
//    public void RegistrarImpresiones(@RequestBody Impresiones impresiones){
//        this.impresionesService.createValidado(impresiones);
//    }

    @GetMapping("/{id}") // Mediante API identificar a traves de su id
    public Optional<Impresion> getById (@PathVariable ("id") Long id){
        return impresionesService.getImpresiones(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        impresionesService.delete(id);
    }

    @GetMapping("/edit")
    public String mostrarPagEditar(Model model, @RequestParam long id) {
        try {
            Impresion impresion = impresionesService.getImpresiones(id).orElseThrow(() -> new RuntimeException("Impresion no encontrado"));
            ImpresionDTO impresionDTO = new ImpresionDTO();

            // Asignar valores a DTO
            impresionDTO.setNombre(impresion.getNombre());
            impresionDTO.setPeso(impresion.getPeso());
            impresionDTO.setCosto_pieza(impresion.getCosto_pieza());
            impresionDTO.setTiempo(impresion.getTiempo());
            impresionDTO.setCosto_kwh(impresion.getCosto_kwh());
            impresionDTO.setFechaImpresion(impresion.getFechaImpresion());

            model.addAttribute("impresionDTO", impresionDTO);
            model.addAttribute("id", id);  // Agregar el ID al modelo para usarlo en el formulario
        } catch (Exception e) {
            System.out.println("Excepción: " + e.getMessage());
            return "redirect:/impresiones";
        }
        return "/impresiones/editarImpresion";
    }

    @PostMapping("/edit")
    public String actualizarImpresion(Model model, @RequestParam long id,
                                      @Validated @ModelAttribute ImpresionDTO impresionDTO, BindingResult resultado){
        if (resultado.hasErrors()){
            List <Rollo> rollo = rollosService.getRollos();
            model.addAttribute("rollo",rollo);
            List <Pieza> pieza = piezasService.getPiezas();
            model.addAttribute("pieza",pieza);
            return "impresiones/editarImpresion.html";
        }

        try{
            Impresion impresion = impresionesService.getImpresiones(id).orElseThrow(() -> new RuntimeException("Impresion no encontrada"));
            Pieza pieza = piezasService.getPiezas(impresionDTO.getPieza()).orElseThrow(() -> new RuntimeException("Pieza no encontrada"));
            Rollo rollo = rollosService.getRollos(impresionDTO.getRollo()).orElseThrow(() -> new RuntimeException("Rollo no encontrado"));

            impresion.setPieza(pieza);
            impresion.setRollo(rollo);
            impresion.setTiempo(impresionDTO.getTiempo());
            impresion.setPeso(impresionDTO.getPeso());
            impresion.setCosto_kwh(impresionDTO.getCosto_kwh());
            impresion.setCosto_pieza(impresionDTO.getCosto_pieza());

            impresionesService.createValidado(impresion);
        } catch (Exception e){
            System.out.println("Excepción: " + e.getMessage());
            return "redirect:/impresiones";
        }
        return "redirect:/impresiones";
    }

    @GetMapping("/delete")
    public String eliminarImpresion(@RequestParam long id) {

        try {
            Impresion impresion = impresionesService.getImpresiones(id).orElseThrow(() -> new RuntimeException("Impresión no encontrada"));

            impresionesService.delete(impresion.getId());
        } catch (Exception e) {
            System.out.println("Excepción: " + e.getMessage());
        }

        return "redirect:/impresiones";

    }


}
