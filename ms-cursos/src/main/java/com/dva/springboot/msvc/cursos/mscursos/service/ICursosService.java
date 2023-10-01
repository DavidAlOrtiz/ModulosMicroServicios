package com.dva.springboot.msvc.cursos.mscursos.service;

import java.util.List;
import java.util.Optional;

import com.dva.springboot.msvc.cursos.mscursos.entities.models.Curso;

public interface ICursosService {

    List<Curso> getCursos();

    Optional<Curso> getById(Long id);

    Curso save(Curso curso);

    Curso update(Long id, Curso curso);

    void delete(Long id);

}
