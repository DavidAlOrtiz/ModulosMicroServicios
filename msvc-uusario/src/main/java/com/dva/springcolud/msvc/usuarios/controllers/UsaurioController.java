package com.dva.springcolud.msvc.usuarios.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dva.springcolud.msvc.usuarios.models.entities.Usuario;
import com.dva.springcolud.msvc.usuarios.services.IUsuriosServices;

@RestController
@RequestMapping("/usuario")
public class UsaurioController {

    @Autowired
    private IUsuriosServices usuarioService;

    @GetMapping
    public List<Usuario> getUsuario() {
        return usuarioService.getUsuairos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.getbyId(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.badRequest().build();
    }

}
