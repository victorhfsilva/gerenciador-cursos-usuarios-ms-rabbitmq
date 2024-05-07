package com.example.usuariosms.controller;

import com.example.usuariosms.fixture.UsuarioFixture;
import com.example.usuariosms.fixture.UsuarioRequestFixture;
import com.example.usuariosms.fixture.UsuarioResourceFixture;
import com.example.usuariosms.model.Usuario;
import com.example.usuariosms.model.dto.UsuarioRequest;
import com.example.usuariosms.model.resources.UsuarioResource;
import com.example.usuariosms.service.impl.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void buscarUsuarioPorIdTest() throws Exception {
        UsuarioResource usuarioResource = UsuarioResourceFixture.buildValido();

        when(usuarioService.findById(any(UUID.class))).thenReturn(usuarioResource);

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/{id}", UUID.randomUUID()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(usuarioResource.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(usuarioResource.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sobrenome").value(usuarioResource.getSobrenome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value(usuarioResource.getCpf()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.papel").value(usuarioResource.getPapel().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataNascimento").value(usuarioResource.getDataNascimento().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(usuarioResource.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.celular").value(usuarioResource.getCelular()));
    }

    @Test
    void buscarTodosUsuariosTest() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        List<UsuarioResource> usuarioResources = List.of(UsuarioResourceFixture.buildValido());
        Page<UsuarioResource> paginaUsuarioResources = new PageImpl<>(usuarioResources, pageable, usuarioResources.size());

        when(usuarioService.findAll(any(Pageable.class))).thenReturn(paginaUsuarioResources);

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuarioResourceList.[0].id").value(usuarioResources.get(0).getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuarioResourceList.[0].nome").value(usuarioResources.get(0).getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuarioResourceList.[0].sobrenome").value(usuarioResources.get(0).getSobrenome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuarioResourceList.[0].cpf").value(usuarioResources.get(0).getCpf()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuarioResourceList.[0].papel").value(usuarioResources.get(0).getPapel().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuarioResourceList.[0].dataNascimento").value(usuarioResources.get(0).getDataNascimento().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuarioResourceList.[0].email").value(usuarioResources.get(0).getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuarioResourceList.[0].celular").value(usuarioResources.get(0).getCelular()));
    }


    @Test
    void registrarUsuarioTest() throws Exception {
        UsuarioResource usuarioResource = UsuarioResourceFixture.buildValido();
        UsuarioRequest usuarioRequest = UsuarioRequestFixture.buildValido();

        String usuarioRequestJson =  objectMapper.writeValueAsString(usuarioRequest);

        when(usuarioService.save(any(UsuarioRequest.class))).thenReturn(usuarioResource);

        mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
                        .contentType("application/json")
                        .content(usuarioRequestJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(usuarioResource.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(usuarioResource.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sobrenome").value(usuarioResource.getSobrenome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value(usuarioResource.getCpf()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.papel").value(usuarioResource.getPapel().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataNascimento").value(usuarioResource.getDataNascimento().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(usuarioResource.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.celular").value(usuarioResource.getCelular()));
    }

    @Test
    void atualizarUsuarioTest() throws Exception {
        UsuarioResource usuarioResource = UsuarioResourceFixture.buildValido();
        UsuarioRequest usuarioRequest = UsuarioRequestFixture.buildValido();

        String usuarioRequestJson =  objectMapper.writeValueAsString(usuarioRequest);

        when(usuarioService.update(any(UUID.class), any(UsuarioRequest.class))).thenReturn(usuarioResource);

        mockMvc.perform(MockMvcRequestBuilders.put("/usuarios/{id}", UUID.randomUUID())
                        .contentType("application/json")
                        .content(usuarioRequestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(usuarioResource.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(usuarioResource.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sobrenome").value(usuarioResource.getSobrenome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value(usuarioResource.getCpf()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.papel").value(usuarioResource.getPapel().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataNascimento").value(usuarioResource.getDataNascimento().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(usuarioResource.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.celular").value(usuarioResource.getCelular()));
    }

    @Test
    void deletarUsuarioTest() throws Exception {
        UsuarioResource usuarioResource = UsuarioResourceFixture.buildValido();

        when(usuarioService.delete(any(UUID.class))).thenReturn(usuarioResource);

        mockMvc.perform(MockMvcRequestBuilders.delete("/usuarios/{id}", UUID.randomUUID()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(usuarioResource.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(usuarioResource.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sobrenome").value(usuarioResource.getSobrenome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value(usuarioResource.getCpf()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.papel").value(usuarioResource.getPapel().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataNascimento").value(usuarioResource.getDataNascimento().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(usuarioResource.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.celular").value(usuarioResource.getCelular()));
    }

}
