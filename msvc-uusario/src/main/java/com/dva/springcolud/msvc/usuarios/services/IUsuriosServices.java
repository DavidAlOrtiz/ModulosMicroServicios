package com.dva.springcolud.msvc.usuarios.services;

import java.util.List;
import java.util.Optional;

import com.dva.springcolud.msvc.usuarios.models.entities.Usuario;

public interface IUsuriosServices {
    List<Usuario> getUsuairos();

    Optional<Usuario> getbyId(Long id);

    Usuario save(Usuario usuario);

    void delete(Long id);

    Optional<Usuario> buscarPorEmail(Usuario usuario);

    boolean emailExiste(String email);

    List<Usuario> getUsaurioById(List<Long> ids);

}
