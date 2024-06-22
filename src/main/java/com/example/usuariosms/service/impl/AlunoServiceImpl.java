package com.example.usuariosms.service.impl;

import com.example.usuariosms.client.CursoClient;
import com.example.usuariosms.controller.EnderecoController;
import com.example.usuariosms.controller.AlunoController;
import com.example.usuariosms.mapper.AlunoMapper;
import com.example.usuariosms.mapper.EnderecoMapper;
import com.example.usuariosms.model.Aluno;
import com.example.usuariosms.model.dtos.AlunoClientDto;
import com.example.usuariosms.model.requests.AlunoRequest;
import com.example.usuariosms.model.resources.AlunoResource;
import com.example.usuariosms.repository.AlunoRepository;
import com.example.usuariosms.service.AlunoService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
public class AlunoServiceImpl implements AlunoService {
    private AlunoRepository alunoRepository;
    private AlunoMapper alunoMapper;
    private EnderecoMapper enderecoMapper;
    private CursoClient cursoClient;

    @Transactional
    public AlunoResource save(AlunoRequest alunoDto) {
        Aluno aluno = alunoMapper.map(alunoDto);

        Aluno alunoSalvo = alunoRepository.save(aluno);
        cursoClient.registrarAluno(AlunoClientDto.builder().usuarioId(alunoSalvo.getId()).build());

        AlunoResource alunoResource = alunoMapper.map(alunoSalvo);

        alunoResource.add(linkTo(methodOn(AlunoController.class).registrarAluno(alunoDto)).withSelfRel());
        alunoResource.add(linkTo(methodOn(EnderecoController.class).buscarEnderecosPorUsuarioId(alunoSalvo.getId())).withRel("endereco"));

        return alunoResource;
    }

    public AlunoResource findById(UUID id) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow();

        AlunoResource alunoResource = alunoMapper.map(aluno);

        alunoResource.add(linkTo(methodOn(AlunoController.class).buscarAlunoPorId(id)).withSelfRel());
        alunoResource.add(linkTo(methodOn(EnderecoController.class).buscarEnderecosPorUsuarioId(id)).withRel("endereco"));

        return alunoResource;
    }

    public Page<AlunoResource> findAll(Pageable pageable) {
        Page<Aluno> alunos = alunoRepository.findAll(pageable);

        return alunos
                .map(aluno ->
                        alunoMapper
                                .map(aluno)
                                .add(linkTo(methodOn(AlunoController.class).buscarAlunoPorId(aluno.getId())).withSelfRel())
                                .add(linkTo(methodOn(EnderecoController.class).buscarEnderecosPorUsuarioId(aluno.getId())).withRel("endereco")));
    }

    @Transactional
    public AlunoResource update(UUID id, AlunoRequest alunoDto) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow();

        atualizarAluno(aluno, alunoDto);

        Aluno alunoSalvo = alunoRepository.save(aluno);

        AlunoResource alunoResource = alunoMapper.map(alunoSalvo);

        alunoResource.add(linkTo(methodOn(AlunoController.class).atualizarAluno(id, alunoDto)).withSelfRel());
        alunoResource.add(linkTo(methodOn(EnderecoController.class).buscarEnderecosPorUsuarioId(id)).withRel("endereco"));

        return alunoResource;
    }

    @Transactional
    public AlunoResource delete(UUID id) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow();

        alunoRepository.delete(aluno);
        cursoClient.deletarAluno(aluno.getId());

        AlunoResource alunoResource = alunoMapper.map(aluno);

        alunoResource.add(linkTo(methodOn(AlunoController.class).deletarAluno(id)).withSelfRel());

        return alunoResource;
    }

    private void atualizarAluno(Aluno aluno, AlunoRequest novoAluno) {
        aluno.setNome(novoAluno.nome());
        aluno.setSobrenome(novoAluno.sobrenome());
        aluno.setCpf(novoAluno.cpf());
        aluno.setSenha(novoAluno.senha());
        aluno.setPapel(novoAluno.papel());
        aluno.setDataNascimento(novoAluno.dataNascimento());
        aluno.setEmail(novoAluno.email());
        aluno.setCelular(novoAluno.celular());
        aluno.setEndereco(enderecoMapper
                .map(novoAluno.endereco()));
        aluno.setIdEstudantil(novoAluno.idEstudantil());
    }
}
