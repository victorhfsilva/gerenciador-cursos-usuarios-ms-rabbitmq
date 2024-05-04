package com.example.usuariosms.service;

import com.example.usuariosms.mapper.UsuarioRegistrarUsuarioRequestMapper;
import com.example.usuariosms.model.Usuario;
import com.example.usuariosms.model.dto.RegistrarUsuarioRequest;
import com.example.usuariosms.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private UsuarioRegistrarUsuarioRequestMapper usuarioRegistrarUsuarioRequestMapper;

    public Usuario save(RegistrarUsuarioRequest usuarioDto) {
        Usuario usuario = usuarioRegistrarUsuarioRequestMapper.registrarUsuarioRequestToUsuario(usuarioDto);
        return usuarioRepository.save(usuario);
    }
}
