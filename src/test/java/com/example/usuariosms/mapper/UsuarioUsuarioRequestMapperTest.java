package com.example.usuariosms.mapper;


import com.example.usuariosms.fixture.EnderecoFixture;
import com.example.usuariosms.fixture.UsuarioRequestFixture;
import com.example.usuariosms.model.Endereco;
import com.example.usuariosms.model.Usuario;
import com.example.usuariosms.model.dto.UsuarioRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class UsuarioUsuarioRequestMapperTest {

    @Autowired
    private UsuarioUsuarioRequestMapper usuarioUsuarioRequestMapper;

    @MockBean
    private EnderecoEnderecoRequestMapper enderecoEnderecoRequestMapper;

    @Test
    void testUsuarioUsuarioRequestMapper() {
        UsuarioRequest usuarioRequest = UsuarioRequestFixture.buildValido();
        Endereco endereco = EnderecoFixture.buildValido();
        when(enderecoEnderecoRequestMapper.enderecoRequestToEndereco(usuarioRequest.endereco())).thenReturn(endereco);

        Usuario usuario = usuarioUsuarioRequestMapper.usuarioRequestToUsuario(usuarioRequest);

        assertEquals(usuario.getNome(), usuarioRequest.nome());
        assertEquals(usuario.getSobrenome(), usuarioRequest.sobrenome());
        assertEquals(usuario.getCpf(), usuarioRequest.cpf());
        assertEquals(usuario.getPapel(), usuarioRequest.papel());
        assertEquals(usuario.getDataNascimento(), usuarioRequest.dataNascimento());
        assertEquals(usuario.getEmail(), usuarioRequest.email());
        assertEquals(usuario.getCelular(), usuarioRequest.celular());
        assertEquals(endereco, usuario.getEndereco());
    }

}
