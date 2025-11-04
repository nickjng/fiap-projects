package com.nickjunior.restaurante_api_fiap.Dono.Service;

import com.nickjunior.restaurante_api_fiap.Dono.Entity.DonoEntity;
import com.nickjunior.restaurante_api_fiap.Restaurante.Objects.dao.RestauranteDAO;
import com.nickjunior.restaurante_api_fiap.Restaurante.Objects.dto.RestauranteDTO;

import java.util.List;

public interface DonoService {

    RestauranteDTO cadastrarRestaurante(DonoEntity donoRestaurante, RestauranteDAO restauranteRequest);
    List<RestauranteDTO> listarMeusRestaurantes(DonoEntity donoRestaurante);

}
