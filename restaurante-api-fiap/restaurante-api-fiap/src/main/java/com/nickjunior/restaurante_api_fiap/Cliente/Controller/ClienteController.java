package com.nickjunior.restaurante_api_fiap.Cliente.Controller;

import com.nickjunior.restaurante_api_fiap.Auth.Service.AuthServiceImpl;
import com.nickjunior.restaurante_api_fiap.Cliente.Entity.ClienteEntity;
import com.nickjunior.restaurante_api_fiap.Cliente.Service.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteServiceImpl clienteService;

    @Autowired
    private AuthServiceImpl authServiceImpl;

    @PostMapping("/favoritar/{restauranteId}")
    public ResponseEntity<String> favoritarRestaurante(@PathVariable Long restauranteId,
                                                       @RequestHeader("Autrorization") String token){
        clienteService.favoritarRestaurante(restauranteId, (ClienteEntity) authServiceImpl.getUsuarioFromToken(token));
        return ResponseEntity.ok("Restaurante favoritado");
    }

    @PostMapping("/desfavoritar/{restauranteId}")
    public ResponseEntity<String> desfavoritarRestaurante(@PathVariable Long restauranteId,
                                                       @RequestHeader("Autrorization") String token){
        clienteService.desfavoritarRestaurante(restauranteId, (ClienteEntity) authServiceImpl.getUsuarioFromToken(token));
        return ResponseEntity.ok("Restaurante favoritado");
    }

    @GetMapping("/favoritos")
    public ResponseEntity<List<Long>> listarFavoritos(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(clienteService.listarFavoritos((ClienteEntity) authServiceImpl.getUsuarioFromToken(token)));
    }

    @PostMapping("/avaliar/{restauranteId}")
    public ResponseEntity<String> avaliarRestaurante(
            @RequestHeader("Authorization") String token,
            @PathVariable Long restauranteId,
            @RequestParam Integer nota,
            @RequestParam String comentario) {

        clienteService.avaliarRestaurante(restauranteId,(ClienteEntity) authServiceImpl.getUsuarioFromToken(token), nota, comentario);
        return ResponseEntity.ok("Avaliação registrada!");
    }

    @GetMapping("/avaliacoes")
    public ResponseEntity<List<Map<String, Object>>> listarAvaliacoes(@RequestHeader("Authorization") String token){
        return ResponseEntity.status(200).body(clienteService.listarAvaliacoes((ClienteEntity) authServiceImpl.getUsuarioFromToken(token)));
    }


}
