package com.dva.springcolud.msvc.usuarios.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dva.springcolud.msvc.usuarios.models.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> getByEmail(Usuario usuario);

    @Query("SELECT u FROM Usuario u where u.email=?1")
    Optional<Usuario> porEmail(Usuario usuario);

    boolean existsByEmail(String email);

}
