package com.example.hoteling.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hoteling.entities.Recurso;
import com.example.hoteling.repositories.RecursoRepository;

@Controller
public class RecursoController {

	private final RecursoRepository recursoRepository;
	
	public RecursoController(RecursoRepository recursoRepository) {
        this.recursoRepository = recursoRepository;
    }
	
	// Página principal con lista de recursos
    @GetMapping("/")
    public String index(Model model, @RequestParam(required = false) String q) {
        List<Recurso> recursos;

        if (q != null && !q.isEmpty()) {
            // Si hay búsqueda, filtramos por nombre o precio (simplificado)
            try {
                // Intentamos buscar por precio primero
                double precio = Double.parseDouble(q);
                recursos = recursoRepository.findAll().stream()
                        .filter(r -> r.getPrecio().doubleValue() <= precio)
                        .toList();
            } catch (NumberFormatException e) {
                // Si no es número, buscamos por nombre
                recursos = recursoRepository.findAll().stream()
                        .filter(r -> r.getNombre().toLowerCase().contains(q.toLowerCase()))
                        .toList();
            }
        } else {
            recursos = recursoRepository.findAll();
        }

        model.addAttribute("recursos", recursos);
        return "index"; // Thymeleaf: src/main/resources/templates/index.html
    }

    // Detalle de recurso (para tu botón "Información")
    @GetMapping("/recursos/{id}")
    public String detalleRecurso(@PathVariable Long id, Model model) {
        Recurso recurso = recursoRepository.findById(id).orElse(null);
        model.addAttribute("recurso", recurso);
        return "fichaDetalle"; // Necesitarías crear detalle.html
    }
}
