package com.example.usuariosms.service;

import com.example.usuariosms.fixture.RegistrarUsuarioRequestFixture;
import com.example.usuariosms.fixture.UsuarioFixture;
import com.example.usuariosms.fixture.UsuarioResourceFixture;
import com.example.usuariosms.mapper.UsuarioRegistrarUsuarioRequestMapper;
import com.example.usuariosms.mapper.UsuarioUsuarioResourceMapper;
import com.example.usuariosms.model.Usuario;
import com.example.usuariosms.model.dto.RegistrarUsuarioRequest;
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
    private UsuarioRegistrarUsuarioRequestMapper usuarioRegistrarUsuarioRequestMapper;

    @Test
    void salvarUsuarioTest(){
        RegistrarUsuarioRequest registrarUsuarioRequest = RegistrarUsuarioRequestFixture.buildValido();
        Usuario usuario = UsuarioFixture.buildValido();

        when(usuarioRegistrarUsuarioRequestMapper.registrarUsuarioRequestToUsuario(registrarUsuarioRequest)).thenReturn(usuario);
        when(usuarioRepository.save(any())).thenReturn(usuario);
        Usuario usuarioSalvo = usuarioService.save(registrarUsuarioRequest);

        assertEquals(usuarioSalvo.getCpf(), registrarUsuarioRequest.cpf());
    }

    @Test
    void findByIdShouldReturnUserWhenUserExists() {
        Usuario usuario = UsuarioFixture.buildValido();
        UsuarioResource usuarioResource = UsuarioResourceFixture.buildValido();

        when(usuarioRepository.findById(any())).thenReturn(Optional.of(usuario));
        when(usuarioUsuarioResourceMapper.usuarioToUsuarioResource(usuario)).thenReturn(usuarioResource);
        UsuarioResource usuarioRetornado = usuarioService.findById(UUID.randomUUID());

        assertEquals(usuario.getCpf(), usuarioRetornado.getCpf());
    }

}
