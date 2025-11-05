package com.nickjunior.restaurante_api_fiap.Restaurante.mapper;

import com.nickjunior.restaurante_api_fiap.Restaurante.Entity.RestaurantEntity;
import com.nickjunior.restaurante_api_fiap.Restaurante.Objects.dao.RestauranteDAO;
import com.nickjunior.restaurante_api_fiap.Restaurante.Objects.dto.RestauranteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {


    RestauranteDTO toResponse(RestaurantEntity restaurantDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "dataUltimaAlteracao", ignore = true)
    @Mapping(target = "usuarioDono", ignore = true)
    RestaurantEntity toEntity(RestauranteDAO restauranteDAO);


}
