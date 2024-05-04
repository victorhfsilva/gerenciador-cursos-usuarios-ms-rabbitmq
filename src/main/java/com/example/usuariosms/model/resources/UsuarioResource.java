package com.example.usuariosms.model.resources;

import com.example.usuariosms.model.enums.Papel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResource extends RepresentationModel<UsuarioResource> {
    private String nome;
    private String sobrenome;
    private String cpf;
    private Papel papel;
    private LocalDate dataNascimento;
    private String email;
    private String celular;
}
