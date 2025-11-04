package com.nickjunior.restaurante_api_fiap.Auth.Objects.dao;

import jakarta.validation.constraints.NotBlank;

public record LoginDAO(
        @NotBlank String login,
        @NotBlank String senha
) {}
