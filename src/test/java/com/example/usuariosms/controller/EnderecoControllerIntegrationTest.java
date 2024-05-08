package com.example.usuariosms.controller;

import com.example.usuariosms.fixture.EnderecoResourceFixture;
import com.example.usuariosms.model.resources.EnderecoResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
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
public class EnderecoControllerIntegrationTest {

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
    void buscarEnderecoPorIdTest() {
        ResponseEntity<EnderecoResource> resposta = restTemplate
                .getForEntity("http://localhost:" + port + "/enderecos/38bbaa9d-4b9b-4efb-9bd7-5f51de312e9d",
                        EnderecoResource.class);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals("12345678", resposta.getBody().getCep());
    }

    @Test
    @SqlGroup({
            @Sql(scripts = "/db/delete_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/restart_ids.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/insert_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void atualizarEnderecoTest() {

        EnderecoResource enderecoResource = EnderecoResourceFixture.buildValido();
        HttpEntity<EnderecoResource> request = new HttpEntity<>(enderecoResource);

        ResponseEntity<EnderecoResource> resposta = restTemplate
                .exchange("http://localhost:" + port + "/enderecos/38bbaa9d-4b9b-4efb-9bd7-5f51de312e9d",
                        HttpMethod.PUT,
                        request,
                        EnderecoResource.class);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals("12345678", resposta.getBody().getCep());
    }
}
