package com.example.usuariosms.controller;

import com.example.usuariosms.fixture.ProfessorRequestFixture;
import com.example.usuariosms.fixture.ProfessorResourceFixture;
import com.example.usuariosms.model.requests.ProfessorRequest;
import com.example.usuariosms.model.resources.ProfessorResource;
import com.example.usuariosms.service.impl.ProfessorServiceImpl;
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
class ProfessorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfessorServiceImpl professorServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void buscarProfessorPorIdTest() throws Exception {
        ProfessorResource professorResource = ProfessorResourceFixture.buildValido();

        when(professorServiceImpl.findById(any(UUID.class))).thenReturn(professorResource);

        mockMvc.perform(MockMvcRequestBuilders.get("/professores/{id}", UUID.randomUUID()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(professorResource.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(professorResource.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sobrenome").value(professorResource.getSobrenome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value(professorResource.getCpf()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.papel").value(professorResource.getPapel().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataNascimento").value(professorResource.getDataNascimento().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(professorResource.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.celular").value(professorResource.getCelular()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroCTPS").value(professorResource.getNumeroCTPS()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.serieCTPS").value(professorResource.getSerieCTPS()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroPIS").value(professorResource.getNumeroPIS()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.remuneracao").value(professorResource.getRemuneracao()));

    }

    @Test
    void buscarTodosProfessorsTest() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        List<ProfessorResource> professorResources = List.of(ProfessorResourceFixture.buildValido());
        Page<ProfessorResource> paginaProfessorResources = new PageImpl<>(professorResources, pageable, professorResources.size());

        when(professorServiceImpl.findAll(any(Pageable.class))).thenReturn(paginaProfessorResources);

        mockMvc.perform(MockMvcRequestBuilders.get("/professores"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.professorResourceList.[0].id").value(professorResources.get(0).getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.professorResourceList.[0].nome").value(professorResources.get(0).getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.professorResourceList.[0].sobrenome").value(professorResources.get(0).getSobrenome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.professorResourceList.[0].cpf").value(professorResources.get(0).getCpf()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.professorResourceList.[0].papel").value(professorResources.get(0).getPapel().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.professorResourceList.[0].dataNascimento").value(professorResources.get(0).getDataNascimento().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.professorResourceList.[0].email").value(professorResources.get(0).getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.professorResourceList.[0].celular").value(professorResources.get(0).getCelular()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.professorResourceList.[0].numeroCTPS").value(professorResources.get(0).getNumeroCTPS()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.professorResourceList.[0].serieCTPS").value(professorResources.get(0).getSerieCTPS()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.professorResourceList.[0].numeroPIS").value(professorResources.get(0).getNumeroPIS()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.professorResourceList.[0].remuneracao").value(professorResources.get(0).getRemuneracao()));
    }


    @Test
    void registrarProfessorTest() throws Exception {
        ProfessorResource professorResource = ProfessorResourceFixture.buildValido();
        ProfessorRequest professorRequest = ProfessorRequestFixture.buildValido();

        String professorRequestJson =  objectMapper.writeValueAsString(professorRequest);

        when(professorServiceImpl.save(any(ProfessorRequest.class))).thenReturn(professorResource);

        mockMvc.perform(MockMvcRequestBuilders.post("/professores")
                        .contentType("application/json")
                        .content(professorRequestJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(professorResource.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(professorResource.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sobrenome").value(professorResource.getSobrenome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value(professorResource.getCpf()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.papel").value(professorResource.getPapel().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataNascimento").value(professorResource.getDataNascimento().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(professorResource.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.celular").value(professorResource.getCelular()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroCTPS").value(professorResource.getNumeroCTPS()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.serieCTPS").value(professorResource.getSerieCTPS()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroPIS").value(professorResource.getNumeroPIS()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.remuneracao").value(professorResource.getRemuneracao()));
    }

    @Test
    void atualizarProfessorTest() throws Exception {
        ProfessorResource professorResource = ProfessorResourceFixture.buildValido();
        ProfessorRequest professorRequest = ProfessorRequestFixture.buildValido();

        String professorRequestJson =  objectMapper.writeValueAsString(professorRequest);

        when(professorServiceImpl.update(any(UUID.class), any(ProfessorRequest.class))).thenReturn(professorResource);

        mockMvc.perform(MockMvcRequestBuilders.put("/professores/{id}", UUID.randomUUID())
                        .contentType("application/json")
                        .content(professorRequestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(professorResource.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(professorResource.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sobrenome").value(professorResource.getSobrenome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value(professorResource.getCpf()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.papel").value(professorResource.getPapel().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataNascimento").value(professorResource.getDataNascimento().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(professorResource.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.celular").value(professorResource.getCelular()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroCTPS").value(professorResource.getNumeroCTPS()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.serieCTPS").value(professorResource.getSerieCTPS()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroPIS").value(professorResource.getNumeroPIS()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.remuneracao").value(professorResource.getRemuneracao()));
    }

    @Test
    void deletarProfessorTest() throws Exception {
        ProfessorResource professorResource = ProfessorResourceFixture.buildValido();

        when(professorServiceImpl.delete(any(UUID.class))).thenReturn(professorResource);

        mockMvc.perform(MockMvcRequestBuilders.delete("/professores/{id}", UUID.randomUUID()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(professorResource.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(professorResource.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sobrenome").value(professorResource.getSobrenome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value(professorResource.getCpf()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.papel").value(professorResource.getPapel().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataNascimento").value(professorResource.getDataNascimento().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(professorResource.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.celular").value(professorResource.getCelular()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroCTPS").value(professorResource.getNumeroCTPS()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.serieCTPS").value(professorResource.getSerieCTPS()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroPIS").value(professorResource.getNumeroPIS()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.remuneracao").value(professorResource.getRemuneracao()));
    }
}
