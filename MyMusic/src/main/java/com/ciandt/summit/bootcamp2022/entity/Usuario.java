package com.ciandt.summit.bootcamp2022.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    private String id;
    private String nome;

    @Column(name = "tipoUsuario")
    private String tipoUsuario;

    @OneToOne
    @JoinColumn(name = "PlaylistId")
    private Playlist playlist;

    public Usuario() {
    }

    public Usuario(String id, String nome, String tipoUsuario, Playlist playlist) {
        this.id = id;
        this.nome = nome;
        this.tipoUsuario = tipoUsuario;
        this.playlist = playlist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }
}
