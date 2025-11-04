package com.nickjunior.restaurante_api_fiap.Dono.Repository;

import com.nickjunior.restaurante_api_fiap.Usuarios.Entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DonoRepository extends JpaRepository<UsuarioEntity, Long> {

}
