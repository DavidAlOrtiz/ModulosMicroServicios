package com.dva.springboot.msvc.cursos.mscursos.DAO;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dva.springboot.msvc.cursos.mscursos.entities.models.Curso;

public interface CusrosDAO extends CrudRepository<Curso, Long> {

    @Modifying
    @Query("DELETE FROM CursoUsuario cu WHERE cu.usuarioId =?1")
    void eliminarCursoUsuario(Long id);
}
