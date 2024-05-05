package com.example.usuariosms.service;

import com.example.usuariosms.mapper.EnderecoEnderecoRequestMapper;
import com.example.usuariosms.mapper.EnderecoEnderecoResourceMapper;
import com.example.usuariosms.model.Endereco;
import com.example.usuariosms.model.Usuario;
import com.example.usuariosms.model.dto.EnderecoRequest;
import com.example.usuariosms.model.resources.EnderecoResource;
import com.example.usuariosms.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EnderecoService {

    private EnderecoEnderecoRequestMapper enderecoEnderecoRequestMapper;
    private EnderecoEnderecoResourceMapper enderecoEnderecoResourceMapper;
    private UsuarioRepository usuarioRepository;

    public EnderecoResource findById(UUID usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow();

        EnderecoResource enderecoResource = enderecoEnderecoResourceMapper.enderecoToEnderecoResponse(usuario.getEndereco());

        //Link to Usuario
        //Link to self

        return enderecoResource;
    }

    public EnderecoResource update(UUID usuarioId, EnderecoRequest enderecoDto) {
        Endereco endereco = enderecoEnderecoRequestMapper.enderecoRequestToEndereco(enderecoDto);
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow();
        usuario.setEndereco(endereco);

        EnderecoResource enderecoResource = enderecoEnderecoResourceMapper.enderecoToEnderecoResponse(usuario.getEndereco());

        //Link to usuario
        //Link to self

        return enderecoResource;
    }
}
