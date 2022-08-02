package com.ciandt.summit.bootcamp2022.entity;


import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    private String id;
    private String nome;

    @Column(name = "tipoUsuario")
    private TipoUsuarioEnum tipoUsuarioEnum;

    @OneToMany
    @JoinColumn(name = "PlaylistId")
    private List<Playlist> playlist;

    public Usuario() {
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

    public TipoUsuarioEnum getTipoUsuario() {
        return tipoUsuarioEnum;
    }

    public void setTipoUsuario(TipoUsuarioEnum tipoUsuarioEnum) {
        this.tipoUsuarioEnum = tipoUsuarioEnum;
    }

    public List<Playlist> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<Playlist> playlist) {
        this.playlist = playlist;
    }
}
