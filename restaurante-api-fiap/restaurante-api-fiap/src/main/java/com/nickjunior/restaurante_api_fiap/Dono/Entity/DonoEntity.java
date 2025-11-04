package com.nickjunior.restaurante_api_fiap.Dono.Entity;

import com.nickjunior.restaurante_api_fiap.Restaurante.Entity.RestaurantEntity;
import com.nickjunior.restaurante_api_fiap.Usuarios.Entity.UsuarioEntity;
import com.nickjunior.restaurante_api_fiap.Usuarios.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@DiscriminatorValue("DONO")
public class DonoEntity extends UsuarioEntity {

    @Override
    public TipoUsuario getTipo() {
        return TipoUsuario.DONO;
    }
}
