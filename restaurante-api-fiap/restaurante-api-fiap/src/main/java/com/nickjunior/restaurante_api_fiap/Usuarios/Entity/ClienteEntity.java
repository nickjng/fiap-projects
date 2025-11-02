package com.nickjunior.restaurante_api_fiap.Usuarios.Entity;

import com.nickjunior.restaurante_api_fiap.Abstract.Usuarios;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "clientes")
public class ClienteEntity extends Usuarios {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "cliente_id")
    //private Long clienteId;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<Long> restaurantesFavoritados;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<Map<String, Object>> restaurantesAvaliados;

    public ClienteEntity(List<Long> restaurantesFavoritados, List<Map<String, Object>> restaurantesAvaliados) {
        this.restaurantesFavoritados = restaurantesFavoritados;
        this.restaurantesAvaliados = restaurantesAvaliados;
    }
}
