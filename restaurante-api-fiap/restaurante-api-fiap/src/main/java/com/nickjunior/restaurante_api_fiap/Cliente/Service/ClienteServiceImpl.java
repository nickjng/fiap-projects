package com.nickjunior.restaurante_api_fiap.Cliente.Service;

import com.nickjunior.restaurante_api_fiap.Cliente.Entity.ClienteEntity;
import com.nickjunior.restaurante_api_fiap.Restaurante.Entity.RestaurantEntity;
import com.nickjunior.restaurante_api_fiap.Restaurante.Repository.RestaurantRepository;
import com.nickjunior.restaurante_api_fiap.Usuarios.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ClienteServiceImpl(RestaurantRepository restaurantRepository, UsuarioRepository usuarioRepository) {
        this.restaurantRepository = restaurantRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void favoritarRestaurante(Long restauranteId, ClienteEntity cliente) {

        RestaurantEntity restaurante = restaurantRepository.findById(restauranteId)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        if (cliente.getRestaurantesFavoritados() == null) {
            cliente.setRestaurantesFavoritados(new ArrayList<>());
        }

        if (cliente.getRestaurantesFavoritados().contains(restauranteId)) {
            throw new RuntimeException("Restaurante já está nos favoritos");
        }

        cliente.getRestaurantesFavoritados().add(restauranteId);
        usuarioRepository.save(cliente);
    }

    @Override
    public void desfavoritarRestaurante(Long restauranteId, ClienteEntity cliente) {

        if (cliente.getRestaurantesFavoritados() == null ||
                cliente.getRestaurantesFavoritados().isEmpty()) {
            throw new RuntimeException("Nenhum restaurante favoritado");
        }

        boolean removido = cliente.getRestaurantesFavoritados().remove(restauranteId);

        if (!removido) {
            throw new RuntimeException("Restaurante não estava nos favoritos");
        }

        usuarioRepository.save(cliente);
    }

    @Override
    public List<Long> listarFavoritos(ClienteEntity cliente) {
        return cliente.getRestaurantesFavoritados() != null ?
                cliente.getRestaurantesFavoritados() :
                Collections.emptyList();
    }

    @Override
    public void avaliarRestaurante(Long restauranteId, ClienteEntity cliente, Integer nota, String comentario) {
        if (nota < 1 || nota > 5) {
            throw new RuntimeException("Nota deve ser entre 1 e 5");
        }

        RestaurantEntity restaurante = restaurantRepository.findById(restauranteId)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        if (cliente.getRestaurantesAvaliados() == null) {
            cliente.setRestaurantesAvaliados(new ArrayList<>());
        }

        Map<String, Object> avaliacao = new HashMap<>();
        avaliacao.put("restauranteId", restauranteId);
        avaliacao.put("nota", nota);
        avaliacao.put("comentario", comentario != null ? comentario : "");
        avaliacao.put("dataAvaliacao", LocalDateTime.now());
        avaliacao.put("clienteNome", cliente.getNome());

        cliente.getRestaurantesAvaliados().add(avaliacao);
        usuarioRepository.save(cliente);

    }

    @Override
    public List<Map<String, Object>> listarAvaliacoes(ClienteEntity cliente) {
        return cliente.getRestaurantesAvaliados() != null ?
                cliente.getRestaurantesAvaliados() :
                Collections.emptyList();
    }
}
