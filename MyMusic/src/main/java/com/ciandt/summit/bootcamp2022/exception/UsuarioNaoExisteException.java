package com.ciandt.summit.bootcamp2022.exception;

public class UsuarioNaoExisteException extends RuntimeException {
    public UsuarioNaoExisteException(String message){
        super(message);
    }
}
