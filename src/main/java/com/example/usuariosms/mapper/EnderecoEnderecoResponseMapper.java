package com.example.usuariosms.mapper;

import com.example.usuariosms.model.Endereco;
import com.example.usuariosms.model.dto.EnderecoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoEnderecoResponseMapper {
    EnderecoResponse enderecoToEnderecoResponse(Endereco endereco);
}
