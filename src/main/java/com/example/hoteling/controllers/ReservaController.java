package com.example.hoteling.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.hoteling.bussiness.ReservaService;
import com.example.hoteling.entities.Recurso;
import com.example.hoteling.entities.Reserva;
import com.example.hoteling.entities.Usuario;
import com.example.hoteling.repositories.RecursoRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReservaController {

    private final RecursoRepository recursoRepository;
    private final ReservaService reservaService;

    public ReservaController(RecursoRepository recursoRepository, ReservaService reservaService) {
        this.recursoRepository = recursoRepository;
        this.reservaService = reservaService;
    }

    // Mostrar formulario de reserva
    @GetMapping("/reservar/{id}")
    public String mostrarFormularioReserva(@PathVariable Long id, Model model, HttpSession session) {
    	
    	// ✅ Obtener usuario logueado desde la sesión
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            // Si no hay sesión activa, redirigimos al login
            return "redirect:/login";
        }
    	
        Recurso recurso = recursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
        model.addAttribute("recurso", recurso);
        return "reserva";
    }

    // Procesar reserva (usuario en sesión)
    @PostMapping("/reservar/{id}")
    public String procesarReserva(
            @PathVariable Long id,
            @RequestParam String fechaEntrada,
            @RequestParam String fechaSalida,
            @RequestParam Integer personas,
            @RequestParam(required = false) String observaciones,
            HttpSession session,
            Model model
    ) {
    	// ✅ Obtener usuario logueado desde la sesión
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        Recurso recurso = recursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recurso no encontrado"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaInicio = LocalDate.parse(fechaEntrada, formatter);
        LocalDate fechaFin = LocalDate.parse(fechaSalida, formatter);

        try {
            reservaService.crearReserva(usuario, recurso, fechaInicio, fechaFin, personas, observaciones);
            model.addAttribute("successReserva", true);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorReserva", true);
        }

        model.addAttribute("recurso", recurso);
        return "reserva";
    }
    
    @GetMapping("/mis-reservas")
    public String verMisReservas(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/login";
        }

        List<Reserva> reservas = reservaService.obtenerReservasDeUsuario(usuario);
        model.addAttribute("reservas", reservas);

        return "reservasUser"; // nombre del template
    }

}
