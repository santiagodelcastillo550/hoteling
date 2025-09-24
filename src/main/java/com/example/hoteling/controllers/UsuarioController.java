package com.example.hoteling.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hoteling.entities.Rol;
import com.example.hoteling.entities.Usuario;
import com.example.hoteling.repositories.UsuarioRepository;

@Controller
public class UsuarioController {

	private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
 // Mostrar login
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Mostrar registro
    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    // Guardar usuario registrado
    @PostMapping("/registro")
    public String guardarUsuario(Usuario usuario, @RequestParam String confirmPassword, Model model) {

        // Verificar que las contraseñas coincidan
        if (!usuario.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Las contraseñas no coinciden");
            return "registro";
        }

        // Verificar que el email no exista
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            model.addAttribute("error", "El email ya está registrado");
            return "registro";
        }

        // Asignar rol por defecto
        usuario.setRol(Rol.USER);

        // Guardar usuario
        usuarioRepository.save(usuario);

        return "redirect:/login";
    }

    // Login simplificado (solo demo, sin Spring Security)
    @PostMapping("/login")
    public String autenticar(@RequestParam String email, @RequestParam String password, Model model) {
        return usuarioRepository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password))
                .map(u -> "redirect:/") // Login correcto → redirige al index
                .orElseGet(() -> {
                    model.addAttribute("error", "Email o contraseña incorrectos");
                    return "login";
                });
    }
}
