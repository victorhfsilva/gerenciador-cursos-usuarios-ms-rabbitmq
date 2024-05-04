package com.example.usuariosms.model;

import com.example.usuariosms.model.enums.Papel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@ToString
public class Aluno extends Usuario {

    @Column(nullable = false, unique = true)
    private UUID matricula;

    @Builder(builderMethodName = "alunoBuilder")
    public Aluno(UUID id, String nome, String sobrenome, String cpf, String senha, Papel papel, LocalDate dataNascimento, String email, String celular, Endereco endereco, UUID matricula) {
        super(id, nome, sobrenome, cpf, senha, papel, dataNascimento, email, celular, endereco);
        this.matricula = matricula;
    }
}
