package com.nickjunior.restaurante_api_fiap.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtConfig {

    @Value("${jwt.secret:minhaChaveSuperSecretaParaOJWTTokenDaAplicacaoFIAP2024}")
    private String secret;

    @Value("${jwt.expiration:86400000}")
    private Long expiration;

    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Long getExpiration() {
        return expiration;
    }

    public SignatureAlgorithm getSignatureAlgorithm() {
        return SignatureAlgorithm.HS256;
    }
}