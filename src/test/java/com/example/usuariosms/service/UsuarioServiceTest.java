package com.example.usuariosms.service;

import com.example.usuariosms.fixture.RegistrarEnderecoRequestFixture;
import com.example.usuariosms.fixture.RegistrarUsuarioRequestFixture;
import com.example.usuariosms.fixture.UsuarioFixture;
import com.example.usuariosms.mapper.UsuarioRegistrarUsuarioRequestMapper;
import com.example.usuariosms.model.Usuario;
import com.example.usuariosms.model.dto.RegistrarEnderecoRequest;
import com.example.usuariosms.model.dto.RegistrarUsuarioRequest;
import com.example.usuariosms.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

}
