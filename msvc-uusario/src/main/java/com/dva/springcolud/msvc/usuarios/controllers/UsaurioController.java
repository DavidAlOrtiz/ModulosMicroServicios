package com.dva.springcolud.msvc.usuarios.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioConsulta = usuarioService.getbyId(id);
        if (usuarioConsulta.isPresent()) {
            Usuario usuCon = usuarioConsulta.get();
            usuCon.setNombre(usuario.getNombre());
            usuCon.setPassword(usuario.getPassword());
            usuCon.setEmail(usuario.getEmail());
            System.err.println(usuCon);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuCon));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.getbyId(id);
        if (usuario.isPresent()) {
            usuarioService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
