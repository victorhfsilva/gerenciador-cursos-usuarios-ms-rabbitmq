package com.example.usuariosms.repository;

import com.example.usuariosms.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, UUID>{
}
