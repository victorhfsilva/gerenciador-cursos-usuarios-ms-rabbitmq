package com.example.usuariosms.model.dto;

import com.example.usuariosms.model.enums.Papel;
import com.example.usuariosms.validators.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Builder
public record ProfessorRequest(
        @NotBlank
        @Size(max = 100)
        String nome,

        @NotBlank
        @Size(max = 100)
        String sobrenome,

        @CPF
        String cpf,

        @NotBlank
        @Size(max = 30)
        @ValidPassword
        String senha,

        @NotNull
        Papel papel,

        @NotNull
        LocalDate dataNascimento,

        @NotBlank
        @Size(max = 100)
        String email,

        @NotBlank
        @Size(max = 16)
        String celular,

        EnderecoRequest endereco,

        @NotBlank
        @Size(max = 10)
        String numeroCTPS,

        @NotBlank
        @Size(max = 10)
        String serieCTPS,

        @NotBlank
        @Size(max = 11)
        String numeroPIS,

        @NotNull
        Double remuneracao
){

}