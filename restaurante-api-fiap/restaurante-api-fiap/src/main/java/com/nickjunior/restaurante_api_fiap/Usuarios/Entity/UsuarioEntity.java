package com.nickjunior.restaurante_api_fiap.Usuarios.Entity;

import com.nickjunior.restaurante_api_fiap.Usuarios.enums.TipoUsuario;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
@Table(name = "usuarios")
public abstract class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;
    @NotBlank(message = "Nome é obrigatorio")
    private String nome;
    @Column(unique = true, nullable = false)
    private String email;
    @NotBlank(message = "Nome é obrigatorio")
    private String login;
    @NotBlank(message = "Nome é obrigatorio")
    private String senha;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<Long> restaurantesFavoritados;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<Map<String, Object>> restaurantesAvaliados;

    @Column(name = "data_criacao")
    private String dataCriacao;
    @Column(name = "data_ultima_alteracao")
    private LocalDate dataUltimaAlteracao;


    public abstract TipoUsuario getTipo();

    public boolean isCliente() {
        return TipoUsuario.CLIENTE.equals(this.getTipo());
    }

    public boolean isDono() {
        return TipoUsuario.DONO.equals(this.getTipo());
    }

}
