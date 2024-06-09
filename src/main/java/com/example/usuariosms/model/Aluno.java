package com.example.usuariosms.model;

import com.example.usuariosms.model.enums.Papel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Table(name = "alunos")
public class Aluno extends Usuario {

    @Column(nullable = false, unique = true, length = 16)
    private String idEstudantil;

    @Builder(builderMethodName = "alunoBuilder")
    public Aluno(UUID id, String nome, String sobrenome, String cpf, String senha, Papel papel, LocalDate dataNascimento, String email, String celular, Endereco endereco, String idEstudantil) {
        super(id, nome, sobrenome, cpf, senha, papel, dataNascimento, email, celular, endereco);
        this.idEstudantil = idEstudantil;
    }
}
