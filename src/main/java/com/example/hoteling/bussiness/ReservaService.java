package com.example.hoteling.bussiness;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.hoteling.entities.Recurso;
import com.example.hoteling.entities.Reserva;
import com.example.hoteling.entities.Usuario;
import com.example.hoteling.repositories.ReservaRepository;

@Service
public class ReservaService {
	
	private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

 // Crear reserva sin tocar Estado (para futuro pop-up)
    public Reserva crearReserva(Usuario usuario, Recurso recurso, LocalDate fechaInicio, LocalDate fechaFin, int personas, String observaciones) {
        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setRecurso(recurso);
        reserva.setFechaInicio(fechaInicio);
        reserva.setFechaFin(fechaFin);
        reserva.setPersonas(personas);
        // Estado se deja null por ahora
        // Si quieres guardar las observaciones, agrega un campo en la entidad Reserva
        // reserva.setObservaciones(observaciones);

        return reservaRepository.save(reserva);
    }

    public Optional<Reserva> obtenerReservaPorId(Long id) {
        return reservaRepository.findById(id);
    }

    // Método para actualizar estado en el futuro
    public void actualizarEstado(Long reservaId, com.example.hoteling.entities.Estado nuevoEstado) {
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        reserva.setEstado(nuevoEstado);
        reservaRepository.save(reserva);
    }
}
