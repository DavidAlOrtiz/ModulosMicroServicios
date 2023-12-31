package com.dva.springcolud.msvc.usuarios.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dva.springcolud.msvc.usuarios.models.entities.Usuario;
import com.dva.springcolud.msvc.usuarios.services.IUsuriosServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
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
    public ResponseEntity<?> guardar(@Valid @RequestBody Usuario usuario, BindingResult result) {

        // if (usuarioService.buscarPorEmail(usuario).isPresent()) {
        // return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje",
        // "El email ya existe"));
        // }

        // if (result.hasErrors()) {
        // System.err.println("Entra al metodo");
        // return ResponseEntity.accepted().body(this.mensajesError(result));
        // }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable Long id, BindingResult result, @RequestBody Usuario usuario) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(this.mensajesError(result));
        }
        Optional<Usuario> usuarioConsulta = usuarioService.getbyId(id);
        if (usuarioConsulta.isPresent()) {
            Usuario usuCon = usuarioConsulta.get();

            if (!usuario.getEmail().equals(usuCon.getEmail()) && usuarioService.buscarPorEmail(usuario).isPresent()) {
                return ResponseEntity.badRequest()
                        .body(Collections.singletonMap("mensaje", "El email ya existe"));
            }

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

    @GetMapping("/usuarios-por-Curso")
    public ResponseEntity<?> getUsuarioPorCurso(@RequestParam List<Long> ids) {
        return ResponseEntity.ok().body(usuarioService.getUsaurioById(ids));
    }

    private Map<String, String> mensajesError(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getAllErrors().forEach(p -> {
            errores.put(p.getObjectName(), "error en el campo " +
                    p.getObjectName() + " " + p.getDefaultMessage());
        });
        return errores;
    }

}
