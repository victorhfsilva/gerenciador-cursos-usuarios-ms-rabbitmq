package com.example.usuariosms.service;

import com.example.usuariosms.fixture.EnderecoFixture;
import com.example.usuariosms.fixture.EnderecoRequestFixture;
import com.example.usuariosms.fixture.EnderecoResourceFixture;
import com.example.usuariosms.fixture.UsuarioFixture;
import com.example.usuariosms.mapper.EnderecoMapper;
import com.example.usuariosms.model.Endereco;
import com.example.usuariosms.model.Usuario;
import com.example.usuariosms.model.requests.EnderecoRequest;
import com.example.usuariosms.model.resources.EnderecoResource;
import com.example.usuariosms.repository.UsuarioRepository;
import com.example.usuariosms.service.impl.EnderecoServiceImpl;
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
class EnderecoServiceImplTest {

    @InjectMocks
    private EnderecoServiceImpl enderecoServiceImpl;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private EnderecoMapper enderecoMapper;

    @Test
    void updateTest() {
        Endereco endereco = EnderecoFixture.buildValido();
        Usuario usuario = UsuarioFixture.buildValido();
        EnderecoResource enderecoResource = EnderecoResourceFixture.buildValido();
        EnderecoRequest enderecoRequest = EnderecoRequestFixture.buildValido();

        when(enderecoMapper.map(enderecoRequest)).thenReturn(endereco);
        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));
        when(enderecoMapper.map(any(Endereco.class))).thenReturn(enderecoResource);

        EnderecoResource enderecoRetornado = enderecoServiceImpl.update(usuario.getId(), enderecoRequest);
        assertEquals(enderecoRequest.cep(), enderecoRetornado.getCep());
    }

    @Test
    void findByIdTest() {
        Usuario usuario = UsuarioFixture.buildValido();
        EnderecoResource enderecoResource = EnderecoResourceFixture.buildValido();
        Endereco endereco = usuario.getEndereco();

        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));
        when(enderecoMapper.map(endereco)).thenReturn(enderecoResource);

        EnderecoResource enderecoRetornado = enderecoServiceImpl.findByUsuarioId(usuario.getId());
        assertEquals(endereco.getCep(), enderecoRetornado.getCep());
    }
}
