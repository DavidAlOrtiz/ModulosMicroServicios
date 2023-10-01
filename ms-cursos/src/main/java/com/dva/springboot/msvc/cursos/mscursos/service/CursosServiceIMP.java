package com.dva.springboot.msvc.cursos.mscursos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.dva.springboot.msvc.cursos.mscursos.DAO.CusrosDAO;
import com.dva.springboot.msvc.cursos.mscursos.entities.models.Curso;

public class CursosServiceIMP implements ICursosService {

    @Autowired
    private CusrosDAO cursoDao;

    @Override
    public List<Curso> getCursos() {
        return (List<Curso>) cursoDao.findAll();
    }

    @Override
    public Optional<Curso> getById(Long id) {
        return cursoDao.findById(id);
    }

    @Override
    public Curso save(Curso curso) {
        return cursoDao.save(curso);
    }

    @Override
    public Curso update(Long id, Curso curso) {
        Optional<Curso> cursoNuevo = this.getById(id);
        if (cursoNuevo.isPresent()) {
            cursoNuevo.get().setName(curso.getName());
            return cursoDao.save(cursoNuevo.get());
        }
        return cursoNuevo.orElse(null);
    }

    @Override
    public void delete(Long id) {
        cursoDao.deleteById(id);
    }

}
