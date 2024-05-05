package com.example.usuariosms.service;

import com.example.usuariosms.fixture.EnderecoFixture;
import com.example.usuariosms.fixture.EnderecoRequestFixture;
import com.example.usuariosms.fixture.EnderecoResourceFixture;
import com.example.usuariosms.fixture.UsuarioFixture;
import com.example.usuariosms.mapper.EnderecoEnderecoRequestMapper;
import com.example.usuariosms.mapper.EnderecoEnderecoResourceMapper;
import com.example.usuariosms.model.Endereco;
import com.example.usuariosms.model.Usuario;
import com.example.usuariosms.model.dto.EnderecoRequest;
import com.example.usuariosms.model.resources.EnderecoResource;
import com.example.usuariosms.repository.UsuarioRepository;
import com.example.usuariosms.service.impl.EnderecoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnderecoServiceTest {

    @InjectMocks
    private EnderecoService enderecoService;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private EnderecoEnderecoRequestMapper enderecoEnderecoRequestMapper;
    @Mock
    private EnderecoEnderecoResourceMapper enderecoEnderecoResourceMapper;

    @Test
    void updateTest() {
        Endereco endereco = EnderecoFixture.buildValido();
        Usuario usuario = UsuarioFixture.buildValido();
        EnderecoResource enderecoResource = EnderecoResourceFixture.buildValido();
        EnderecoRequest enderecoRequest = EnderecoRequestFixture.buildValido();

        when(enderecoEnderecoRequestMapper.enderecoRequestToEndereco(enderecoRequest)).thenReturn(endereco);
        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));
        when(enderecoEnderecoResourceMapper.enderecoToEnderecoResponse(any())).thenReturn(enderecoResource);

        EnderecoResource enderecoRetornado = enderecoService.update(usuario.getId(), enderecoRequest);
        assertEquals(enderecoRequest.cep(), enderecoRetornado.getCep());
    }

    @Test
    void findByIdTest() {
        Usuario usuario = UsuarioFixture.buildValido();
        EnderecoResource enderecoResource = EnderecoResourceFixture.buildValido();
        Endereco endereco = usuario.getEndereco();

        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));
        when(enderecoEnderecoResourceMapper.enderecoToEnderecoResponse(endereco)).thenReturn(enderecoResource);

        EnderecoResource enderecoRetornado = enderecoService.findByUsuarioId(usuario.getId());
        assertEquals(endereco.getCep(), enderecoRetornado.getCep());
    }
}
