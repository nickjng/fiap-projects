package com.nickjunior.restaurante_api_fiap.Auth.Service;

import com.nickjunior.restaurante_api_fiap.Cliente.Entity.ClienteEntity;
import com.nickjunior.restaurante_api_fiap.Dono.Entity.DonoEntity;
import com.nickjunior.restaurante_api_fiap.Usuarios.Entity.UsuarioEntity;
import com.nickjunior.restaurante_api_fiap.Auth.Objects.dao.LoginDAO;
import com.nickjunior.restaurante_api_fiap.Auth.Objects.dto.LoginDTO;
import com.nickjunior.restaurante_api_fiap.Usuarios.Repository.UsuarioRepository;
import com.nickjunior.restaurante_api_fiap.config.JwtConfig;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtConfig jwtConfig;

    public AuthServiceImpl(UsuarioRepository usuarioRepository, JwtConfig jwtConfig) {
        this.usuarioRepository = usuarioRepository;
        this.jwtConfig = jwtConfig;
    }

    @Override
    public LoginDTO login(LoginDAO request) {
        UsuarioEntity usuario = usuarioRepository.findByEmail(request.login())
                .orElseThrow(() -> new RuntimeException("e-mail inválido"));

        if(!usuario.getSenha().equals(request.senha())){
            throw new RuntimeException();
        }

        String token = gerarToken(usuario);

        return new LoginDTO(
                token,
                usuario.getTipo().name(),
                usuario.getNome(),
                usuario.getId()
        );
    }

    private String gerarToken(UsuarioEntity usuario) {
        return Jwts.builder()
                .setSubject(usuario.getId().toString())
                .claim("tipo", usuario.getTipo().name())
                .claim("nome", usuario.getNome())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
                .signWith(jwtConfig.getSecretKey(), jwtConfig.getSignatureAlgorithm())
                .compact();
    }

    @Override
    public Long validarToken(String token) {
        try {
            return Long.parseLong(
                    Jwts.parserBuilder()
                            .setSigningKey(jwtConfig.getSecretKey())
                            .build()
                            .parseClaimsJws(token)
                            .getBody()
                            .getSubject()
            );
        } catch (Exception e) {
            throw new RuntimeException("Token inválido");
        }
    }

    @Override
    public UsuarioEntity getUsuarioFromToken(String token) {
        Long usuarioId = validarToken(token);
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}