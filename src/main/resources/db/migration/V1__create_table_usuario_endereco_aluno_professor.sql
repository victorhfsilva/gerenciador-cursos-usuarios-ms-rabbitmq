CREATE TABLE enderecos (
    id BIGSERIAL PRIMARY KEY,
    cep VARCHAR(8) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    complemento VARCHAR(100),
    logradouro VARCHAR(100) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(2) NOT NULL
);

CREATE TABLE usuarios (
    id UUID PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    senha VARCHAR(500) NOT NULL,
    papel VARCHAR(50) NOT NULL,
    data_nascimento DATE NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    celular VARCHAR(16) NOT NULL UNIQUE,
    endereco_id BIGINT NOT NULL,
    FOREIGN KEY (endereco_id) REFERENCES enderecos(id)
);

CREATE TABLE alunos (
    id UUID PRIMARY KEY,
    id_Estudantil VARCHAR(16) NOT NULL UNIQUE,
    FOREIGN KEY (id) REFERENCES usuarios(id)
);

CREATE TABLE professores (
    id UUID PRIMARY KEY,
    numero_ctps VARCHAR(10) NOT NULL,
    serie_ctps VARCHAR(10) NOT NULL,
    numero_pis VARCHAR(11) NOT NULL,
    remuneracao DOUBLE PRECISION NOT NULL,
    FOREIGN KEY (id) REFERENCES usuarios(id)
);