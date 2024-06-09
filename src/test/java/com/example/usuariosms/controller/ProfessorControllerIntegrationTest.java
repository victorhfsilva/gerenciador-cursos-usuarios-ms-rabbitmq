package com.example.usuariosms.controller;

import com.example.usuariosms.fixture.ProfessorRequestFixture;
import com.example.usuariosms.model.requests.ProfessorRequest;
import com.example.usuariosms.model.resources.ProfessorResource;
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
public class ProfessorControllerIntegrationTest {

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
    void buscarProfessorPorIdTest() {
        ResponseEntity<ProfessorResource> resposta = restTemplate
                .getForEntity("http://localhost:" + port + "/professores/18bbaa9d-4b9b-4efb-9bd7-5f51de312e9b",
                        ProfessorResource.class);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals("33344455566", resposta.getBody().getCpf());
    }

    @Test
    @SqlGroup({
            @Sql(scripts = "/db/delete_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/restart_ids.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/insert_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void registrarProfessorTest() {
        ProfessorRequest professoreRequest = ProfessorRequestFixture.buildValido();
        HttpEntity<ProfessorRequest> requisicao = new HttpEntity<>(professoreRequest);

        ResponseEntity<ProfessorResource> resposta = restTemplate
                .postForEntity("http://localhost:" + port + "/professores",
                        requisicao,
                        ProfessorResource.class);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals("78664841209", resposta.getBody().getCpf());
    }
    
    @Test
    @SqlGroup({
            @Sql(scripts = "/db/delete_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/restart_ids.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/insert_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void atualizarProfessorTest() {
        ProfessorRequest professoreRequest = ProfessorRequestFixture.buildValido();
        HttpEntity<ProfessorRequest> requisicao = new HttpEntity<>(professoreRequest);

        ResponseEntity<ProfessorResource> resposta = restTemplate
                .exchange("http://localhost:" + port + "/professores/18bbaa9d-4b9b-4efb-9bd7-5f51de312e9b",
                        HttpMethod.PUT,
                        requisicao, 
                        ProfessorResource.class);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals("78664841209", resposta.getBody().getCpf());
    }

    @Test
    @SqlGroup({
            @Sql(scripts = "/db/delete_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/restart_ids.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/insert_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void deletarProfessorTest() {
        ResponseEntity<ProfessorResource> resposta = restTemplate
                .exchange("http://localhost:" + port + "/professores/18bbaa9d-4b9b-4efb-9bd7-5f51de312e9b",
                        HttpMethod.DELETE,
                        null,
                        ProfessorResource.class);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals("33344455566", resposta.getBody().getCpf());
    }
}
