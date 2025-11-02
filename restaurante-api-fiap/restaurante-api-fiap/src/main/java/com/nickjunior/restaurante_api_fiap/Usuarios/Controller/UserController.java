package com.nickjunior.restaurante_api_fiap.Usuarios.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/usuarios")
public class UserController {

    @GetMapping()
    public ResponseEntity<String> teste(){
        return ResponseEntity.ok("Tudo certo");
    }


}
