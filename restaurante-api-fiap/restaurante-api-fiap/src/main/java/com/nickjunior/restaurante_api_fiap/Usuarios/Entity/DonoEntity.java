package com.nickjunior.restaurante_api_fiap.Usuarios.Entity;

import com.nickjunior.restaurante_api_fiap.Abstract.Usuarios;
import com.nickjunior.restaurante_api_fiap.Restaurante.Entity.RestaurantEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "donos_restaurante")
public class DonoEntity extends Usuarios {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "dono_id")
    //private Long donoId;

    @OneToMany(mappedBy = "dono", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RestaurantEntity> restaurantes = new ArrayList<>();

    public DonoEntity(List<RestaurantEntity> restaurantes) {
        this.restaurantes = restaurantes;
    }
}
