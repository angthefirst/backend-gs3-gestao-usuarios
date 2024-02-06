-- Inserir registros na tabela Perfil
INSERT INTO Perfil VALUES (1, 'ADMIN');
INSERT INTO Perfil VALUES (2, 'COMUM');

-- Inserir registros na tabela Usuario
INSERT INTO Usuario (ID_USUARIO, LOGIN, SENHA, NOME_COMPLETO, EMAIL, ID_PERFIL)
VALUES ('1', 'admin', 'admin', 'Admin One', 'admin1@exemplo.com', 1),
       ('2', 'usuario0', 'usuario0', 'Alucard', 'alucard@exemplo.com', 2),
       ('3', 'usuario1', 'usuario1', 'Usuario Um', 'Usuario1@exemplo.com', 2),
       ('4', 'usuario2', 'usuario2', 'Usuario Dois', 'Usuario2@exemplo.com', 2),
       ('5', 'usuario3', 'usuario3', 'Usuario Tres', 'Usuario3@exemplo.com', 2),
       ('6', 'joao123', 'joao123', 'João da Silva', 'joao123@exemplo.com', 2),
       ('7', 'maria456', 'maria456', 'Maria Oliveira', 'maria456@exemplo.com', 2),
       ('8', 'carlos789', 'carlos789', 'Carlos Souza', 'carlos789@exemplo.com', 2),
       ('9', 'anaabc', 'anaabc', 'Ana Santos', 'anaabc@exemplo.com', 2),
       ('10', 'pedroxyz', 'pedroxyz', 'Pedro Costa', 'pedroxyz@exemplo.com', 2),
       ('11', 'lucas789', 'lucas789', 'Lucas Pereira', 'lucas789@exemplo.com', 2),
       ('12', 'camila456', 'camila456', 'Camila Alves', 'camila456@exemplo.com', 2),
       ('13', 'fernanda123', 'fernanda123', 'Fernanda Lima', 'fernanda123@exemplo.com', 2),
       ('14', 'rafaelxyz', 'rafaelxyz', 'Rafael Rodrigues', 'rafaelxyz@exemplo.com', 2),
       ('15', 'anaabc', 'anaabc', 'Ana Paula', 'anaabc@exemplo.com', 2);

-- Inserir registros na tabela Endereco
INSERT INTO Endereco (ID_USUARIO, RUA, BAIRRO, CIDADE)
VALUES ('1', 'Rua Admin 1', 'Centro', 'Cidade A'),
       ('2', 'Rua Usuario 0', 'Centro', 'Cidade B'),
       ('3', 'Rua Usuario 1', 'Bairro X', 'Cidade A'),
       ('4', 'Rua Usuario 2', 'Bairro Y', 'Cidade B'),
       ('5', 'Rua Usuario 3', 'Bairro Z', 'Cidade C'),
       ('6', 'Rua João 123', 'Bairro A', 'Cidade D'),
       ('7', 'Rua Maria 456', 'Bairro B', 'Cidade E'),
       ('8', 'Rua Carlos 789', 'Bairro C', 'Cidade F'),
       ('9', 'Rua Ana ABC', 'Bairro D', 'Cidade G'),
       ('10', 'Rua Pedro XYZ', 'Bairro E', 'Cidade H'),
       ('11', 'Rua Lucas 789', 'Bairro F', 'Cidade I'),
       ('12', 'Rua Camila 456', 'Bairro G', 'Cidade J'),
       ('13', 'Rua Fernanda 123', 'Bairro H', 'Cidade K'),
       ('14', 'Rua Rafael XYZ', 'Bairro I', 'Cidade L'),
       ('15', 'Rua Ana ABC', 'Bairro J', 'Cidade M');
