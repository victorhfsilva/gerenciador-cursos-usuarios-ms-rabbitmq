package com.example.usuariosms.service.impl;

import com.example.usuariosms.config.RabbitMQConfig;
import com.example.usuariosms.model.dtos.AlunoDto;
import com.example.usuariosms.model.dtos.ProfessorDto;
import com.example.usuariosms.service.MensagensService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class MensagensServiceImpl implements MensagensService {

    private RabbitTemplate rabbitTemplate;

    @Override
    public void registrarAluno(AlunoDto alunoDto) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_USUARIOS, "registrar.aluno", alunoDto);
    }

    @Override
    public void registrarProfessor(ProfessorDto professorDto) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_USUARIOS, "registrar.professor", professorDto);
    }

    @Override
    public void deletarAluno(UUID usuarioId) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_USUARIOS, "deletar.aluno", usuarioId);
    }

    @Override
    public void deletarProfessor(UUID usuarioId) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_USUARIOS, "deletar.professor", usuarioId);
    }
}
