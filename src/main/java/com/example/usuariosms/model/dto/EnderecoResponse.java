package com.example.usuariosms.model.dto;

import com.example.usuariosms.model.enums.Estado;
public record EnderecoResponse (
        String cep,
        String numero,
        String complemento,
        String logradouro,
        String bairro,
        String cidade,
        Estado estado
){

}