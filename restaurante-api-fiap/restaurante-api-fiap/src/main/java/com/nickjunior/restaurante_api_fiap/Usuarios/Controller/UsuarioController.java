package com.nickjunior.restaurante_api_fiap.Usuarios.Controller;

import com.nickjunior.restaurante_api_fiap.Usuarios.Objetcs.dao.UsuarioDAO;
import com.nickjunior.restaurante_api_fiap.Usuarios.Objetcs.dto.UsuarioDTO;
import com.nickjunior.restaurante_api_fiap.Usuarios.Service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;


    @PostMapping()
    public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody UsuarioDAO userRequest){
        UsuarioDTO usuarioCadastrado = usuarioServiceImpl.cadastrarUsuario(userRequest);
        return ResponseEntity.status(201).body(usuarioCadastrado);
    }

    @GetMapping()
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios(){
        List<UsuarioDTO> listaUsuarios = usuarioServiceImpl.listarUsuarios();
        return ResponseEntity.status(200).body(listaUsuarios);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> buscarUsuario(@PathVariable Long idUsuario){
        UsuarioDTO usuarioBuscado = usuarioServiceImpl.buscarUsuario(idUsuario);
        return ResponseEntity.status(200).body(usuarioBuscado);
    }

    @PatchMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@RequestBody UsuarioDAO userPatch, @PathVariable Long idUsuario){
        UsuarioDTO usuarioAtualizado = usuarioServiceImpl.atualizarUsuario(userPatch, idUsuario);
        return ResponseEntity.status(204).body(usuarioAtualizado);
    }

    @DeleteMapping
    public ResponseEntity<String> deletarConta(@PathVariable Long idUsuario){
        return ResponseEntity.status(204).body(usuarioServiceImpl.deletarConta(idUsuario));
    }


}
