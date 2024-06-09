package com.example.usuariosms.controller;

import com.example.usuariosms.fixture.EnderecoRequestFixture;
import com.example.usuariosms.fixture.EnderecoResourceFixture;
import com.example.usuariosms.model.requests.EnderecoRequest;
import com.example.usuariosms.model.resources.EnderecoResource;
import com.example.usuariosms.service.impl.EnderecoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EnderecoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnderecoService enderecoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void buscarEnderecosPorUsuarioIdTest() throws Exception {

        EnderecoResource enderecoResource = EnderecoResourceFixture.buildValido();

        when(enderecoService.findByUsuarioId(any(UUID.class))).thenReturn(enderecoResource);

        mockMvc.perform(MockMvcRequestBuilders.get("/enderecos/{usuarioId}", UUID.randomUUID()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cep").value(enderecoResource.getCep()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.logradouro").value(enderecoResource.getLogradouro()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numero").value(enderecoResource.getNumero()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.complemento").value(enderecoResource.getComplemento()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bairro").value(enderecoResource.getBairro()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cidade").value(enderecoResource.getCidade()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.estado").value(enderecoResource.getEstado().toString()));

    }

    @Test
    void atualizarEnderecoTest() throws Exception {

        EnderecoResource enderecoResource = EnderecoResourceFixture.buildValido();
        EnderecoRequest enderecoRequest = EnderecoRequestFixture.buildValido();

        when(enderecoService.update(any(UUID.class), any(EnderecoRequest.class))).thenReturn(enderecoResource);

        mockMvc.perform(MockMvcRequestBuilders.put("/enderecos/{usuarioId}", UUID.randomUUID())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(enderecoRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cep").value(enderecoResource.getCep()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.logradouro").value(enderecoResource.getLogradouro()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numero").value(enderecoResource.getNumero()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.complemento").value(enderecoResource.getComplemento()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bairro").value(enderecoResource.getBairro()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cidade").value(enderecoResource.getCidade()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.estado").value(enderecoResource.getEstado().toString()));

    }
}
