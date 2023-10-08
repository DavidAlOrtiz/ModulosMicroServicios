package com.dva.springboot.msvc.cursos.mscursos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dva.springboot.msvc.cursos.mscursos.DAO.CusrosDAO;
import com.dva.springboot.msvc.cursos.mscursos.entities.Usuario;
import com.dva.springboot.msvc.cursos.mscursos.entities.models.Curso;
import com.dva.springboot.msvc.cursos.mscursos.entities.models.CursoUsuario;
import com.dva.springboot.msvc.cursos.mscursos.feingReopository.UsuarioFeingRepo;

@Service
public class CursosServiceIMP implements ICursosService {

    @Autowired
    private CusrosDAO cursoDao;

    @Autowired
    private UsuarioFeingRepo usuarioFein;

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

    @Override
    public Optional<Usuario> agregarUsuario(Usuario usuario, Long idCurso) {
        Optional<Curso> o = cursoDao.findById(idCurso);
        if (o.isPresent()) {

            Usuario usuarioMvs = usuarioFein.getUsuario(idCurso);
            Curso curso = o.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioMvs.getId());
            curso.agregarUsaurio(cursoUsuario);
            cursoDao.save(curso);
            return Optional.of(usuarioMvs);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> crearUsuario(Usuario usuario, Long idCurso) {
        System.out.println("Entrando al metodo para agregar al Arriba");
        Optional<Curso> cursoO = cursoDao.findById(idCurso);
        if (cursoO.isPresent()) {
            System.out.println("Entrando al metodo para agregar al usuario");
            Usuario usuarioCreado = usuarioFein.saveUsaurio(usuario);
            Curso curso = cursoO.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioCreado.getId());

            curso.agregarUsaurio(cursoUsuario);
            cursoDao.save(curso);

            return Optional.of(usuarioCreado);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> eliminarUsuario(Usuario usuario, Long idCurso) {

        Optional<Curso> cursoO = cursoDao.findById(idCurso);
        if (cursoO.isPresent()) {
            System.out.println("Presente en el metodo " + usuario.getId());
            Usuario usuarioDb = usuarioFein.getUsuario(usuario.getId());
            System.out.println("Id dle usuario de la base de datos " + usuarioDb.getId());
            Curso curso = cursoO.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuario.getId());
            curso.eliminarUsario(cursoUsuario);

            cursoDao.save(curso);
            return Optional.of(usuario);
        }
        return Optional.empty();
    }

}
