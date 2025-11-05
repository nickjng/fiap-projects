package com.nickjunior.restaurante_api_fiap.Dono.Controller;

import com.nickjunior.restaurante_api_fiap.Auth.Service.AuthService;
import com.nickjunior.restaurante_api_fiap.Dono.Entity.DonoEntity;
import com.nickjunior.restaurante_api_fiap.Dono.Service.DonoServiceImpl;
import com.nickjunior.restaurante_api_fiap.Restaurante.Objects.dao.RestauranteDAO;
import com.nickjunior.restaurante_api_fiap.Restaurante.Objects.dto.RestauranteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/dono-restaurante")
public class DonoController {

    @Autowired
    private DonoServiceImpl donoService;

    @Autowired
    private AuthService authService;

    public DonoController(DonoServiceImpl donoService, AuthService authService) {
        this.donoService = donoService;
        this.authService = authService;
    }

    @PostMapping("/cadastrar-restaurante")
    public ResponseEntity<RestauranteDTO> cadastrarRestaurante(
            @RequestBody RestauranteDAO restauranteRequest,
            @RequestHeader("Authorization") String token){
        return ResponseEntity.status(201)
                .body(donoService.cadastrarRestaurante(
                        (DonoEntity) authService.getUsuarioFromToken(token), restauranteRequest));
    }

    @GetMapping("/listar-restaurantes")
    public ResponseEntity<List<RestauranteDTO>> listarRestaurantes(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(donoService.listarMeusRestaurantes((DonoEntity) authService.getUsuarioFromToken(token)));
    }





}
