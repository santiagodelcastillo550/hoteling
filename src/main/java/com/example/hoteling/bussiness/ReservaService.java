package com.example.hoteling.bussiness;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hoteling.entities.Estado;
import com.example.hoteling.entities.Recurso;
import com.example.hoteling.entities.Reserva;
import com.example.hoteling.entities.Usuario;
import com.example.hoteling.repositories.ReservaRepository;

@Service
public class ReservaService {
	private static final Logger log = LoggerFactory.getLogger(ReservaService.class);
	
	@Autowired
	private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public void crearReserva(Usuario usuario, Recurso recurso, LocalDate fechaInicio, LocalDate fechaFin, int personas, String observaciones) {
        log.info("Entrando en crearReserva(): usuario={}, recurso={}, personas={}, fechas={} -> {}",
                usuario.getNombre(), recurso.getNombre(), personas, fechaInicio, fechaFin);

        List<Reserva> solapadas = reservaRepository.findReservasSolapadas(usuario, recurso, fechaInicio, fechaFin);

        if (!solapadas.isEmpty()) {
            throw new IllegalArgumentException("Ya tienes una reserva en estas fechas para este recurso.");
        }
        
        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setRecurso(recurso);
        reserva.setFechaInicio(fechaInicio);
        reserva.setFechaFin(fechaFin);
        reserva.setPersonas(personas);
        reserva.setObservaciones(observaciones);
        reserva.setEstado(Estado.CONFIRMADA);

        reservaRepository.save(reserva);
        log.info("Reserva guardada correctamente en la base de datos con ID={}", reserva.getId());
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
    
    public List<Reserva> obtenerReservasDeUsuario(Usuario usuario) {
        return reservaRepository.findByUsuario(usuario);
    }
    
    public void cancelarReserva(Reserva reserva, Usuario usuario) {
        if (!reserva.getUsuario().getId().equals(usuario.getId())) {
            throw new IllegalArgumentException("No puedes cancelar una reserva que no es tuya.");
        }
        reserva.setEstado(Estado.CANCELADA);
        reservaRepository.save(reserva);
    }


}
