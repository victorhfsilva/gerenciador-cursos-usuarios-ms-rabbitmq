package com.example.usuariosms.client;

import com.example.usuariosms.model.dtos.AlunoClientDto;
import com.example.usuariosms.model.dtos.ProfessorClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Repository
@FeignClient(name = "cursos-ms")
public interface CursoClient {
    @PostMapping("/alunos")
    AlunoClientDto registrarAluno(@RequestBody AlunoClientDto alunoClientDto);

    @PostMapping("/professores")
    ProfessorClientDto registrarProfessor(@RequestBody ProfessorClientDto professorClientDto);

    @DeleteMapping("/alunos/{usuarioId}")
    AlunoClientDto deletarAluno(@PathVariable UUID usuarioId);

    @DeleteMapping("/professores/{usuarioId}")
    ProfessorClientDto deletarProfessor(@PathVariable UUID usuarioId);
}
