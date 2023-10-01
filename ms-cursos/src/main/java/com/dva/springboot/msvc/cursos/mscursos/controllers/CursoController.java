package com.dva.springboot.msvc.cursos.mscursos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dva.springboot.msvc.cursos.mscursos.entities.models.Curso;
import com.dva.springboot.msvc.cursos.mscursos.service.ICursosService;

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
    public ResponseEntity<?> save(@RequestBody Curso curso) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(cursosService.save(curso));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("Ocurrio un error !!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Curso curso) {
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
}
