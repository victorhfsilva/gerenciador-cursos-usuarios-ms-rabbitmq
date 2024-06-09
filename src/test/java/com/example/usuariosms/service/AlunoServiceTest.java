package com.example.usuariosms.service;

import com.example.usuariosms.client.CursoClient;
import com.example.usuariosms.fixture.AlunoFixture;
import com.example.usuariosms.fixture.AlunoRequestFixture;
import com.example.usuariosms.fixture.AlunoResourceFixture;
import com.example.usuariosms.mapper.EnderecoEnderecoRequestMapper;
import com.example.usuariosms.mapper.AlunoAlunoRequestMapper;
import com.example.usuariosms.mapper.AlunoAlunoResourceMapper;
import com.example.usuariosms.model.Aluno;
import com.example.usuariosms.model.dtos.AlunoClientDto;
import com.example.usuariosms.model.requests.AlunoRequest;
import com.example.usuariosms.model.requests.EnderecoRequest;
import com.example.usuariosms.model.resources.AlunoResource;
import com.example.usuariosms.repository.AlunoRepository;
import com.example.usuariosms.service.impl.AlunoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {
    @InjectMocks
    private AlunoService alunoService;
    @Mock
    private AlunoRepository alunoRepository;
    @Mock
    private AlunoAlunoResourceMapper alunoAlunoResourceMapper;
    @Mock
    private AlunoAlunoRequestMapper alunoAlunoRequestMapper;
    @Mock
    private EnderecoEnderecoRequestMapper enderecoEnderecoRequestMapper;
    @Mock
    private CursoClient cursoClient;
    @Test
    void saveTest(){
        AlunoRequest alunoRequest = AlunoRequestFixture.buildValido();
        Aluno aluno = AlunoFixture.buildValido();

        when(alunoAlunoRequestMapper.alunoRequestToAluno(alunoRequest)).thenReturn(aluno);
        when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);
        when(alunoAlunoResourceMapper.alunoToAlunoResource(aluno)).thenReturn(AlunoResourceFixture.buildValido());
        when(cursoClient.registrarAluno(any(AlunoClientDto.class))).thenReturn(AlunoClientDto.builder().usuarioId(aluno.getId()).build());

        AlunoResource alunoResource = alunoService.save(alunoRequest);

        assertEquals(alunoResource.getCpf(), alunoRequest.cpf());
        assertNotNull(alunoResource.getLink("self"));
        assertNotNull(alunoResource.getLink("endereco"));
    }

    @Test
    void findByIdTest() {
        Aluno aluno = AlunoFixture.buildValido();
        AlunoResource alunoResource = AlunoResourceFixture.buildValido();

        when(alunoRepository.findById(any(UUID.class))).thenReturn(Optional.of(aluno));
        when(alunoAlunoResourceMapper.alunoToAlunoResource(aluno)).thenReturn(alunoResource);
        AlunoResource alunoRetornado = alunoService.findById(UUID.randomUUID());

        assertEquals(aluno.getCpf(), alunoRetornado.getCpf());
        assertNotNull(alunoRetornado.getLink("self"));
        assertNotNull(alunoRetornado.getLink("endereco"));
    }

    @Test
    public void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Aluno> alunos = List.of(AlunoFixture.buildValido());
        Page<Aluno> paginaAlunos = new PageImpl<>(alunos, pageable, alunos.size());
        AlunoResource alunoResource = AlunoResourceFixture.buildValido();

        when(alunoRepository.findAll(pageable)).thenReturn(paginaAlunos);
        when(alunoAlunoResourceMapper.alunoToAlunoResource(any(Aluno.class)))
                .thenReturn(alunoResource);

        Page<AlunoResource> paginaAlunosRetornada = alunoService.findAll(pageable);

        assertEquals(paginaAlunos.getTotalElements(), paginaAlunosRetornada.getTotalElements());
        assertEquals(paginaAlunos.getTotalPages(), paginaAlunosRetornada.getTotalPages());
        assertEquals(paginaAlunos.getContent().size(), paginaAlunosRetornada.getContent().size());
        assertNotNull(paginaAlunosRetornada.getContent().get(0).getLink("self"));
        assertNotNull(paginaAlunosRetornada.getContent().get(0).getLink("endereco"));
    }

    @Test
    void updateTest() {
        Aluno aluno = AlunoFixture.buildValido();
        AlunoRequest alunoRequest = AlunoRequestFixture.buildValido();
        AlunoResource alunoResource = AlunoResourceFixture.buildValido();

        when(alunoRepository.findById(any(UUID.class))).thenReturn(Optional.of(aluno));
        when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);
        when(enderecoEnderecoRequestMapper.enderecoRequestToEndereco(any(EnderecoRequest.class))).thenReturn(aluno.getEndereco());
        when(alunoAlunoResourceMapper.alunoToAlunoResource(aluno)).thenReturn(alunoResource);

        AlunoResource alunoRetornado = alunoService.update(UUID.randomUUID(), alunoRequest);

        assertEquals(aluno.getCpf(), alunoRetornado.getCpf());
        assertNotNull(alunoRetornado.getLink("self"));
        assertNotNull(alunoRetornado.getLink("endereco"));
    }

    @Test
    void deleteTest() {
        Aluno aluno = AlunoFixture.buildValido();
        AlunoResource alunoResource = AlunoResourceFixture.buildValido();

        when(alunoRepository.findById(any(UUID.class))).thenReturn(Optional.of(aluno));
        when(alunoAlunoResourceMapper.alunoToAlunoResource(aluno)).thenReturn(alunoResource);
        when(cursoClient.deletarAluno(any(UUID.class))).thenReturn(AlunoClientDto.builder().usuarioId(aluno.getId()).build());

        AlunoResource alunoRetornado = alunoService.delete(UUID.randomUUID());

        assertEquals(aluno.getCpf(), alunoRetornado.getCpf());
        assertNotNull(alunoRetornado.getLink("self"));
        assertNotNull(alunoRetornado.getLink("endereco"));
    }
}
