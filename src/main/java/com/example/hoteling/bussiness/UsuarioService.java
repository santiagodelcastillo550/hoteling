package com.example.hoteling.bussiness;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.hoteling.entities.Rol;
import com.example.hoteling.entities.Usuario;
import com.example.hoteling.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Registrar un nuevo usuario
    public String registrarUsuario(Usuario usuario, String confirmPassword) {
        if (!usuario.getPassword().equals(confirmPassword)) {
            return "Las contraseñas no coinciden";
        }

        if (usuarioRepository.findByNombre(usuario.getNombre()).isPresent()) {
            return "El nombre de usuario ya está en uso";
        }

        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            return "El email ya está registrado";
        }

        usuario.setRol(Rol.USER);
        usuarioRepository.save(usuario);

        return null; // null = todo OK
    }

    // Autenticar usuario
    public Optional<Usuario> autenticar(String nombre, String password) {
        return usuarioRepository.findByNombre(nombre)
                .filter(u -> u.getPassword().equals(password));
    }
}
