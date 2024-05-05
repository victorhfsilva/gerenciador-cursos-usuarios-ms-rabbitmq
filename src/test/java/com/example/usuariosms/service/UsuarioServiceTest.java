package com.example.usuariosms.service;

import com.example.usuariosms.fixture.UsuarioRequestFixture;
import com.example.usuariosms.fixture.UsuarioFixture;
import com.example.usuariosms.fixture.UsuarioResourceFixture;
import com.example.usuariosms.mapper.EnderecoEnderecoRequestMapper;
import com.example.usuariosms.mapper.UsuarioUsuarioRequestMapper;
import com.example.usuariosms.mapper.UsuarioUsuarioResourceMapper;
import com.example.usuariosms.model.Usuario;
import com.example.usuariosms.model.dto.UsuarioRequest;
import com.example.usuariosms.model.resources.UsuarioResource;
import com.example.usuariosms.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private UsuarioUsuarioResourceMapper usuarioUsuarioResourceMapper;
    @Mock
    private UsuarioUsuarioRequestMapper usuarioUsuarioRequestMapper;
    @Mock
    private EnderecoEnderecoRequestMapper enderecoEnderecoRequestMapper;

    @Test
    void saveTest(){
        UsuarioRequest usuarioRequest = UsuarioRequestFixture.buildValido();
        Usuario usuario = UsuarioFixture.buildValido();

        when(usuarioUsuarioRequestMapper.usuarioRequestToUsuario(usuarioRequest)).thenReturn(usuario);
        when(usuarioRepository.save(any())).thenReturn(usuario);
        when(usuarioUsuarioResourceMapper.usuarioToUsuarioResource(usuario)).thenReturn(UsuarioResourceFixture.buildValido());

        UsuarioResource usuarioResource = usuarioService.save(usuarioRequest);

        assertEquals(usuarioResource.getCpf(), usuarioRequest.cpf());
    }

    @Test
    void findByIdTest() {
        Usuario usuario = UsuarioFixture.buildValido();
        UsuarioResource usuarioResource = UsuarioResourceFixture.buildValido();

        when(usuarioRepository.findById(any())).thenReturn(Optional.of(usuario));
        when(usuarioUsuarioResourceMapper.usuarioToUsuarioResource(usuario)).thenReturn(usuarioResource);
        UsuarioResource usuarioRetornado = usuarioService.findById(UUID.randomUUID());

        assertEquals(usuario.getCpf(), usuarioRetornado.getCpf());
    }

    @Test
    void updateTest() {
        Usuario usuario = UsuarioFixture.buildValido();
        UsuarioRequest usuarioRequest = UsuarioRequestFixture.buildValido();
        UsuarioResource usuarioResource = UsuarioResourceFixture.buildValido();

        when(usuarioRepository.findById(any())).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any())).thenReturn(usuario);
        when(enderecoEnderecoRequestMapper.enderecoRequestToEndereco(any())).thenReturn(usuario.getEndereco());
        when(usuarioUsuarioResourceMapper.usuarioToUsuarioResource(usuario)).thenReturn(usuarioResource);

        UsuarioResource usuarioRetornado = usuarioService.update(UUID.randomUUID(), usuarioRequest);

        assertEquals(usuario.getCpf(), usuarioRetornado.getCpf());
    }

}
