package com.impresion3d.gest3d.controller;

import com.impresion3d.gest3d.model.Impresion;
import com.impresion3d.gest3d.model.Impresora;
import com.impresion3d.gest3d.model.Pieza;
import com.impresion3d.gest3d.model.PiezaDTO;
import com.impresion3d.gest3d.service.ImpresorasService;
import com.impresion3d.gest3d.service.PiezasService;
import jakarta.annotation.PostConstruct;
import jakarta.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "piezas")
public class PiezasController {

    @Autowired
    private PiezasService piezasService;
    @Autowired
    private ImpresorasService impresorasService;

    @PostConstruct
    public void init() {
        System.out.println("ImpresorasService inyectado: " + impresorasService);
    }

    @GetMapping("/api") // Obtener Lista de piezas por API
    public List<Pieza> getAll() {
        return piezasService.getPiezas();
    }

    @GetMapping("/api/{id}") // Obtener pieza por ID API
    public Optional<Pieza> getById(@PathVariable("id") Long id) {
        return piezasService.getPiezas(id);
    }

    @GetMapping({"", "/"})
    public String mostrarPaginaPiezas(Model model) {
        List<Pieza> piezas = piezasService.getPiezas();
        model.addAttribute("piezas", piezas);
        List <Impresora> impresora = impresorasService.getImpresoras();
        model.addAttribute("impresora", impresora);

        return "piezas/index";
    }

    @PostMapping("/api/create") // Crear REST por API
    public void create(@RequestBody Pieza pieza) {
        piezasService.create(pieza);
    }

    @PostMapping("/api/createValidado")
    public void registrarPiezas(@RequestBody Pieza pieza) {
        this.piezasService.createValidado(pieza);
    }

    @GetMapping("/create")
    public String mostrarPagCrear(Model model) {
        PiezaDTO piezaDTO = new PiezaDTO();
        model.addAttribute("piezaDTO", piezaDTO);
        List <Impresora> impresora = impresorasService.getImpresoras();
        model.addAttribute("impresora", impresora);

        return "/piezas/crearPieza";
    }

    @PostMapping("/create")
    public String crearPieza(@ModelAttribute @Validated PiezaDTO piezaDTO, BindingResult r) {
        if (r.hasErrors()) {
            return "/piezas/crearPieza";
        }
        Pieza pieza = new Pieza();

        pieza.setNombre(piezaDTO.getNombre());
        pieza.setCalidad(piezaDTO.getCalidad());
        pieza.setArchivo_gcode(piezaDTO.getArchivo_gcode());

        Optional<Impresora> impresora = impresorasService.getImpresoras(piezaDTO.getImpresora());
        if (impresora.isPresent()) {
            pieza.setImpresora(impresora.get());
        } else {
            throw new RuntimeException("Impresora no encontrada");
        }

        piezasService.create(pieza);

        return "redirect:/piezas";
    }

    @GetMapping("/edit")
    public String mostrarPagEditar(Model model, @RequestParam("id") long id) {
        try {
            Pieza pieza = piezasService.getPiezas(id).orElseThrow(() -> new RuntimeException("Pieza no encontrada"));
            PiezaDTO piezaDTO = new PiezaDTO();

            // Asignar valores a DTO
            piezaDTO.setNombre(pieza.getNombre());
            piezaDTO.setCalidad(pieza.getCalidad());
            piezaDTO.setArchivo_gcode(pieza.getArchivo_gcode());



            model.addAttribute("piezaDTO", piezaDTO);
            model.addAttribute("id", id);
        } catch (Exception e) {
            System.out.println("Excepción: " + e.getMessage());
            return "redirect:/piezas";
        }
        return "piezas/editarPieza";
    }

    @PostMapping("/edit")
    public String actualizarPieza(Model model, @ModelAttribute PiezaDTO piezaDTO, @RequestParam("id") long id, BindingResult resultado) {
        if (resultado.hasErrors()) {
            return "piezas/editarPieza";
        }
        Pieza pieza = piezasService.getPiezas(id).orElseThrow(() -> new RuntimeException("Pieza no encontrada"));

        pieza.setNombre(piezaDTO.getNombre());
        pieza.setCalidad(piezaDTO.getCalidad());
        pieza.setArchivo_gcode(piezaDTO.getArchivo_gcode());


        piezasService.create(pieza);
        return "redirect:/piezas";
    }

    @DeleteMapping("/api/{id}") // Eliminar por API
    public void delete(@RequestParam("id") Long id) {
        piezasService.delete(id);
    }

    @GetMapping("/delete")
    public String eliminarPieza(@RequestParam("id")long id) {
        try {
            Pieza pieza = piezasService.getPiezas(id).get();
            piezasService.delete(pieza.getId());
        } catch (Exception e) {
            System.out.println("Excepción: " + e.getMessage());
        }
        return "redirect:/piezas";
    }
}
