package com.example.usuariosms.controller;

import com.example.usuariosms.fixture.AlunoRequestFixture;
import com.example.usuariosms.model.dto.AlunoRequest;
import com.example.usuariosms.model.resources.AlunoResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AlunoControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    @SqlGroup({
            @Sql(scripts = "/db/delete_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/restart_ids.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/insert_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void buscarAlunoPorIdTest() {
        ResponseEntity<AlunoResource> resposta = restTemplate
                .getForEntity("http://localhost:" + port + "/alunos/28bbaa9d-4b9b-4efb-9bd7-5f51de312e9c",
                        AlunoResource.class);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals("22233344455", resposta.getBody().getCpf());
    }

    @Test
    @SqlGroup({
            @Sql(scripts = "/db/delete_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/restart_ids.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/insert_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void registrarAlunoTest() {
        AlunoRequest alunoRequest = AlunoRequestFixture.buildValido();
        HttpEntity<AlunoRequest> requisicao = new HttpEntity<>(alunoRequest);

        ResponseEntity<AlunoResource> resposta = restTemplate
                .postForEntity("http://localhost:" + port + "/alunos",
                        requisicao,
                        AlunoResource.class);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals("78664841209", resposta.getBody().getCpf());
    }
    
    @Test
    @SqlGroup({
            @Sql(scripts = "/db/delete_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/restart_ids.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/insert_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void atualizarAlunoTest() {
        AlunoRequest alunoRequest = AlunoRequestFixture.buildValido();
        HttpEntity<AlunoRequest> requisicao = new HttpEntity<>(alunoRequest);

        ResponseEntity<AlunoResource> resposta = restTemplate
                .exchange("http://localhost:" + port + "/alunos/28bbaa9d-4b9b-4efb-9bd7-5f51de312e9c",
                        HttpMethod.PUT,
                        requisicao, 
                        AlunoResource.class);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals("78664841209", resposta.getBody().getCpf());
    }

    @Test
    @SqlGroup({
            @Sql(scripts = "/db/delete_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/restart_ids.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/insert_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void deletarAlunoTest() {
        ResponseEntity<AlunoResource> resposta = restTemplate
                .exchange("http://localhost:" + port + "/alunos/28bbaa9d-4b9b-4efb-9bd7-5f51de312e9c",
                        HttpMethod.DELETE,
                        null,
                        AlunoResource.class);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals("22233344455", resposta.getBody().getCpf());
    }
}
