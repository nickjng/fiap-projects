package com.nickjunior.restaurante_api_fiap.Auth.Controller;

import com.nickjunior.restaurante_api_fiap.Auth.Objects.dao.LoginDAO;
import com.nickjunior.restaurante_api_fiap.Auth.Objects.dto.LoginDTO;
import com.nickjunior.restaurante_api_fiap.Auth.Service.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "API para login e geração de tokens")
public class AuthController {

    @Autowired
    private AuthServiceImpl authServiceImpl;

    @Operation(summary = "Realizar login")
    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody LoginDAO request) {
        LoginDTO response = authServiceImpl.login(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Validar token")
    @PostMapping("/validar")
    public ResponseEntity<String> validarToken(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        authServiceImpl.validarToken(token);
        return ResponseEntity.ok("Token válido");
    }
}