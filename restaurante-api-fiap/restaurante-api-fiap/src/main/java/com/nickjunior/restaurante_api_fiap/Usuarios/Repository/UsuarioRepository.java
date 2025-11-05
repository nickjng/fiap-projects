package com.nickjunior.restaurante_api_fiap.Usuarios.Repository;

import com.nickjunior.restaurante_api_fiap.Cliente.Entity.ClienteEntity;
import com.nickjunior.restaurante_api_fiap.Usuarios.Entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query("SELECT u FROM UsuarioEntity u WHERE " +
            "LOWER(u.nome) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
            "LOWER(u.email) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
            "LOWER(u.login) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<UsuarioEntity> buscarPorQualquerDado(@Param("termo") String termo);


}
