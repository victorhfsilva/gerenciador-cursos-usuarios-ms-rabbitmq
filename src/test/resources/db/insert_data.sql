INSERT INTO enderecos (cep, numero, complemento, logradouro, bairro, cidade, estado) VALUES
('12345678', '123', 'Apartamento 101', 'Rua das Flores', 'Centro', 'São Paulo', 'SP'),
('87654321', '456', 'Casa 202', 'Avenida dos Sonhos', 'Jardim', 'São Paulo', 'SP'),
('45678912', '789', 'Sala 303', 'Praça das Árvores', 'Vila', 'São Paulo', 'SP');

INSERT INTO usuarios (id, nome, sobrenome, cpf, senha, papel, data_nascimento, email, celular, endereco_id) VALUES
('38bbaa9d-4b9b-4efb-9bd7-5f51de312e9d', 'Maria', 'Souza', '43146612674', 'Senha@123', 'ADMIN', '1990-01-01', 'maria@example.com', '999888777', 1),
('28bbaa9d-4b9b-4efb-9bd7-5f51de312e9c', 'Pedro', 'Silva', '22233344455', 'Senha@123', 'USUARIO', '1995-05-10', 'pedro@example.com', '888777666', 2),
('18bbaa9d-4b9b-4efb-9bd7-5f51de312e9b', 'Ana', 'Martins', '33344455566', 'Senha@123', 'ADMIN', '1980-12-20', 'ana@example.com', '777666555', 3);

INSERT INTO alunos (id, id_estudantil)
VALUES ('28bbaa9d-4b9b-4efb-9bd7-5f51de312e9c', '20220001');

INSERT INTO professores (id, numero_ctps, serie_ctps, numero_pis, remuneracao)
VALUES ('18bbaa9d-4b9b-4efb-9bd7-5f51de312e9b', '1234567890', 'ABCDE', '12345678901', 5000.00);