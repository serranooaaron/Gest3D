package com.impresion3d.gest3d.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AyudaController {

    @GetMapping("/ayuda/nosotros")
    public String nosotros(Model model) {
        model.addAttribute("title", "Nosotros");
        return "ayuda/nosotros"; 
    }

    @GetMapping("/ayuda/contacto")
    public String contacto() {
        return "ayuda/contacto"; 
    }

    @GetMapping("/ayuda/faq")
    public String faq() {
        return "ayuda/faq";
    }

    @GetMapping("/ayuda/terminos")
    public String terminos() {
        return "ayuda/terminos";
    }
}
