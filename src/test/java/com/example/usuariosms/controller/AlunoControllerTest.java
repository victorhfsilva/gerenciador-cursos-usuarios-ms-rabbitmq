package com.example.usuariosms.controller;

import com.example.usuariosms.fixture.AlunoRequestFixture;
import com.example.usuariosms.fixture.AlunoResourceFixture;
import com.example.usuariosms.model.dto.AlunoRequest;
import com.example.usuariosms.model.resources.AlunoResource;
import com.example.usuariosms.service.impl.AlunoService;
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
public class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService alunoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void buscarAlunoPorIdTest() throws Exception {
        AlunoResource alunoResource = AlunoResourceFixture.buildValido();

        when(alunoService.findById(any(UUID.class))).thenReturn(alunoResource);

        mockMvc.perform(MockMvcRequestBuilders.get("/alunos/{id}", UUID.randomUUID()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(alunoResource.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(alunoResource.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sobrenome").value(alunoResource.getSobrenome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value(alunoResource.getCpf()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.papel").value(alunoResource.getPapel().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataNascimento").value(alunoResource.getDataNascimento().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(alunoResource.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.celular").value(alunoResource.getCelular()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idEstudantil").value(alunoResource.getIdEstudantil()));
    }

    @Test
    void buscarTodosAlunosTest() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        List<AlunoResource> alunoResources = List.of(AlunoResourceFixture.buildValido());
        Page<AlunoResource> paginaAlunoResources = new PageImpl<>(alunoResources, pageable, alunoResources.size());

        when(alunoService.findAll(any(Pageable.class))).thenReturn(paginaAlunoResources);

        mockMvc.perform(MockMvcRequestBuilders.get("/alunos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.alunoResourceList.[0].id").value(alunoResources.get(0).getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.alunoResourceList.[0].nome").value(alunoResources.get(0).getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.alunoResourceList.[0].sobrenome").value(alunoResources.get(0).getSobrenome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.alunoResourceList.[0].cpf").value(alunoResources.get(0).getCpf()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.alunoResourceList.[0].papel").value(alunoResources.get(0).getPapel().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.alunoResourceList.[0].dataNascimento").value(alunoResources.get(0).getDataNascimento().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.alunoResourceList.[0].email").value(alunoResources.get(0).getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.alunoResourceList.[0].celular").value(alunoResources.get(0).getCelular()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.alunoResourceList.[0].idEstudantil").value(alunoResources.get(0).getIdEstudantil()));
    }


    @Test
    void registrarAlunoTest() throws Exception {
        AlunoResource alunoResource = AlunoResourceFixture.buildValido();
        AlunoRequest alunoRequest = AlunoRequestFixture.buildValido();

        String alunoRequestJson =  objectMapper.writeValueAsString(alunoRequest);

        when(alunoService.save(any(AlunoRequest.class))).thenReturn(alunoResource);

        mockMvc.perform(MockMvcRequestBuilders.post("/alunos")
                        .contentType("application/json")
                        .content(alunoRequestJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(alunoResource.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(alunoResource.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sobrenome").value(alunoResource.getSobrenome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value(alunoResource.getCpf()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.papel").value(alunoResource.getPapel().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataNascimento").value(alunoResource.getDataNascimento().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(alunoResource.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.celular").value(alunoResource.getCelular()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idEstudantil").value(alunoResource.getIdEstudantil()));
    }

    @Test
    void atualizarAlunoTest() throws Exception {
        AlunoResource alunoResource = AlunoResourceFixture.buildValido();
        AlunoRequest alunoRequest = AlunoRequestFixture.buildValido();

        String alunoRequestJson =  objectMapper.writeValueAsString(alunoRequest);

        when(alunoService.update(any(UUID.class), any(AlunoRequest.class))).thenReturn(alunoResource);

        mockMvc.perform(MockMvcRequestBuilders.put("/alunos/{id}", UUID.randomUUID())
                        .contentType("application/json")
                        .content(alunoRequestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(alunoResource.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(alunoResource.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sobrenome").value(alunoResource.getSobrenome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value(alunoResource.getCpf()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.papel").value(alunoResource.getPapel().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataNascimento").value(alunoResource.getDataNascimento().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(alunoResource.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.celular").value(alunoResource.getCelular()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idEstudantil").value(alunoResource.getIdEstudantil()));
    }

    @Test
    void deletarAlunoTest() throws Exception {
        AlunoResource alunoResource = AlunoResourceFixture.buildValido();

        when(alunoService.delete(any(UUID.class))).thenReturn(alunoResource);

        mockMvc.perform(MockMvcRequestBuilders.delete("/alunos/{id}", UUID.randomUUID()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(alunoResource.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(alunoResource.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sobrenome").value(alunoResource.getSobrenome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value(alunoResource.getCpf()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.papel").value(alunoResource.getPapel().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataNascimento").value(alunoResource.getDataNascimento().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(alunoResource.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.celular").value(alunoResource.getCelular()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idEstudantil").value(alunoResource.getIdEstudantil()));
    }
    
}
