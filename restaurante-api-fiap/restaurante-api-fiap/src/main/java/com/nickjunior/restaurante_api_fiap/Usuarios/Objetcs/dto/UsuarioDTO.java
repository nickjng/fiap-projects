package com.nickjunior.restaurante_api_fiap.Usuarios.Objetcs.dto;


import com.nickjunior.restaurante_api_fiap.Usuarios.enums.TipoUsuario;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String endereco;
    private String email;
    private String login;
    private String senha;
    private TipoUsuario tipo;
    private String dataCriacao;
    private LocalDate dataUltimaAlteracao;

    public UsuarioDTO(Long id, String nome, String endereco, String email, String login, String senha, TipoUsuario tipo, String dataCriacao, LocalDate dataUltimaAlteracao) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
        this.dataCriacao = dataCriacao;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }
}
