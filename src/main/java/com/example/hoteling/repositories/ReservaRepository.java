package com.example.hoteling.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.hoteling.entities.Recurso;
import com.example.hoteling.entities.Reserva;
import com.example.hoteling.entities.Usuario;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{

	// Obtener reservas de un usuario concreto
    List<Reserva> findByUsuarioId(Long usuarioId);

    // Obtener reservas de un recurso concreto
    List<Reserva> findByRecursoId(Long recursoId);
    
    List<Reserva> findByUsuario(Usuario usuario);
    
    @Query("SELECT r FROM Reserva r " +
            "WHERE r.usuario = :usuario " +
            "AND r.recurso = :recurso " +
            "AND r.estado = 'CONFIRMADA' " +
            "AND ( (r.fechaInicio <= :fechaFin) AND (r.fechaFin >= :fechaInicio) )")
     List<Reserva> findReservasSolapadas(Usuario usuario, Recurso recurso,
                                         LocalDate fechaInicio, LocalDate fechaFin);
}
