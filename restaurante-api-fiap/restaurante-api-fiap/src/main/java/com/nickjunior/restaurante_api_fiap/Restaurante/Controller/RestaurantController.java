package com.nickjunior.restaurante_api_fiap.Restaurante.Controller;

import com.nickjunior.restaurante_api_fiap.Restaurante.Objects.dto.RestauranteDTO;
import com.nickjunior.restaurante_api_fiap.Restaurante.Service.RestauranteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurante")
public class RestaurantController {

    @Autowired
    private RestauranteServiceImpl restauranteService;

    public RestaurantController(RestauranteServiceImpl restauranteService) {
        this.restauranteService = restauranteService;
    }

    @GetMapping()
    public ResponseEntity<List<RestauranteDTO>> listarRestaurantes(){
        return ResponseEntity.status(200).body(restauranteService.listarRestaurantes());
    }

    @GetMapping("/{idRestaurante}")
    public ResponseEntity<RestauranteDTO> buscarRestaurante(@PathVariable Long idRestaurante){
        return ResponseEntity.status(200).body(restauranteService.buscarRestaurante(idRestaurante));
    }





}
