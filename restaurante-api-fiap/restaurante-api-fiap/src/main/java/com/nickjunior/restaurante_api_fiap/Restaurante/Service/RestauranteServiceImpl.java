package com.nickjunior.restaurante_api_fiap.Restaurante.Service;

import com.nickjunior.restaurante_api_fiap.Restaurante.Entity.RestaurantEntity;
import com.nickjunior.restaurante_api_fiap.Restaurante.Objects.dto.RestauranteDTO;
import com.nickjunior.restaurante_api_fiap.Restaurante.Repository.RestaurantRepository;
import com.nickjunior.restaurante_api_fiap.Restaurante.mapper.RestaurantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteServiceImpl implements RestauranteService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantMapper restaurantMapper;

    public RestauranteServiceImpl(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }

    @Override
    public List<RestauranteDTO> listarRestaurantes() {
        List<RestaurantEntity> restaurantes = restaurantRepository.findAll();
        return restaurantes.stream()
                .map(restaurantMapper::toResponse)
                .toList();
    }

    @Override
    public RestauranteDTO buscarRestaurante(Long idRestaurante) {
        RestaurantEntity restaurantEncontrado = restaurantRepository.findById(idRestaurante)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado com ID: " + idRestaurante));
        return restaurantMapper.toResponse(restaurantEncontrado);
    }
}
