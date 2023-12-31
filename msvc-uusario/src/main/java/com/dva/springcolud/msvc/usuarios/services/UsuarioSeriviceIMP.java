package com.dva.springcolud.msvc.usuarios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dva.springcolud.msvc.usuarios.client.CursoClienteFeing;
import com.dva.springcolud.msvc.usuarios.models.entities.Usuario;
import com.dva.springcolud.msvc.usuarios.repositories.UsuarioRepository;

@Service
public class UsuarioSeriviceIMP implements IUsuriosServices {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    CursoClienteFeing clienteFeing;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuairos() {
        return (List<Usuario>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> getbyId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
        clienteFeing.eliminaUsuario(id);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(Usuario usuario) {
        return repository.porEmail(usuario);
    }

    // Agregando el metodo exists
    @Override
    public boolean emailExiste(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsaurioById(List<Long> ids) {
        return (List<Usuario>) repository.findAllById(ids);
    }

}
