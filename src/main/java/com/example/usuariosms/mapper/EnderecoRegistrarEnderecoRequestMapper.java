package com.example.usuariosms.mapper;

import com.example.usuariosms.model.Endereco;
import com.example.usuariosms.model.dto.RegistrarEnderecoRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoRegistrarEnderecoRequestMapper {
    Endereco registrarEnderecoRequestToEndereco(RegistrarEnderecoRequest registrarenderecoRequest);

}
