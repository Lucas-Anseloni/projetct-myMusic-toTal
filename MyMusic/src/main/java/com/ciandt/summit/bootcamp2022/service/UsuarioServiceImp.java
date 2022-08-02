package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.Usuario;
import com.ciandt.summit.bootcamp2022.exception.UsuarioNaoExisteException;
import com.ciandt.summit.bootcamp2022.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImp implements UsuarioService{

    private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImp.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario buscarUsuario(String id) {
        logger.info("Buscando usuario com id: " + id);
        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoExisteException(id));
    }

}
