package com.nickjunior.restaurante_api_fiap.Restaurante.Repository;

import com.nickjunior.restaurante_api_fiap.Restaurante.Entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

    List<RestaurantEntity> findByUsuarioDono_Id(Long donoId);

}
