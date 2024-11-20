package com.impresion3d.gest3d.controller;

import com.impresion3d.gest3d.model.*;
import com.impresion3d.gest3d.service.ImpresionesService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.impresion3d.gest3d.service.PiezasService;
import com.impresion3d.gest3d.service.RollosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
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




    // ########################################
    @GetMapping("/{id}") // Mediante API identificar a traves de su id
    public Optional<Impresion> getById (@PathVariable ("id") Long id){
        return impresionesService.getImpresiones(id);
    }

    @DeleteMapping("/{id}") // Mediante API Borrar a traves de su id
    public void delete(@PathVariable("id") Long id){
        impresionesService.delete(id);
    }
    // ########################################

    @GetMapping({"", "/"})
    public String mostrarImpresion (Model model){ // Mediante este Get, podremos visualizar cada uno de los models en el index "Impresiones"
        List <Impresion> impresion = impresionesService.getImpresiones();
        model.addAttribute("impresion", impresion);
        List<Rollo> rollo = rollosService.getRollos();
        model.addAttribute("rollos", rollo);
        List<Pieza> pieza = piezasService.getPiezas();
        model.addAttribute("piezas", pieza);
        return "impresiones/index.html";
    }

    // ###################      CREATE       ##############################################
    @GetMapping("/create")
    public String MostrarPagCrearImpresion (Model model){ // Mediante este Get, podremos visualizar cada uno de los models en el index "Impresiones"

        ImpresionDTO impresionDTO = new ImpresionDTO();
        //impresionDTO.setFechaImpresion(LocalDateTime.now());
        model.addAttribute("impresionDTO", impresionDTO);
        List<Rollo> rollos = rollosService.getRollos();
        model.addAttribute("rollo", rollos);
        List<Pieza> piezas = piezasService.getPiezas();
        model.addAttribute("pieza", piezas);
        return "/impresiones/crearImpresion.html";

    }

    @PostMapping("/create")
    public String crearImpresion(@ModelAttribute ImpresionDTO impresionDTO, BindingResult resultado) {
        if (resultado.hasErrors()) {
            return "/impresiones/crearImpresion.html";
        }

        Impresion impresion = new Impresion();
        impresion.setNombre(impresionDTO.getNombre());
        impresion.setTiempo(impresionDTO.getTiempo());
        impresion.setPeso(impresionDTO.getPeso());
        impresion.setCosto_kwh(impresionDTO.getCosto_kwh());
        impresion.setCosto_pieza(impresionDTO.getCosto_pieza());



        Optional<Pieza> pieza = piezasService.getPiezas(impresionDTO.getPieza());
        if (pieza.isPresent()) {
            impresion.setPieza(pieza.get());
        } else {
            throw new RuntimeException("Pieza no encontrada");
        }
        Optional<Rollo> rollo = rollosService.getRollos(impresionDTO.getRollo());
        if (rollo.isPresent()) {
            impresion.setRollo(rollo.get());
        } else {
            throw new RuntimeException("Rollos no encontrado");
        }

        impresionesService.create(impresion);

        return "redirect:/impresiones";
    }
// ###################      CREATE     ##############################################

// ###################      EDITAR       ##############################################

    @GetMapping("/edit")
    public String mostrarPaginaEditar(Model model, @RequestParam("id") long id) {

        try {
            Impresion impresion = impresionesService.getImpresiones(id).get();
            ImpresionDTO impresionDTO = new ImpresionDTO();
            // Asignar valores a DTO
            impresionDTO.setNombre(impresion.getNombre());
            impresionDTO.setTiempo(impresion.getTiempo());
            impresionDTO.setPeso(impresion.getPeso());
            impresionDTO.setCosto_kwh(impresion.getCosto_kwh());
            impresionDTO.setCosto_pieza(impresion.getCosto_pieza());
          //  impresionDTO.setFechaImpresion(LocalDateTime.now());

            model.addAttribute("impresionDTO", impresionDTO);
            model.addAttribute("id", id);
            model.addAttribute("rollo", rollosService.getRollos());
            model.addAttribute("pieza", piezasService.getPiezas());

        } catch (Exception e) {
            System.out.println("Excepción: " + e.getMessage());
            return "redirect:/impresiones";
        }

        return "impresiones/editarImpresion";
    }

    @PostMapping("/edit")
    public String actualizarImpresion(Model model, @RequestParam("id") long id,
                                      @Validated @ModelAttribute ImpresionDTO impresionDTO, BindingResult resultado){

        if (resultado.hasErrors()){
            return "impresiones/editarImpresion.html";
        }

        Impresion impresion = impresionesService.getImpresiones(id).orElseThrow(() -> new RuntimeException("Impresion no encontrada"));

        impresion.setNombre(impresionDTO.getNombre());
        impresion.setTiempo(impresionDTO.getTiempo());
        impresion.setPeso(impresionDTO.getPeso());
        impresion.setCosto_kwh(impresionDTO.getCosto_kwh());
        impresion.setCosto_pieza(impresionDTO.getCosto_pieza());
        //impresion.setFechaImpresion(LocalDateTime.now());

        Optional<Pieza> pieza = piezasService.getPiezas(impresionDTO.getPieza());
        if (pieza.isPresent()) {
            impresion.setPieza(pieza.get());
        } else {
            throw new RuntimeException("Pieza no encontrada");
        }
        Optional<Rollo> rollo = rollosService.getRollos(impresionDTO.getRollo());
        if (rollo.isPresent()) {
            impresion.setRollo(rollo.get());
        } else {
            throw new RuntimeException("Rollos no encontrado");
        }

        impresionesService.update(id,impresion);
        return "redirect:/impresiones";
    }
    // ###################      EDITAR       ##############################################





    @GetMapping("/delete")
    public String eliminarImpresion(@RequestParam("id") long id) {

        try {
            Impresion impresion = impresionesService.getImpresiones(id).orElseThrow(() -> new RuntimeException("Impresión no encontrada"));

            impresionesService.delete(impresion.getId());
        } catch (Exception e) {
            System.out.println("Excepción: " + e.getMessage());
        }

        return "redirect:/impresiones";

    }


}
