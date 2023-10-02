package com.dva.springboot.msvc.cursos.mscursos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dva.springboot.msvc.cursos.mscursos.entities.models.Curso;
import com.dva.springboot.msvc.cursos.mscursos.service.ICursosService;

import jakarta.validation.Valid;

@RestController
public class CursoController {

    @Autowired
    private ICursosService cursosService;

    @GetMapping
    public ResponseEntity<List<Curso>> getCursos() {
        return ResponseEntity.ok().body(cursosService.getCursos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Curso> curso = cursosService.getById(id);
        if (curso.isPresent()) {
            return ResponseEntity.ok(curso);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Curso curso, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(this.validarCampos(result));
        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(cursosService.save(curso));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("Ocurrio un error !!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Curso curso, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(this.validarCampos(result));
        }
        if (cursosService.update(id, curso) != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(curso);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Curso> curOptional = cursosService.getById(id);
        if (curOptional.isPresent()) {
            cursosService.delete(curOptional.get().getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private Map<String, String> validarCampos(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getAllErrors().forEach(p -> {
            errores.put(p.getObjectName(),
                    "Error en el objeto " + p.getObjectName().concat(" " + p.getDefaultMessage()));
        });
        return errores;
    }
}
