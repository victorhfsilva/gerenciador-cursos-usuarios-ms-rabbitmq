package com.example.usuariosms.service;

import com.example.usuariosms.model.dtos.AlunoDto;
import com.example.usuariosms.model.dtos.ProfessorDto;
import java.util.UUID;

public interface MensagensService {
    void registrarAluno(AlunoDto alunoDto);
    void registrarProfessor(ProfessorDto professorDto);
    void deletarAluno(UUID usuarioId);
    void deletarProfessor(UUID usuarioId);
}
