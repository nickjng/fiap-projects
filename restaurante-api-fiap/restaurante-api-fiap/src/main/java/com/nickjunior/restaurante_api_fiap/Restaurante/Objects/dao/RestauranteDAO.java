package com.nickjunior.restaurante_api_fiap.Restaurante.Objects.dao;

import com.nickjunior.restaurante_api_fiap.Usuarios.Entity.UsuarioEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public record RestauranteDAO(
        String nome,
        String descricao,
        LocalDate dataInauguracao,
        Map<String, Object>dadosRestaurante,
        LocalDateTime dataCriacao,
        LocalDateTime dataUltimaAlteracao,
        UsuarioEntity usuarioDono
) {
}
