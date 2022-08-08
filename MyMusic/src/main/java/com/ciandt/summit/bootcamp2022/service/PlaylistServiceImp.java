package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.controller.request.PlaylistRequest;
import com.ciandt.summit.bootcamp2022.entity.*;
import com.ciandt.summit.bootcamp2022.exception.*;
import com.ciandt.summit.bootcamp2022.model.MusicaDTO;
import com.ciandt.summit.bootcamp2022.repository.PlaylistMusicaRepository;
import com.ciandt.summit.bootcamp2022.repository.PlaylistRepository;
import com.ciandt.summit.bootcamp2022.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaylistServiceImp implements PlaylistService {

    private static final Logger logger = LoggerFactory.getLogger(PlaylistServiceImp.class);

    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private PlaylistMusicaRepository playlistMusicaRepository;
    @Autowired
    private MusicaServiceImp musicaServiceImp;

    @Autowired
    private UsuarioServiceImp usuarioServiceImp;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public PlaylistMusica adicionarMusicaNaPlaylist(String playlistId, PlaylistRequest musicaRequest) {
        buscarPlaylistPorId(playlistId);

        Musica musicaDb = musicaServiceImp.buscarMusicaPorId(musicaRequest.getData().getId());

        validarPayloadBodyRequest(musicaRequest.getData(), musicaDb);

        Optional<PlaylistMusica> relacaoPlaylistMusica = playlistMusicaRepository.findByPlaylistIdAndMusicaId(playlistId, musicaRequest.getData().getId());

        if (relacaoPlaylistMusica.isPresent()) {
            throw new NaoPermitidoSalvarAMesmaMusicaException("Música duplicada.");
        }

        PlaylistMusica playlistMusica = new PlaylistMusica(new PlaylistMusicaKey(playlistId, musicaRequest.getData().getId()));

        playlistMusicaRepository.save(playlistMusica);

        return playlistMusica;
    }

    public void validarPayloadBodyRequest(MusicaDTO musicaRequest, Musica musicaDb) {
        logger.info("Validando payload do RequestBody");
        if (!musicaDb.getNome().equalsIgnoreCase(musicaRequest.getNome())
                || !musicaDb.getArtista().getId().equals(musicaRequest.getArtista().getId())
                || !musicaDb.getArtista().getNome().equals(musicaRequest.getArtista().getNome())) {
            logger.error("Payload inválido.");
            throw new PayloadBodyInvalidoException(musicaRequest.getId());
        }
    }

    @Override
    public Playlist buscarPlaylistPorId(String playlistId) {
        logger.info("Buscando playlist com id: " + playlistId);
        return playlistRepository.findById(playlistId).orElseThrow(() -> new PlaylistNaoExisteException(playlistId));
    }

    @Override
    public void removerMusicaFromPlaylist(String playlistId, String musicaId) {
        Playlist playlist = buscarPlaylistPorId(playlistId);
        Musica musica = musicaServiceImp.buscarMusicaPorId(musicaId);
        if (playlistMusicaRepository.findByPlaylistIdAndMusicaId(playlistId, musicaId).isPresent()) {
            playlistMusicaRepository.deleteById(new PlaylistMusicaKey(playlistId, musicaId));
        } else {
            throw new PlaylistNaoContemMusicaException(playlistId + " " + musicaId);
        }
        logger.info("Removendo a musica " + musica + " da playlist " + playlist.getId());
    }

    @Override
    public PlaylistMusica adicionarMusicaNaPlaylistUsuario(String playlistId, PlaylistRequest musicaRequest, String usuarioId) {
        buscarPlaylistPorId(playlistId);

        Usuario usuario = usuarioServiceImp.buscarUsuario(usuarioId);

        if(!usuario.getPlaylist().getId().equals(playlistId)){
            throw new PlaylistNaoPertenceAoUsuarioException(playlistId + " " + usuarioId);
        }

        if(usuario.getTipoUsuario().equals(TipoUsuarioEnum.COMUM.getDescricao()) && playlistMusicaRepository.quantidadeMusica(usuarioId) >=5){
            throw new ValidarQuantidadeMusica(usuarioId);
        }

        Musica musicaDb = musicaServiceImp.buscarMusicaPorId(musicaRequest.getData().getId());

        validarPayloadBodyRequest(musicaRequest.getData(), musicaDb);

        Optional<PlaylistMusica> relacaoPlaylistMusica = playlistMusicaRepository.findByPlaylistIdAndMusicaId(playlistId, musicaRequest.getData().getId());

        if (relacaoPlaylistMusica.isPresent()) {
            throw new NaoPermitidoSalvarAMesmaMusicaException("Música duplicada.");
        }

        PlaylistMusica playlistMusica = new PlaylistMusica(new PlaylistMusicaKey(playlistId, musicaRequest.getData().getId()));

        playlistMusicaRepository.save(playlistMusica);

        return playlistMusica;

    }
}
