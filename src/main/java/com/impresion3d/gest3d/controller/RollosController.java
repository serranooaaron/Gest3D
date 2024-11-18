package com.impresion3d.gest3d.controller;



import com.impresion3d.gest3d.model.Material;
import com.impresion3d.gest3d.model.Rollo;
import com.impresion3d.gest3d.model.RolloDTO;
import com.impresion3d.gest3d.service.MaterialesService;
import com.impresion3d.gest3d.service.RollosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "rollos")
public class RollosController {

    @Autowired
    private RollosService rollosService;
    @Autowired
    private MaterialesService materialesService;

    @GetMapping("/api") // Obtener Lista de rollos por API
    public List<Rollo> getAll() {
        return rollosService.getRollos();
    }

    @GetMapping("/api/{id}") // Obtener rollo por ID API
    public Optional<Rollo> getById(@PathVariable("id") Long id) {
        return rollosService.getRollos(id);
    }

    @GetMapping({"", "/"})
    public String mostrarPaginaRollos(Model model) {
        List<Rollo> rollos = rollosService.getRollos();
        model.addAttribute("rollos", rollos);
        List<Material> materiales = materialesService.getMateriales();
        model.addAttribute("materiales", materiales);

        return "rollos/index";
    }

    @PostMapping("/api/create") // Crear REST por API
    public void create(@RequestBody Rollo rollo) {
        rollosService.create(rollo);
    }

    @PostMapping("/api/createValidado")
    public void registrarRollos(@RequestBody Rollo rollo) {
        this.rollosService.createValidado(rollo);
    }

    @GetMapping("/create")
    public String mostrarPagCrear(Model model) {
        RolloDTO rolloDTO = new RolloDTO();
        model.addAttribute("rolloDTO", rolloDTO);
        List<Material> materiales = materialesService.getMateriales();
        model.addAttribute("materiales", materiales);

        return "/rollos/crearRollo";
    }

    @PostMapping("/create")
    public String crearRollo(@ModelAttribute @Validated RolloDTO rolloDTO, BindingResult r) {
        if (r.hasErrors()) {
            return "/rollos/crearRollo";
        }
        Rollo rollo = new Rollo();

        rollo.setNombre(rolloDTO.getNombre());
        rollo.setColor(rolloDTO.getColor());
        rollo.setCosto(rolloDTO.getCosto());
        rollo.setPeso_gr(rolloDTO.getPeso_gr());

        Optional<Material> material = materialesService.getMateriales(rolloDTO.getMaterial());
        if (material.isPresent()) {
            rollo.setMaterial(material.get());
        } else {
            throw new RuntimeException("material no encontrada");
        }

        rollosService.create(rollo);

        return "redirect:/rollos";
    }

    @GetMapping("/edit")
    public String mostrarPagEditar(Model model, @RequestParam("id") long id) {
        try {
            Rollo rollo = rollosService.getRollos(id).orElseThrow(() -> new RuntimeException("Rollo no encontrado"));
            RolloDTO rolloDTO = new RolloDTO();

            // Asignar valores a DTO
            rolloDTO.setNombre(rollo.getNombre());
            rolloDTO.setColor(rollo.getColor());
            rolloDTO.setCosto(rollo.getCosto());
            rolloDTO.setPeso_gr(rollo.getPeso_gr());
            //rolloDTO.setMaterial(rollo.getMaterial());

            model.addAttribute("rolloDTO", rolloDTO);
            model.addAttribute("id", id);
            model.addAttribute("materiales", materialesService.getMateriales()); // Asegúrate de pasar la lista de materiales
        } catch (Exception e) {
            System.out.println("Excepción: " + e.getMessage());
            return "redirect:/rollos";
        }
        return "rollos/editarRollo";
    }

    @PostMapping("/edit")
    public String actualizarRollo(Model model, @ModelAttribute RolloDTO rolloDTO, @RequestParam("id") long id, BindingResult resultado) {
        if (resultado.hasErrors()) {
            return "rollos/editarRollo";
        }
        Rollo rollo = rollosService.getRollos(id).orElseThrow(() -> new RuntimeException("Rollo no encontrado"));

        rollo.setNombre(rolloDTO.getNombre());
        rollo.setColor(rolloDTO.getColor());
        rollo.setCosto(rolloDTO.getCosto());
        rollo.setPeso_gr(rolloDTO.getPeso_gr());


        rollosService.create(rollo);
        return "redirect:/rollos";
    }

    @DeleteMapping("/api/{id}") // Eliminar por API
    public void delete(@RequestParam("id") Long id) {
        rollosService.delete(id);
    }

    @GetMapping("/delete")
    public String eliminarRollo(@RequestParam("id")long id) {
        try {
            Rollo rollo = rollosService.getRollos(id).get();
            rollosService.delete(rollo.getId());
        } catch (Exception e) {
            System.out.println("Excepción: " + e.getMessage());
        }
        return "redirect:/rollos";
    }




}
