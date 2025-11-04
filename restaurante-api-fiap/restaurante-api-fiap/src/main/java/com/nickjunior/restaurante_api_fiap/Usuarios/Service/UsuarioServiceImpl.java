package com.nickjunior.restaurante_api_fiap.Usuarios.Service;

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
        Boolean userExist = usuarioRepository.existsByEmail(usuarioRequest.email());

        if(userExist){
          throw new RuntimeException("email já existe");
        }

        UsuarioEntity novoUsuario = usuarioRequest.tipo() == TipoUsuario.CLIENTE ?
                usuarioMapper.toClienteEntity(usuarioRequest) :
                usuarioMapper.toDonoEntity(usuarioRequest);

        if(usuarioRequest.tipo() == TipoUsuario.CLIENTE){
            novoUsuario =  usuarioMapper.toClienteEntity(usuarioRequest);
        }
        return usuarioMapper.toResponse(usuarioRepository.save(novoUsuario));
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
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
    public UsuarioDTO atualizarUsuario(UsuarioDAO userPatch, Long idUsuario) {
        UsuarioEntity usuarioEncontrado = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + idUsuario));

        BeanUtils.copyProperties(userPatch, usuarioEncontrado, getNullPropertyNames(userPatch));

        if (userPatch.email() != null && !usuarioEncontrado.getEmail().equals(userPatch.email())) {
            if (usuarioRepository.existsByEmail(userPatch.email())) {
                throw new RuntimeException("Email já está em uso");
            }
        }

        UsuarioEntity usuarioAtualizado = usuarioRepository.save(usuarioEncontrado);
        return usuarioMapper.toResponse(usuarioAtualizado);
    }

    @Override
    public String deletarConta(Long idUsuario) {
        if (!usuarioRepository.existsById(Long.valueOf(idUsuario))) {
            throw new RuntimeException("Usuário não encontrado com ID: " + idUsuario);
        }
        usuarioRepository.deleteById(Long.valueOf(idUsuario));
        return "Usuário deletado com sucesso!";
    }

    public String[] getNullPropertyNames(UsuarioDAO source) {
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

}
