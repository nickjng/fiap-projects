package com.nickjunior.restaurante_api_fiap.Restaurante.Service;

import com.nickjunior.restaurante_api_fiap.Restaurante.Objects.dto.RestauranteDTO;

import java.util.List;
import java.util.Optional;

public interface RestauranteService {

    List<RestauranteDTO> listarRestaurantes();
    RestauranteDTO buscarRestaurante(Long idRestaurante);

}
