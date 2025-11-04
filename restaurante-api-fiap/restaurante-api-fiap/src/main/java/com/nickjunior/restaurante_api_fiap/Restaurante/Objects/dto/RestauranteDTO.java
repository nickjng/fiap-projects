package com.nickjunior.restaurante_api_fiap.Restaurante.Objects.dto;

import com.nickjunior.restaurante_api_fiap.Usuarios.Entity.UsuarioEntity;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class RestauranteDTO {

    private String nome;
    private String descricao;
    private LocalDate dataInauguracao;
    private Map<String, Object> dadosRestaurante;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltimaAlteracao;
    private UsuarioEntity usuarioDono;

    public RestauranteDTO(String nome, String descricao, LocalDate dataInauguracao,
                          Map<String, Object> dadosRestaurante, LocalDateTime dataCriacao,
                          LocalDateTime dataUltimaAlteracao, UsuarioEntity usuarioDono) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataInauguracao = dataInauguracao;
        this.dadosRestaurante = dadosRestaurante;
        this.dataCriacao = dataCriacao;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
        this.usuarioDono = usuarioDono;
    }
}
