package com.example.usuariosms.service;

import com.example.usuariosms.fixture.UsuarioRequestFixture;
import com.example.usuariosms.fixture.UsuarioFixture;
import com.example.usuariosms.fixture.UsuarioResourceFixture;
import com.example.usuariosms.mapper.EnderecoMapper;
import com.example.usuariosms.mapper.UsuarioMapper;
import com.example.usuariosms.model.Usuario;
import com.example.usuariosms.model.requests.EnderecoRequest;
import com.example.usuariosms.model.requests.UsuarioRequest;
import com.example.usuariosms.model.resources.UsuarioResource;
import com.example.usuariosms.repository.UsuarioRepository;
import com.example.usuariosms.service.impl.UsuarioServiceImpl;
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
class UsuarioServiceImplTest {

    @InjectMocks
    private UsuarioServiceImpl usuarioServiceImpl;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private UsuarioMapper usuarioMapper;
    @Mock
    private EnderecoMapper enderecoMapper;

    @Test
    void saveTest(){
        UsuarioRequest usuarioRequest = UsuarioRequestFixture.buildValido();
        Usuario usuario = UsuarioFixture.buildValido();

        when(usuarioMapper.map(usuarioRequest)).thenReturn(usuario);
        when(usuarioRepository.save(any())).thenReturn(usuario);
        when(usuarioMapper.map(usuario)).thenReturn(UsuarioResourceFixture.buildValido());

        UsuarioResource usuarioResource = usuarioServiceImpl.save(usuarioRequest);

        assertEquals(usuarioResource.getCpf(), usuarioRequest.cpf());
        assertNotNull(usuarioResource.getLink("self"));
        assertNotNull(usuarioResource.getLink("endereco"));
    }

    @Test
    void findByIdTest() {
        Usuario usuario = UsuarioFixture.buildValido();
        UsuarioResource usuarioResource = UsuarioResourceFixture.buildValido();

        when(usuarioRepository.findById(any())).thenReturn(Optional.of(usuario));
        when(usuarioMapper.map(usuario)).thenReturn(usuarioResource);
        UsuarioResource usuarioRetornado = usuarioServiceImpl.findById(UUID.randomUUID());

        assertEquals(usuario.getCpf(), usuarioRetornado.getCpf());
        assertNotNull(usuarioRetornado.getLink("self"));
        assertNotNull(usuarioRetornado.getLink("endereco"));
    }

    @Test
    void findByCpfTest() {
        Usuario usuario = UsuarioFixture.buildValido();
        UsuarioResource usuarioResource = UsuarioResourceFixture.buildValido();

        when(usuarioRepository.findByCpf(any())).thenReturn(Optional.of(usuario));
        when(usuarioMapper.map(usuario)).thenReturn(usuarioResource);
        UsuarioResource usuarioRetornado = usuarioServiceImpl.findByCpf(usuario.getCpf());

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
        when(usuarioMapper.map(any(Usuario.class)))
                .thenReturn(usuarioResource);

        Page<UsuarioResource> paginaUsuariosRetornada = usuarioServiceImpl.findAll(pageable);

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
        when(enderecoMapper.map(any(EnderecoRequest.class))).thenReturn(usuario.getEndereco());
        when(usuarioMapper.map(usuario)).thenReturn(usuarioResource);

        UsuarioResource usuarioRetornado = usuarioServiceImpl.update(UUID.randomUUID(), usuarioRequest);

        assertEquals(usuario.getCpf(), usuarioRetornado.getCpf());
        assertNotNull(usuarioRetornado.getLink("self"));
        assertNotNull(usuarioRetornado.getLink("endereco"));
    }

    @Test
    void deleteTest() {
        Usuario usuario = UsuarioFixture.buildValido();
        UsuarioResource usuarioResource = UsuarioResourceFixture.buildValido();

        when(usuarioRepository.findById(any())).thenReturn(Optional.of(usuario));
        when(usuarioMapper.map(usuario)).thenReturn(usuarioResource);

        UsuarioResource usuarioRetornado = usuarioServiceImpl.delete(UUID.randomUUID());

        assertEquals(usuario.getCpf(), usuarioRetornado.getCpf());
        assertNotNull(usuarioRetornado.getLink("self"));
        assertNotNull(usuarioRetornado.getLink("endereco"));
    }

}
