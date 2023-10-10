package com.dva.springcolud.msvc.usuarios.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-cursos", url = "localhost:8002")
public interface CursosClienteFeing {

    @DeleteMapping("/eliminaUsuario-curso/{id}")
    void eliminaUsuario(@PathVariable Long id);

}
