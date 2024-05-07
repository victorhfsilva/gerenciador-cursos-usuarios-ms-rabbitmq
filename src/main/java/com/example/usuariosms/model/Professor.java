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
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Professor extends Usuario {

    @Column(name = "numero_ctps", nullable = false, length = 10)
    private String numeroCTPS;

    @Column(name = "serie_ctps", nullable = false, length = 10)
    private String serieCTPS;

    @Column(name = "numero_pis", nullable = false, length = 11)
    private String numeroPIS;

    @Column(nullable = false)
    private Double remuneracao;

    @Builder(builderMethodName = "professorBuilder")
    public Professor(UUID id, String nome, String sobrenome, String cpf, String senha, Papel papel, LocalDate dataNascimento, String email, String celular, Endereco endereco, String numeroCTPS, String serieCTPS, String numeroPIS, Double remuneracao) {
        super(id, nome, sobrenome, cpf, senha, papel, dataNascimento, email, celular, endereco);
        this.numeroCTPS = numeroCTPS;
        this.serieCTPS = serieCTPS;
        this.numeroPIS = numeroPIS;
        this.remuneracao = remuneracao;
    }
}
