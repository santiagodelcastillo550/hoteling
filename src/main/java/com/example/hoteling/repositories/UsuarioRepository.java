package com.example.hoteling.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hoteling.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	// Buscar por email (útil para login y evitar duplicados)
    Optional<Usuario> findByEmail(String email);
    
    Optional<Usuario> findByNombre(String nombre);

}
