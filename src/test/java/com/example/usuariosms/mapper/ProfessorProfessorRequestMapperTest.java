package com.example.usuariosms.mapper;

import com.example.usuariosms.fixture.EnderecoFixture;
import com.example.usuariosms.fixture.ProfessorRequestFixture;
import com.example.usuariosms.model.Endereco;
import com.example.usuariosms.model.Professor;
import com.example.usuariosms.model.dto.ProfessorRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProfessorProfessorRequestMapperTest {

    @Autowired
    private ProfessorProfessorRequestMapper professorProfessorRequestMapper;

    @MockBean
    private EnderecoEnderecoRequestMapper enderecoEnderecoRequestMapper;

    @Test
    void testProfessorProfessorRequestMapper() {
        ProfessorRequest professorRequest = ProfessorRequestFixture.buildValido();
        Endereco endereco = EnderecoFixture.buildValido();
        when(enderecoEnderecoRequestMapper.enderecoRequestToEndereco(professorRequest.endereco())).thenReturn(endereco);

        Professor professor = professorProfessorRequestMapper.professorRequestToProfessor(professorRequest);

        assertEquals(professor.getNome(), professorRequest.nome());
        assertEquals(professor.getSobrenome(), professorRequest.sobrenome());
        assertEquals(professor.getCpf(), professorRequest.cpf());
        assertEquals(professor.getPapel(), professorRequest.papel());
        assertEquals(professor.getDataNascimento(), professorRequest.dataNascimento());
        assertEquals(professor.getEmail(), professorRequest.email());
        assertEquals(professor.getCelular(), professorRequest.celular());
        assertEquals(endereco, professor.getEndereco());
        assertEquals(professor.getNumeroCTPS(), professorRequest.numeroCTPS());
        assertEquals(professor.getSerieCTPS(), professorRequest.serieCTPS());
        assertEquals(professor.getNumeroPIS(), professorRequest.numeroPIS());
        assertEquals(professor.getRemuneracao(), professorRequest.remuneracao());
    }

}