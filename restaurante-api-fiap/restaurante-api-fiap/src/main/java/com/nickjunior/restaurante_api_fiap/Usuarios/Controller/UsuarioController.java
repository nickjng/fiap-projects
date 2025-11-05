package com.nickjunior.restaurante_api_fiap.Usuarios.Controller;

import com.nickjunior.restaurante_api_fiap.Auth.Service.AuthServiceImpl;
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

    @Autowired
    private AuthServiceImpl authService;


    @PostMapping()
    public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody UsuarioDAO userRequest){
        UsuarioDTO usuarioCadastrado = usuarioServiceImpl.cadastrarUsuario(userRequest);
        return ResponseEntity.status(201).body(usuarioCadastrado);
    }

    @GetMapping()
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios(@RequestParam(name = "nome",required = false) String buscarPor){
        List<UsuarioDTO> listaUsuarios = usuarioServiceImpl.listarUsuarios(buscarPor);
        return ResponseEntity.status(200).body(listaUsuarios);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> buscarUsuario(@PathVariable Long idUsuario){
        UsuarioDTO usuarioBuscado = usuarioServiceImpl.buscarUsuario(idUsuario);
        return ResponseEntity.status(200).body(usuarioBuscado);
    }

    @PatchMapping("/atualizar-dados")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@RequestBody UsuarioDAO userPatch,  @RequestHeader("Authorization") String token){
        UsuarioDTO usuarioAtualizado = usuarioServiceImpl.atualizarUsuario(userPatch, authService.getUsuarioFromToken(token));
        return ResponseEntity.status(204).body(usuarioAtualizado);
    }

    @DeleteMapping
    public ResponseEntity<String> deletarConta( @RequestHeader("Authorization") String token){
        return ResponseEntity.status(204).body(usuarioServiceImpl.deletarConta(authService.getUsuarioFromToken(token)));
    }


}
