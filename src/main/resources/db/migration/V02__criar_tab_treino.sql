CREATE TABLE treino (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(50) NOT NULL,
    dh_registro DATETIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;