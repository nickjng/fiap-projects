package com.nickjunior.restaurante_api_fiap.Abstract;

import com.nickjunior.restaurante_api_fiap.enums.TipoUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuarios")
public abstract class Usuarios {

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuario tipo;

    @Column(name = "data_criacao")
    private String dataCriacao;
    @Column(name = "data_ultima_alteracao")
    private LocalDate dataUltimaAlteracao;
    // private Endereco;

    public void atualizarDadosPatch(){
        System.out.println("0");
    }


}
