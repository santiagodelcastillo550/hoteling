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
import com.example.hoteling.repositories.UsuarioRepository;

@Controller
public class ReservaController {

	private final RecursoRepository recursoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ReservaService reservaService;

    public ReservaController(RecursoRepository recursoRepository, UsuarioRepository usuarioRepository, ReservaService reservaService) {
        this.recursoRepository = recursoRepository;
        this.usuarioRepository = usuarioRepository;
        this.reservaService = reservaService;
    }

    // Mostrar formulario de reserva
    @GetMapping("/reservar/{id}")
    public String mostrarFormularioReserva(@PathVariable Long id, Model model) {
        Recurso recurso = recursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
        model.addAttribute("recurso", recurso);
        return "reserva"; // nombre de tu HTML
    }

    // Procesar reserva
    @PostMapping("/reservar/{id}")
    public String procesarReserva(
            @PathVariable Long id,
            @RequestParam String fechaEntrada,
            @RequestParam String fechaSalida,
            @RequestParam int personas,
            @RequestParam(required = false) String observaciones
    ) {
        Recurso recurso = recursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recurso no encontrado"));

        // Para simplificar, usamos un usuario fijo; en producción usar autenticación
        Usuario usuario = usuarioRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaInicio = LocalDate.parse(fechaEntrada, formatter);
        LocalDate fechaFin = LocalDate.parse(fechaSalida, formatter);

        // Validación simple: fechaFin > fechaInicio
        if (!fechaFin.isAfter(fechaInicio)) {
            throw new RuntimeException("La fecha de salida debe ser posterior a la de entrada");
        }

        reservaService.crearReserva(usuario, recurso, fechaInicio, fechaFin, personas, observaciones);

        return "redirect:/"; // redirigir a la página principal
    }
}
