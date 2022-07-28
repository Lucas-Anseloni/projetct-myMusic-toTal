package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.controller.request.PlaylistRequest;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.service.PlaylistServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    private static final Logger logger = LoggerFactory.getLogger(PlaylistController.class);

    @Autowired
    private PlaylistServiceImp playlistServiceImp;

    @PostMapping("/{playlistId}/musicas")
    @Operation(summary = "Responsável por adicionar uma música no playlist.", security = @SecurityRequirement(name = "basicAuth"))
    public ResponseEntity<Playlist> adicionarMusicaPlaylist(@PathVariable String playlistId, @RequestBody PlaylistRequest musica){
        logger.info("Executando POST - /playlists/" + playlistId + "/musicas");
        playlistServiceImp.adicionarMusicaNaPlaylist(playlistId, musica);

        logger.info("Música Adicionada - 201 OK");
        return new ResponseEntity<Playlist>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{playlistId}/musicas/{musicaId}")
    @Operation(summary = "Responsável por remover uma música no playlist.", security = @SecurityRequirement(name = "basicAuth"))
    public ResponseEntity<Playlist> removerMusicaPlaylist(@PathVariable String playlistId, @PathVariable String musicaId){
        logger.info("Executando DELETE - /playlists/" + playlistId + "/musicas" + musicaId);
        playlistServiceImp.removerMusicaFromPlaylist(playlistId,musicaId);

        logger.info("Música removida - 200 OK");
        return new ResponseEntity<Playlist>(HttpStatus.OK);
    }
}