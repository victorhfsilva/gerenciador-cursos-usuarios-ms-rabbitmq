package com.example.usuariosms.controller;

import com.example.usuariosms.fixture.UsuarioRequestFixture;
import com.example.usuariosms.model.dto.UsuarioRequest;
import com.example.usuariosms.model.resources.UsuarioResource;
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
public class UsuarioControllerIntegrationTest {

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
    void buscarUsuarioPorIdTest() {
        ResponseEntity<UsuarioResource> resposta = restTemplate
                .getForEntity("http://localhost:" + port + "/usuarios/38bbaa9d-4b9b-4efb-9bd7-5f51de312e9d",
                        UsuarioResource.class);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals("43146612674", resposta.getBody().getCpf());
    }

    @Test
    @SqlGroup({
            @Sql(scripts = "/db/delete_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/restart_ids.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/insert_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void registrarUsuarioTest() {
        UsuarioRequest usuarioRequest = UsuarioRequestFixture.buildValido();
        HttpEntity<UsuarioRequest> requisicao = new HttpEntity<>(usuarioRequest);

        ResponseEntity<UsuarioResource> resposta = restTemplate
                .postForEntity("http://localhost:" + port + "/usuarios",
                        requisicao,
                        UsuarioResource.class);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals("78664841209", resposta.getBody().getCpf());
    }
    
    @Test
    @SqlGroup({
            @Sql(scripts = "/db/delete_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/restart_ids.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/insert_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void atualizarUsuarioTest() {
        UsuarioRequest usuarioRequest = UsuarioRequestFixture.buildValido();
        HttpEntity<UsuarioRequest> requisicao = new HttpEntity<>(usuarioRequest);

        ResponseEntity<UsuarioResource> resposta = restTemplate
                .exchange("http://localhost:" + port + "/usuarios/38bbaa9d-4b9b-4efb-9bd7-5f51de312e9d",
                        HttpMethod.PUT,
                        requisicao, 
                        UsuarioResource.class);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals("78664841209", resposta.getBody().getCpf());
    }

    @Test
    @SqlGroup({
            @Sql(scripts = "/db/delete_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/restart_ids.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/insert_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void deletarUsuarioTest() {
        ResponseEntity<UsuarioResource> resposta = restTemplate
                .exchange("http://localhost:" + port + "/usuarios/38bbaa9d-4b9b-4efb-9bd7-5f51de312e9d",
                        HttpMethod.DELETE,
                        null,
                        UsuarioResource.class);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals("43146612674", resposta.getBody().getCpf());
    }
}
