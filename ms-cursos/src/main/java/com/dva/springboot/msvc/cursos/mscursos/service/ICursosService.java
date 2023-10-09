package com.dva.springboot.msvc.cursos.mscursos.service;

import java.util.List;
import java.util.Optional;

import com.dva.springboot.msvc.cursos.mscursos.entities.Usuario;
import com.dva.springboot.msvc.cursos.mscursos.entities.models.Curso;

public interface ICursosService {

    List<Curso> getCursos();

    Optional<Curso> getById(Long id);

    Curso save(Curso curso);

    Curso update(Long id, Curso curso);

    void delete(Long id);

    Optional<Usuario> agregarUsuario(Usuario usuario, Long idCurso);

    Optional<Usuario> crearUsuario(Usuario usuario, Long idCurso);

    Optional<Usuario> eliminarUsuario(Usuario usuario, Long idCurso);

    Optional<Curso> porIdUsuarioCurso(Long id);

    void eliminarUsarioCursoId(Long id);
}
