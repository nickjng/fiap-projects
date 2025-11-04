package com.nickjunior.restaurante_api_fiap.Usuarios.Service;

import com.nickjunior.restaurante_api_fiap.Usuarios.Objetcs.dao.UsuarioDAO;
import com.nickjunior.restaurante_api_fiap.Usuarios.Objetcs.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {

    UsuarioDTO cadastrarUsuario(UsuarioDAO usuarioRequest);
    List<UsuarioDTO> listarUsuarios();
    UsuarioDTO buscarUsuario(Long idUsuario);
    UsuarioDTO atualizarUsuario(UsuarioDAO userPatch, Long idUsuario);
    String deletarConta(Long idUsuario);


}
