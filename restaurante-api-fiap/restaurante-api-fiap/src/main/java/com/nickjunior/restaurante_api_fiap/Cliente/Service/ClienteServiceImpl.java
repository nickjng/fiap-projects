package com.nickjunior.restaurante_api_fiap.Cliente.Service;

import com.nickjunior.restaurante_api_fiap.Cliente.Entity.ClienteEntity;
import com.nickjunior.restaurante_api_fiap.Restaurante.Entity.RestaurantEntity;
import com.nickjunior.restaurante_api_fiap.Restaurante.Objects.dto.RestauranteDTO;
import com.nickjunior.restaurante_api_fiap.Restaurante.Repository.RestaurantRepository;
import com.nickjunior.restaurante_api_fiap.Restaurante.mapper.RestaurantMapper;
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
    private RestaurantMapper restaurantMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ClienteServiceImpl(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper, UsuarioRepository usuarioRepository) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
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
    public List<RestauranteDTO> listarFavoritos(ClienteEntity cliente) {
        if (cliente.getRestaurantesFavoritados() == null || cliente.getRestaurantesFavoritados().isEmpty()) {
            return Collections.emptyList();
        }
        List<RestaurantEntity> restaurantes = restaurantRepository.findAllById(cliente.getRestaurantesFavoritados());
        return restaurantes.stream()
                .map(restaurantMapper::toResponse)
                .toList();
    }

    @Override
    public void avaliarRestaurante(Long restauranteId, ClienteEntity cliente, Map<String, Object> avaliacaoData) {

        RestaurantEntity restaurante = restaurantRepository.findById(restauranteId)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        if (cliente.getRestaurantesAvaliados() == null) {
            cliente.setRestaurantesAvaliados(new ArrayList<>());
        }

        Map<String, Object> avaliacao = new HashMap<>();
        avaliacao.put("restauranteId", restauranteId);
        avaliacao.put("restauranteNome", restaurante.getNome());
        avaliacao.put("dataAvaliacao", LocalDateTime.now().toString());
        avaliacao.put("clienteId", cliente.getId());
        avaliacao.put("clienteNome", cliente.getNome());

        avaliacao.putAll(avaliacaoData);

        cliente.getRestaurantesAvaliados().add(avaliacao);
        usuarioRepository.save(cliente);

    }

    @Override
    public List<Map<String, Object>> listarAvaliacoes(ClienteEntity cliente) {
        return cliente.getRestaurantesAvaliados() != null ?
                new ArrayList<>(cliente.getRestaurantesAvaliados()) :
                Collections.emptyList();
    }
}
