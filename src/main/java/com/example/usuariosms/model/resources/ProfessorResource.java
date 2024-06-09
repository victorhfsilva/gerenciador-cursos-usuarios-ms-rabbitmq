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
public class ProfessorResource extends RepresentationModel<ProfessorResource> {
    private UUID id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private Papel papel;
    private LocalDate dataNascimento;
    private String email;
    private String celular;
    private String numeroCTPS;
    private String serieCTPS;
    private String numeroPIS;
    private Double remuneracao;
}
