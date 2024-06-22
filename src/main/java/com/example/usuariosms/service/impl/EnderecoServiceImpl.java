package com.example.usuariosms.service.impl;

import com.example.usuariosms.controller.EnderecoController;
import com.example.usuariosms.controller.UsuarioController;
import com.example.usuariosms.mapper.EnderecoMapper;
import com.example.usuariosms.model.Endereco;
import com.example.usuariosms.model.Usuario;
import com.example.usuariosms.model.requests.EnderecoRequest;
import com.example.usuariosms.model.resources.EnderecoResource;
import com.example.usuariosms.repository.UsuarioRepository;
import com.example.usuariosms.service.EnderecoService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
public class EnderecoServiceImpl implements EnderecoService {

    private EnderecoMapper enderecoMapper;
    private UsuarioRepository usuarioRepository;

    public EnderecoResource findByUsuarioId(UUID usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow();

        EnderecoResource enderecoResource = enderecoMapper.map(usuario.getEndereco());

        enderecoResource.add(linkTo(methodOn(EnderecoController.class).buscarEnderecosPorUsuarioId(usuarioId)).withSelfRel());
        enderecoResource.add(linkTo(methodOn(UsuarioController.class).buscarUsuarioPorId(usuarioId)).withRel("usuario"));

        return enderecoResource;
    }

    @Transactional
    public EnderecoResource update(UUID usuarioId, EnderecoRequest enderecoDto) {
        Endereco endereco = enderecoMapper.map(enderecoDto);
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow();
        usuario.setEndereco(endereco);
        usuarioRepository.save(usuario);

        EnderecoResource enderecoResource = enderecoMapper.map(usuario.getEndereco());

        enderecoResource.add(linkTo(methodOn(EnderecoController.class).atualizarEndereco(usuarioId, enderecoDto)).withSelfRel());
        enderecoResource.add(linkTo(methodOn(UsuarioController.class).buscarUsuarioPorId(usuarioId)).withRel("usuario"));

        return enderecoResource;
    }
}
