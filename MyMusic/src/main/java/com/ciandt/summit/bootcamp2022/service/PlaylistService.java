package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.controller.request.PlaylistRequest;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.entity.PlaylistMusica;

public interface PlaylistService {

    PlaylistMusica adicionarMusicaNaPlaylist(String playlistId, PlaylistRequest musica);

    Playlist buscarPlaylistPorId(String playlistId);

     void removerMusicaFromPlaylist(String playlistId, String musicaId);

    PlaylistMusica adicionarMusicaNaPlaylistUsuario(String playlistId, PlaylistRequest musica, String usuarioId);
}
