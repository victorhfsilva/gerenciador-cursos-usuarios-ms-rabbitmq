package com.example.usuariosms.mapper;

import com.example.usuariosms.model.Endereco;
import com.example.usuariosms.model.requests.EnderecoRequest;
import com.example.usuariosms.model.resources.EnderecoResource;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EnderecoMapper {
    EnderecoResource map(Endereco endereco);
    Endereco map(EnderecoRequest registrarenderecoRequest);
}
