package com.nickjunior.restaurante_api_fiap.Cliente.Service;

import com.nickjunior.restaurante_api_fiap.Cliente.Entity.ClienteEntity;

import java.util.List;
import java.util.Map;

public interface ClienteService {

    void favoritarRestaurante(Long restauranteId, ClienteEntity cliente);
    void desfavoritarRestaurante(Long restauranteId, ClienteEntity cliente);
    List<Long> listarFavoritos(ClienteEntity cliente);
    void avaliarRestaurante(Long restauranteId, ClienteEntity usuarioId, Integer nota, String comentario);
    List<Map<String, Object>> listarAvaliacoes(ClienteEntity cliente);

}
