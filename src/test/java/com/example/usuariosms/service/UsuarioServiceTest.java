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
import com.example.usuariosms.service.impl.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        assertNotNull(usuarioResource.getLink("self"));
        assertNotNull(usuarioResource.getLink("endereco"));
    }

    @Test
    void findByIdTest() {
        Usuario usuario = UsuarioFixture.buildValido();
        UsuarioResource usuarioResource = UsuarioResourceFixture.buildValido();

        when(usuarioRepository.findById(any())).thenReturn(Optional.of(usuario));
        when(usuarioUsuarioResourceMapper.usuarioToUsuarioResource(usuario)).thenReturn(usuarioResource);
        UsuarioResource usuarioRetornado = usuarioService.findById(UUID.randomUUID());

        assertEquals(usuario.getCpf(), usuarioRetornado.getCpf());
        assertNotNull(usuarioRetornado.getLink("self"));
        assertNotNull(usuarioRetornado.getLink("endereco"));
    }

    @Test
    public void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Usuario> usuarios = List.of(UsuarioFixture.buildValido());
        Page<Usuario> paginaUsuarios = new PageImpl<>(usuarios, pageable, usuarios.size());
        UsuarioResource usuarioResource = UsuarioResourceFixture.buildValido();

        when(usuarioRepository.findAll(pageable)).thenReturn(paginaUsuarios);
        when(usuarioUsuarioResourceMapper.usuarioToUsuarioResource(any(Usuario.class)))
                .thenReturn(usuarioResource);

        Page<UsuarioResource> paginaUsuariosRetornada = usuarioService.findAll(pageable);

        assertEquals(paginaUsuarios.getTotalElements(), paginaUsuariosRetornada.getTotalElements());
        assertEquals(paginaUsuarios.getTotalPages(), paginaUsuariosRetornada.getTotalPages());
        assertEquals(paginaUsuarios.getContent().size(), paginaUsuariosRetornada.getContent().size());
        assertNotNull(paginaUsuariosRetornada.getContent().get(0).getLink("self"));
        assertNotNull(paginaUsuariosRetornada.getContent().get(0).getLink("endereco"));
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
        assertNotNull(usuarioRetornado.getLink("self"));
        assertNotNull(usuarioRetornado.getLink("endereco"));
    }

    @Test
    void deleteTest() {
        Usuario usuario = UsuarioFixture.buildValido();
        UsuarioResource usuarioResource = UsuarioResourceFixture.buildValido();

        when(usuarioRepository.findById(any())).thenReturn(Optional.of(usuario));
        when(usuarioUsuarioResourceMapper.usuarioToUsuarioResource(usuario)).thenReturn(usuarioResource);

        UsuarioResource usuarioRetornado = usuarioService.delete(UUID.randomUUID());

        assertEquals(usuario.getCpf(), usuarioRetornado.getCpf());
        assertNotNull(usuarioRetornado.getLink("self"));
        assertNotNull(usuarioRetornado.getLink("endereco"));
    }

}
