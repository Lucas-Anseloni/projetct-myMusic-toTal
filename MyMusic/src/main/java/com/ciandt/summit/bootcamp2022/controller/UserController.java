package com.ciandt.summit.bootcamp2022.controller;


import com.ciandt.summit.bootcamp2022.entity.Usuario;
import com.ciandt.summit.bootcamp2022.exception.UsuarioNaoExisteException;
import com.ciandt.summit.bootcamp2022.service.UsuarioServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UsuarioServiceImp usuarioServiceImp;

    @GetMapping("/{id}")
    @Operation(summary = "- Reponsável por buscar as musícas.", security = @SecurityRequirement(name = "basicAuth"))
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable(name = "id") String id){
        logger.info("Executando GET - /api/usuario?id=" + id);
        Usuario usuario =  usuarioServiceImp.buscarUsuario(id);

        if(usuario == null){
            logger.error("Nenhum usuario encontrado com o id: " + id);
            throw new UsuarioNaoExisteException("Usuario não encontrado!");
        }

        logger.info("Busca realizada com sucesso - 200 OK");
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
}
