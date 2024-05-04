package com.example.usuariosms.model.resources;

import com.example.usuariosms.model.enums.Estado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Builder
@Getter
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
