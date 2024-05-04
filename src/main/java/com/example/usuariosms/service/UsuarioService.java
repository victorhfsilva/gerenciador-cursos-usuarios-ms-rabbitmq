package com.example.usuariosms.service;

import com.example.usuariosms.mapper.UsuarioRegistrarUsuarioRequestMapper;
import com.example.usuariosms.mapper.UsuarioUsuarioResourceMapper;
import com.example.usuariosms.model.Usuario;
import com.example.usuariosms.model.dto.RegistrarUsuarioRequest;
import com.example.usuariosms.model.resources.UsuarioResource;
import com.example.usuariosms.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private UsuarioRegistrarUsuarioRequestMapper usuarioRegistrarUsuarioRequestMapper;
    private UsuarioUsuarioResourceMapper usuarioUsuarioResourceMapper;

    public Usuario save(RegistrarUsuarioRequest usuarioDto) {
        Usuario usuario = usuarioRegistrarUsuarioRequestMapper.registrarUsuarioRequestToUsuario(usuarioDto);
        return usuarioRepository.save(usuario);
    }

    public UsuarioResource findById(UUID id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        UsuarioResource usuarioResource = usuarioUsuarioResourceMapper.usuarioToUsuarioResource(usuario);
        //Link to Endereco
        return usuarioResource;
    }


}
