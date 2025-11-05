package com.nickjunior.restaurante_api_fiap.Restaurante.Objects.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class RestauranteDTO {

    private Long id;
    private String nome;
    private String descricao;
    private LocalDate dataInauguracao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltimaAlteracao;

    public RestauranteDTO(Long id, String nome, String descricao, LocalDate dataInauguracao, LocalDateTime dataCriacao, LocalDateTime dataUltimaAlteracao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInauguracao = dataInauguracao;
        this.dataCriacao = dataCriacao;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }
}
