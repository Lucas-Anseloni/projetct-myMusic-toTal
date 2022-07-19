package com.ciandt.summit.bootcamp2022.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioPlaylist extends JpaRepository<Usuario, String> {

    List<Usuario> findByNome(String nome);
}
