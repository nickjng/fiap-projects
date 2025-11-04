package com.nickjunior.restaurante_api_fiap.Usuarios.Objetcs.dao;

import com.nickjunior.restaurante_api_fiap.Usuarios.enums.TipoUsuario;

public record UsuarioDAO(
        Long id,
        String nome,
        String endereco,
        String email,
        String login,
        String senha,
        TipoUsuario tipo
){}
