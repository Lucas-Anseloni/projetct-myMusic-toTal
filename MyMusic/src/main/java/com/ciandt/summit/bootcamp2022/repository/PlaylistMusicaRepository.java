package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.entity.PlaylistMusica;
import com.ciandt.summit.bootcamp2022.entity.PlaylistMusicaKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PlaylistMusicaRepository extends JpaRepository<PlaylistMusica, PlaylistMusicaKey> {

    @Query(value = "select * from PlaylistMusicas pl where pl.PlaylistId = ?1 and pl.MusicaId = ?2", nativeQuery = true)
    Optional<PlaylistMusica> findByPlaylistIdAndMusicaId(String playlistMusicas, String musicaId);

    @Query(value = "SELECT COUNT(pl.MusicaId) as Quantidade FROM PlaylistMusicas pl INNER join Usuarios us on us.PlaylistId = pl.PlaylistId where us.Id = ?1", nativeQuery = true)
    Long quantidadeMusica(String usuarioId);
}
