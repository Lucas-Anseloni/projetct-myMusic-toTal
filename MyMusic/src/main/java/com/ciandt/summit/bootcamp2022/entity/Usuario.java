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
    private TipoUsuarioEnum tipoUsuario;

    @OneToOne
    @JoinColumn(name = "PlaylistId")
    private Playlist playlist;

    public Usuario() {
    }

    public Usuario(String id, String nome, TipoUsuarioEnum tipoUsuarioEnum, Playlist playlist) {
        this.id = id;
        this.nome = nome;
        this.tipoUsuario = tipoUsuarioEnum;
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

    public TipoUsuarioEnum getTipoUsuarioEnum() {
        return tipoUsuario;
    }

    public void setTipoUsuarioEnum(TipoUsuarioEnum tipoUsuarioEnum) {
        this.tipoUsuario = tipoUsuarioEnum;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }
}
