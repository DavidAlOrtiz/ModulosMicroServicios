package com.dva.springboot.msvc.cursos.mscursos.feingReopository;

import org.hibernate.mapping.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dva.springboot.msvc.cursos.mscursos.entities.Usuario;

import jakarta.websocket.server.PathParam;

//Defiene el servicio con el cual tendra comunicacion 
//Nuestro micro servicio

@FeignClient(name = "msvc-usuario", url = "localhost:8001/usuario")
public interface UsuarioFeingRepo {

    @GetMapping("/{id}")
    Usuario getUsuario(@PathVariable Long id);

    @PostMapping()
    Usuario saveUsaurio(@RequestBody Usuario usuario);

}
