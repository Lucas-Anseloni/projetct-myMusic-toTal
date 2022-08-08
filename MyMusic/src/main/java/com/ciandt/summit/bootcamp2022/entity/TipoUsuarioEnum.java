package com.ciandt.summit.bootcamp2022.entity;

public enum TipoUsuarioEnum {

    COMUM("COMUM"),
    PREMIUM("PREMIUM");

    private final String descricao;

    TipoUsuarioEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}