package com.nickjunior.restaurante_api_fiap.Dono.Service;

import com.nickjunior.restaurante_api_fiap.Dono.Entity.DonoEntity;
import com.nickjunior.restaurante_api_fiap.Restaurante.Entity.RestaurantEntity;
import com.nickjunior.restaurante_api_fiap.Restaurante.Objects.dao.RestauranteDAO;
import com.nickjunior.restaurante_api_fiap.Restaurante.Objects.dto.RestauranteDTO;
import com.nickjunior.restaurante_api_fiap.Restaurante.Repository.RestaurantRepository;
import com.nickjunior.restaurante_api_fiap.Restaurante.mapper.RestaurantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonoServiceImpl implements DonoService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantMapper restaurantMapper;

    public DonoServiceImpl(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }

    @Override
    public RestauranteDTO cadastrarRestaurante(DonoEntity donoRestaurante, RestauranteDAO restauranteRequest) {
        RestaurantEntity novoRestaurante = restaurantMapper.toEntity(restauranteRequest);
        return restaurantMapper.toResponse(restaurantRepository.save(novoRestaurante));
    }

    @Override
    public List<RestauranteDTO> listarMeusRestaurantes(DonoEntity donoRestaurante) {
        List<RestaurantEntity> restaurantes = restaurantRepository.findByUsuarioDono_Id(donoRestaurante.getId());
        return restaurantes.stream()
                .map(restaurantMapper::toResponse)
                .toList();
    }
}
