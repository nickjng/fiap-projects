package com.nickjunior.restaurante_api_fiap.Auth.Service;

import com.nickjunior.restaurante_api_fiap.Auth.Objects.dao.LoginDAO;
import com.nickjunior.restaurante_api_fiap.Auth.Objects.dto.LoginDTO;
import com.nickjunior.restaurante_api_fiap.Cliente.Entity.ClienteEntity;
import com.nickjunior.restaurante_api_fiap.Dono.Entity.DonoEntity;
import com.nickjunior.restaurante_api_fiap.Usuarios.Entity.UsuarioEntity;

public interface AuthService {

    LoginDTO login(LoginDAO request);
    Long validarToken(String token);
    UsuarioEntity getUsuarioFromToken(String token);

}
