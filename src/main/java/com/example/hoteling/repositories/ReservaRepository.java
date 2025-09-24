package com.example.hoteling.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hoteling.entities.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{

	// Obtener reservas de un usuario concreto
    List<Reserva> findByUsuarioId(Long usuarioId);

    // Obtener reservas de un recurso concreto
    List<Reserva> findByRecursoId(Long recursoId);
}
