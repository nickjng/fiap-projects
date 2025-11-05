package com.nickjunior.restaurante_api_fiap.Usuarios.Entity;

import com.nickjunior.restaurante_api_fiap.Usuarios.enums.TipoUsuario;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
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

    private String endereco;

    @Column(unique = true, nullable = false)
    @Email
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
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_alteracao")
    private LocalDateTime dataUltimaAlteracao;




    public abstract TipoUsuario getTipo();

    public boolean isCliente() {
        return TipoUsuario.CLIENTE.equals(this.getTipo());
    }

    public boolean isDono() {
        return TipoUsuario.DONO.equals(this.getTipo());
    }

}
