package com.example.usuariosms.service;

import com.example.usuariosms.mapper.EnderecoEnderecoRequestMapper;
import com.example.usuariosms.mapper.UsuarioUsuarioRequestMapper;
import com.example.usuariosms.mapper.UsuarioUsuarioResourceMapper;
import com.example.usuariosms.model.Usuario;
import com.example.usuariosms.model.dto.UsuarioRequest;
import com.example.usuariosms.model.resources.UsuarioResource;
import com.example.usuariosms.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private UsuarioUsuarioRequestMapper usuarioUsuarioRequestMapper;
    private UsuarioUsuarioResourceMapper usuarioUsuarioResourceMapper;
    private EnderecoEnderecoRequestMapper enderecoEnderecoRequestMapper;

    public UsuarioResource save(UsuarioRequest usuarioDto) {
        Usuario usuario = usuarioUsuarioRequestMapper.usuarioRequestToUsuario(usuarioDto);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        UsuarioResource usuarioResource = usuarioUsuarioResourceMapper.usuarioToUsuarioResource(usuarioSalvo);

        //Link to Endereco
        //Link to self

        return usuarioResource;
    }

    public UsuarioResource findById(UUID id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();

        UsuarioResource usuarioResource = usuarioUsuarioResourceMapper.usuarioToUsuarioResource(usuario);

        //Link to Endereco
        //Link to self

        return usuarioResource;
    }

    public UsuarioResource update(UUID id, UsuarioRequest usuarioDto) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();

        atualizarUsuario(usuario, usuarioDto);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        UsuarioResource usuarioResource = usuarioUsuarioResourceMapper.usuarioToUsuarioResource(usuarioSalvo);

        //Link to Endereco
        //Link to self

        return usuarioResource;
    }

    private void atualizarUsuario(Usuario usuario, UsuarioRequest novoUsuario) {
        usuario.setNome(novoUsuario.nome());
        usuario.setSobrenome(novoUsuario.sobrenome());
        usuario.setCpf(novoUsuario.cpf());
        usuario.setSenha(novoUsuario.senha());
        usuario.setPapel(novoUsuario.papel());
        usuario.setDataNascimento(novoUsuario.dataNascimento());
        usuario.setEmail(novoUsuario.email());
        usuario.setCelular(novoUsuario.celular());
        usuario.setEndereco(enderecoEnderecoRequestMapper
                .enderecoRequestToEndereco(novoUsuario.endereco()));
    }


}
