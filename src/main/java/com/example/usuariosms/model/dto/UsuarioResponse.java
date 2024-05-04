package com.example.usuariosms.model.dto;

import com.example.usuariosms.model.enums.Papel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Builder
public record UsuarioResponse (

){

}
