package com.nickjunior.restaurante_api_fiap.Cliente.Service;

import com.nickjunior.restaurante_api_fiap.Cliente.Entity.ClienteEntity;
import com.nickjunior.restaurante_api_fiap.Restaurante.Objects.dto.RestauranteDTO;

import java.util.List;
import java.util.Map;

public interface ClienteService {

    void favoritarRestaurante(Long restauranteId, ClienteEntity cliente);
    void desfavoritarRestaurante(Long restauranteId, ClienteEntity cliente);
    List<RestauranteDTO> listarFavoritos(ClienteEntity cliente);
    void avaliarRestaurante(Long restauranteId, ClienteEntity usuarioId, Map<String, Object> avaliacaoData);
    List<Map<String, Object>> listarAvaliacoes(ClienteEntity cliente);

}
