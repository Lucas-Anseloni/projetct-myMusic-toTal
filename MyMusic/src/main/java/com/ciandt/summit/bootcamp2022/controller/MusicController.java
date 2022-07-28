package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.entity.Musica;
import com.ciandt.summit.bootcamp2022.exception.MusicaNaoEncontradaException;
import com.ciandt.summit.bootcamp2022.service.MusicaServiceImp;
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
@RequestMapping("/api")
public class MusicController {

    private static final Logger logger = LoggerFactory.getLogger(MusicController.class);
    @Autowired   private MusicaServiceImp musicaServiceImp;

    @GetMapping("/musicas")
    @Operation(summary = "- Reponsável por buscar as musícas.", security = @SecurityRequirement(name = "basicAuth"))
    public ResponseEntity<List<Musica>> buscarMusica(@RequestParam String filtro){
        logger.info("Executando GET - /api/musicas?filtro=" + filtro);
        List<Musica> musicas =  musicaServiceImp.buscarMusicas(filtro);

        if(musicas.isEmpty()){
            logger.error("Nenhuma musica encontrada com o filtro: " + filtro);
            throw new MusicaNaoEncontradaException("Música não encontrada!");
        }

        logger.info("Busca realizada com sucesso - 200 OK");
        return new ResponseEntity<List<Musica>>(musicas, HttpStatus.OK);
    }
}

