package com.impresion3d.gest3d.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error"; // Devuelve la vista error.html
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404(NoHandlerFoundException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error"; // Devuelve la vista error.html
    }

    // Puedes agregar más manejadores para otros tipos de excepciones
}