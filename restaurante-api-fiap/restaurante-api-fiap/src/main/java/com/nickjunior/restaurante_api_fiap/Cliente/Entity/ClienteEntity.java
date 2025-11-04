package com.nickjunior.restaurante_api_fiap.Cliente.Entity;

import com.nickjunior.restaurante_api_fiap.Usuarios.Entity.UsuarioEntity;
import com.nickjunior.restaurante_api_fiap.Usuarios.enums.TipoUsuario;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.Map;

@Entity
@DiscriminatorValue("CLIENTE")
public class ClienteEntity extends UsuarioEntity {

    @Override
    public TipoUsuario getTipo() {
        return TipoUsuario.CLIENTE;
    }
}
