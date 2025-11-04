package com.nickjunior.restaurante_api_fiap.Usuarios.mapper;

import com.nickjunior.restaurante_api_fiap.Cliente.Entity.ClienteEntity;
import com.nickjunior.restaurante_api_fiap.Dono.Entity.DonoEntity;
import com.nickjunior.restaurante_api_fiap.Usuarios.Entity.UsuarioEntity;
import com.nickjunior.restaurante_api_fiap.Usuarios.Objetcs.dao.UsuarioDAO;
import com.nickjunior.restaurante_api_fiap.Usuarios.Objetcs.dto.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "dataUltimaAlteracao", ignore = true)
    @Mapping(target = "restaurantesFavoritados", ignore = true)
    @Mapping(target = "restaurantesAvaliados", ignore = true)
    ClienteEntity toClienteEntity(UsuarioDAO request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "dataUltimaAlteracao", ignore = true)
    @Mapping(target = "restaurantesFavoritados", ignore = true)
    @Mapping(target = "restaurantesAvaliados", ignore = true)
    DonoEntity toDonoEntity(UsuarioDAO request);

    @Mapping(target = "tipo", expression = "java(entity.getTipo())")
    UsuarioDTO toResponse(UsuarioEntity entity);

}
