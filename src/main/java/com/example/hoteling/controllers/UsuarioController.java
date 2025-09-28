package com.example.hoteling.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hoteling.bussiness.UsuarioService;
import com.example.hoteling.entities.Usuario;

@Controller
public class UsuarioController {

	private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
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
    public String guardarUsuario(Usuario usuario,
                                 @RequestParam String confirmPassword,
                                 Model model) {

        String error = usuarioService.registrarUsuario(usuario, confirmPassword);

        if (error != null) {
            model.addAttribute("error", error);
            return "registro";
        }

        return "redirect:/login";
    }

    @PostMapping("/login")
    public String autenticar(@RequestParam String nombre,
                             @RequestParam String password,
                             Model model) {

        return usuarioService.autenticar(nombre, password)
                .map(u -> "redirect:/") // login OK
                .orElseGet(() -> {
                    model.addAttribute("error", "Usuario o contraseña incorrectos");
                    return "login";
                });
    }
}
