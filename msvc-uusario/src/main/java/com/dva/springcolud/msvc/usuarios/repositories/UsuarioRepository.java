package com.dva.springcolud.msvc.usuarios.repositories;

import org.springframework.data.repository.CrudRepository;

import com.dva.springcolud.msvc.usuarios.models.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
