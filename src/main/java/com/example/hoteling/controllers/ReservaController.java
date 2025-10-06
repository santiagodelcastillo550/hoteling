package com.example.hoteling.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hoteling.bussiness.ReservaService;
import com.example.hoteling.entities.Recurso;
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
    public String mostrarFormularioReserva(@PathVariable Long id, Model model) {
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
            @RequestParam int personas,
            HttpSession session
    ) {
        // ✅ Obtener usuario logueado desde la sesión
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            // Si no hay sesión activa, redirigimos al login
            return "redirect:/login";
        }

        Recurso recurso = recursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recurso no encontrado"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaInicio = LocalDate.parse(fechaEntrada, formatter);
        LocalDate fechaFin = LocalDate.parse(fechaSalida, formatter);

        if (!fechaFin.isAfter(fechaInicio)) {
            throw new RuntimeException("La fecha de salida debe ser posterior a la de entrada");
        }

        reservaService.crearReserva(usuario, recurso, fechaInicio, fechaFin, personas, null);

        return "redirect:/";
    }
    
    @GetMapping("/mis-reservas")
    public String verMisReservas(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("reservas", reservaService.obtenerReservasDeUsuario(usuario));
        return "reservasUser"; // nombre del template
    }

}
