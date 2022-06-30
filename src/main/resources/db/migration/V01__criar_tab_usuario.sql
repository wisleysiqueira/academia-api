CREATE TABLE usuario (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(200) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(50),
    dt_nascimento DATE,
    user_name VARCHAR(50) NOT NULL,
    senha VARCHAR(150) NOT NULL,
    dh_registro DATETIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;