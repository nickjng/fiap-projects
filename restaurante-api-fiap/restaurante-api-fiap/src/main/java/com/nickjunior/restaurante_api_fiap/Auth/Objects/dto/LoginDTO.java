package com.nickjunior.restaurante_api_fiap.Auth.Objects.dto;

public record LoginDTO(
        String token,
        String tipo,
        String nome,
        Long usuarioId
) {}
