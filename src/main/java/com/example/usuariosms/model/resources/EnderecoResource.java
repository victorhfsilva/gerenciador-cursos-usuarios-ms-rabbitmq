package com.example.usuariosms.model.resources;

import com.example.usuariosms.model.enums.Estado;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoResource extends RepresentationModel<UsuarioResource> {
    private String cep;
    private String numero;
    private String complemento;
    private String logradouro;
    private String bairro;
    private String cidade;
    private Estado estado;
}
