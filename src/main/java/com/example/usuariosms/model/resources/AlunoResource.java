package com.example.usuariosms.model.resources;

import com.example.usuariosms.model.enums.Papel;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AlunoResource extends RepresentationModel<AlunoResource> {
    private UUID id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private Papel papel;
    private LocalDate dataNascimento;
    private String email;
    private String celular;
    private String idEstudantil;
}
