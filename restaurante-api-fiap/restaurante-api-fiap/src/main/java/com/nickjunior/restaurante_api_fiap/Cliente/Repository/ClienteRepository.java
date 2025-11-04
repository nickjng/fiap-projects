package com.nickjunior.restaurante_api_fiap.Cliente.Repository;

import com.nickjunior.restaurante_api_fiap.Cliente.Entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

}
