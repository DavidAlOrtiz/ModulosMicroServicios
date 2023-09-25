package com.dva.springcolud.msvc.usuarios.services;

import java.util.List;
import java.util.Optional;

import com.dva.springcolud.msvc.usuarios.models.entities.Usuario;

public interface IUsuriosServices {
    public List<Usuario> getUsuairos();

    public Optional<Usuario> getbyId(Long id);

    public Usuario save(Usuario usuario);

    public void delete(Long id);
}
