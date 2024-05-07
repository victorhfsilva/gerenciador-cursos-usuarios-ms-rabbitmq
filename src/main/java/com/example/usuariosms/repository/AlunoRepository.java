package com.example.usuariosms.repository;

import com.example.usuariosms.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, UUID>{
}

