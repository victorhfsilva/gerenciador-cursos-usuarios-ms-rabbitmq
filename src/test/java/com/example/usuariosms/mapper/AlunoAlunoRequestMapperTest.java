package com.example.usuariosms.mapper;

import com.example.usuariosms.fixture.EnderecoFixture;
import com.example.usuariosms.fixture.AlunoRequestFixture;
import com.example.usuariosms.model.Endereco;
import com.example.usuariosms.model.Aluno;
import com.example.usuariosms.model.dto.AlunoRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class AlunoAlunoRequestMapperTest {

    @Autowired
    private AlunoAlunoRequestMapper alunoAlunoRequestMapper;

    @MockBean
    private EnderecoEnderecoRequestMapper enderecoEnderecoRequestMapper;

    @Test
    void testAlunoAlunoRequestMapper() {
        AlunoRequest alunoRequest = AlunoRequestFixture.buildValido();
        Endereco endereco = EnderecoFixture.buildValido();
        when(enderecoEnderecoRequestMapper.enderecoRequestToEndereco(alunoRequest.endereco())).thenReturn(endereco);

        Aluno aluno = alunoAlunoRequestMapper.alunoRequestToAluno(alunoRequest);

        assertEquals(aluno.getNome(), alunoRequest.nome());
        assertEquals(aluno.getSobrenome(), alunoRequest.sobrenome());
        assertEquals(aluno.getCpf(), alunoRequest.cpf());
        assertEquals(aluno.getPapel(), alunoRequest.papel());
        assertEquals(aluno.getDataNascimento(), alunoRequest.dataNascimento());
        assertEquals(aluno.getEmail(), alunoRequest.email());
        assertEquals(aluno.getCelular(), alunoRequest.celular());
        assertEquals(endereco, aluno.getEndereco());
        assertEquals(aluno.getIdEstudantil(), alunoRequest.idEstudantil());
    }

}