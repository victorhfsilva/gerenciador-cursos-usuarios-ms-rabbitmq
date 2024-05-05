package com.example.usuariosms.service.impl;

import com.example.usuariosms.controller.EnderecoController;
import com.example.usuariosms.controller.UsuarioController;
import com.example.usuariosms.mapper.EnderecoEnderecoRequestMapper;
import com.example.usuariosms.mapper.UsuarioUsuarioRequestMapper;
import com.example.usuariosms.mapper.UsuarioUsuarioResourceMapper;
import com.example.usuariosms.model.Usuario;
import com.example.usuariosms.model.dto.UsuarioRequest;
import com.example.usuariosms.model.resources.UsuarioResource;
import com.example.usuariosms.repository.UsuarioRepository;
import com.example.usuariosms.service.IUsuarioService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.UUID;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
public class UsuarioService implements IUsuarioService {

    private UsuarioRepository usuarioRepository;
    private UsuarioUsuarioRequestMapper usuarioUsuarioRequestMapper;
    private UsuarioUsuarioResourceMapper usuarioUsuarioResourceMapper;
    private EnderecoEnderecoRequestMapper enderecoEnderecoRequestMapper;

    @Transactional
    public UsuarioResource save(UsuarioRequest usuarioDto) {
        Usuario usuario = usuarioUsuarioRequestMapper.usuarioRequestToUsuario(usuarioDto);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        UsuarioResource usuarioResource = usuarioUsuarioResourceMapper.usuarioToUsuarioResource(usuarioSalvo);

        usuarioResource.add(linkTo(methodOn(UsuarioController.class).registrarUsuario(usuarioDto)).withSelfRel());
        usuarioResource.add(linkTo(methodOn(EnderecoController.class).buscarEnderecosPorUsuarioId(usuarioSalvo.getId())).withRel("endereco"));

        return usuarioResource;
    }

    public UsuarioResource findById(UUID id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();

        UsuarioResource usuarioResource = usuarioUsuarioResourceMapper.usuarioToUsuarioResource(usuario);

        usuarioResource.add(linkTo(methodOn(UsuarioController.class).buscarUsuarioPorId(id)).withSelfRel());
        usuarioResource.add(linkTo(methodOn(EnderecoController.class).buscarEnderecosPorUsuarioId(id)).withRel("endereco"));

        return usuarioResource;
    }

    public Page<UsuarioResource> findAll(Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);

        Page<UsuarioResource> usuarioResources = usuarios
                .map(usuario ->
                        usuarioUsuarioResourceMapper
                                .usuarioToUsuarioResource(usuario)
                                .add(linkTo(methodOn(UsuarioController.class).buscarUsuarioPorId(usuario.getId())).withSelfRel())
                                .add(linkTo(methodOn(EnderecoController.class).buscarEnderecosPorUsuarioId(usuario.getId())).withRel("endereco")));

        return usuarioResources;
    }

    @Transactional
    public UsuarioResource update(UUID id, UsuarioRequest usuarioDto) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();

        atualizarUsuario(usuario, usuarioDto);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        UsuarioResource usuarioResource = usuarioUsuarioResourceMapper.usuarioToUsuarioResource(usuarioSalvo);

        usuarioResource.add(linkTo(methodOn(UsuarioController.class).atualizarUsuario(id, usuarioDto)).withSelfRel());
        usuarioResource.add(linkTo(methodOn(EnderecoController.class).buscarEnderecosPorUsuarioId(id)).withRel("endereco"));

        return usuarioResource;
    }

    @Transactional
    public UsuarioResource delete(UUID id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();

        usuarioRepository.delete(usuario);

        UsuarioResource usuarioResource = usuarioUsuarioResourceMapper.usuarioToUsuarioResource(usuario);

        usuarioResource.add(linkTo(methodOn(UsuarioController.class).deletarUsuario(id)).withSelfRel());

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
