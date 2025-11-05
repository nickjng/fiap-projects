package com.nickjunior.restaurante_api_fiap.Usuarios.Service;

import com.nickjunior.restaurante_api_fiap.Auth.Service.AuthServiceImpl;
import com.nickjunior.restaurante_api_fiap.Usuarios.Entity.UsuarioEntity;
import com.nickjunior.restaurante_api_fiap.Usuarios.Objetcs.dao.UsuarioDAO;
import com.nickjunior.restaurante_api_fiap.Usuarios.Objetcs.dto.UsuarioDTO;
import com.nickjunior.restaurante_api_fiap.Usuarios.Repository.UsuarioRepository;
import com.nickjunior.restaurante_api_fiap.Usuarios.enums.TipoUsuario;
import com.nickjunior.restaurante_api_fiap.Usuarios.mapper.UsuarioMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public UsuarioDTO cadastrarUsuario(UsuarioDAO usuarioRequest) {

        if (!isEmailValido(usuarioRequest.email())) {
            throw new RuntimeException("Email inválido");
        }

        Boolean userExist = usuarioRepository.existsByEmail(usuarioRequest.email());

        if(userExist){
          throw new RuntimeException("email já existe");
        }

        UsuarioEntity novoUsuario = usuarioRequest.tipo() == TipoUsuario.CLIENTE ?
                usuarioMapper.toClienteEntity(usuarioRequest) :
                usuarioMapper.toDonoEntity(usuarioRequest);

        String senhaBase64 = Base64.getEncoder().encodeToString(usuarioRequest.senha().getBytes());
        novoUsuario.setSenha(senhaBase64);
        novoUsuario.setDataCriacao(LocalDateTime.now());
        novoUsuario.setDataUltimaAlteracao(LocalDateTime.now());
        return usuarioMapper.toResponse(usuarioRepository.save(novoUsuario));
    }

    @Override
    public List<UsuarioDTO> listarUsuarios(String termo) {
        List<UsuarioEntity> usuarios = termo == null ?  usuarioRepository.findAll() :
                usuarioRepository.buscarPorQualquerDado(termo.trim());
        return usuarios.stream()
                .map(usuarioMapper::toResponse)
                .toList();
    }

    @Override
    public UsuarioDTO buscarUsuario(Long idUsuario) {
         UsuarioEntity usuarioEncontrado = usuarioRepository.findById(idUsuario)
                 .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + idUsuario));
        return usuarioMapper.toResponse(usuarioEncontrado);
    }

    @Override
    public UsuarioDTO atualizarUsuario(UsuarioDAO userPatch, UsuarioEntity usuario) {

        BeanUtils.copyProperties(userPatch, usuario, getNullPropertyNames(userPatch));

        if (userPatch.email() != null && !usuario.getEmail().equals(userPatch.email())) {
            if (usuarioRepository.existsByEmail(userPatch.email())) {
                throw new RuntimeException("Email já está em uso");
            }
        }

        if(userPatch.senha() != null && !usuario.getSenha().equals(userPatch.senha())){
            String senhaBase64 = Base64.getEncoder().encodeToString(userPatch.senha().getBytes());
            usuario.setSenha(senhaBase64);
        }

        UsuarioEntity usuarioAtualizado = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(usuarioAtualizado);
    }

    @Override
    public String deletarConta(UsuarioEntity usuario) {
        usuarioRepository.delete(usuario);
        return "Usuário deletado com sucesso!";
    }

    private String[] getNullPropertyNames(UsuarioDAO source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    private boolean isEmailValido(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email != null && email.matches(regex);
    }

}
