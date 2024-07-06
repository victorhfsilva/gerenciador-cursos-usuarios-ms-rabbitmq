package com.example.usuariosms.service.impl;

import com.example.usuariosms.service.MensagensService;
import com.example.usuariosms.controller.EnderecoController;
import com.example.usuariosms.controller.ProfessorController;
import com.example.usuariosms.mapper.EnderecoMapper;
import com.example.usuariosms.mapper.ProfessorMapper;
import com.example.usuariosms.model.Professor;
import com.example.usuariosms.model.dtos.ProfessorDto;
import com.example.usuariosms.model.requests.ProfessorRequest;
import com.example.usuariosms.model.resources.ProfessorResource;
import com.example.usuariosms.repository.ProfessorRepository;
import com.example.usuariosms.service.ProfessorService;
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
public class ProfessorServiceImpl implements ProfessorService {
    private ProfessorRepository professorRepository;
    private ProfessorMapper professorMapper;
    private EnderecoMapper enderecoMapper;
    private MensagensService mensagensService;

    @Transactional
    public ProfessorResource save(ProfessorRequest professorDto) {
        Professor professor = professorMapper.map(professorDto);

        Professor professorSalvo = professorRepository.save(professor);
        mensagensService.registrarProfessor(ProfessorDto.builder().usuarioId(professorSalvo.getId()).build());

        ProfessorResource professorResource = professorMapper.map(professorSalvo);

        professorResource.add(linkTo(methodOn(ProfessorController.class).registrarProfessor(professorDto)).withSelfRel());
        professorResource.add(linkTo(methodOn(EnderecoController.class).buscarEnderecosPorUsuarioId(professorSalvo.getId())).withRel("endereco"));

        return professorResource;
    }

    public ProfessorResource findById(UUID id) {
        Professor professor = professorRepository.findById(id).orElseThrow();

        ProfessorResource professorResource = professorMapper.map(professor);

        professorResource.add(linkTo(methodOn(ProfessorController.class).buscarProfessorPorId(id)).withSelfRel());
        professorResource.add(linkTo(methodOn(EnderecoController.class).buscarEnderecosPorUsuarioId(id)).withRel("endereco"));

        return professorResource;
    }

    public Page<ProfessorResource> findAll(Pageable pageable) {
        Page<Professor> professors = professorRepository.findAll(pageable);

        return professors
                .map(professor ->
                        professorMapper
                                .map(professor)
                                .add(linkTo(methodOn(ProfessorController.class).buscarProfessorPorId(professor.getId())).withSelfRel())
                                .add(linkTo(methodOn(EnderecoController.class).buscarEnderecosPorUsuarioId(professor.getId())).withRel("endereco")));
    }

    @Transactional
    public ProfessorResource update(UUID id, ProfessorRequest professorDto) {
        Professor professor = professorRepository.findById(id).orElseThrow();

        atualizarProfessor(professor, professorDto);

        Professor professorSalvo = professorRepository.save(professor);

        ProfessorResource professorResource = professorMapper.map(professorSalvo);

        professorResource.add(linkTo(methodOn(ProfessorController.class).atualizarProfessor(id, professorDto)).withSelfRel());
        professorResource.add(linkTo(methodOn(EnderecoController.class).buscarEnderecosPorUsuarioId(id)).withRel("endereco"));

        return professorResource;
    }

    @Transactional
    public ProfessorResource delete(UUID id) {
        Professor professor = professorRepository.findById(id).orElseThrow();

        professorRepository.delete(professor);
        mensagensService.deletarProfessor(professor.getId());

        ProfessorResource professorResource = professorMapper.map(professor);

        professorResource.add(linkTo(methodOn(ProfessorController.class).deletarProfessor(id)).withSelfRel());

        return professorResource;
    }

    private void atualizarProfessor(Professor professor, ProfessorRequest novoProfessor) {
        professor.setNome(novoProfessor.nome());
        professor.setSobrenome(novoProfessor.sobrenome());
        professor.setCpf(novoProfessor.cpf());
        professor.setSenha(novoProfessor.senha());
        professor.setPapel(novoProfessor.papel());
        professor.setDataNascimento(novoProfessor.dataNascimento());
        professor.setEmail(novoProfessor.email());
        professor.setCelular(novoProfessor.celular());
        professor.setEndereco(enderecoMapper
                .map(novoProfessor.endereco()));
        professor.setNumeroCTPS(novoProfessor.numeroCTPS());
        professor.setSerieCTPS(novoProfessor.serieCTPS());
        professor.setNumeroPIS(novoProfessor.numeroPIS());
        professor.setRemuneracao(novoProfessor.remuneracao());
    }
}
