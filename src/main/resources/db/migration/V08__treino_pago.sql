CREATE TABLE treino_pago (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    id_usuario BIGINT(20) NOT NULL,
    id_treino BIGINT(20) NOT NULL,
    cancelado BOOLEAN NOT NULL,
    dh_registro DATETIME NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_treino) REFERENCES treino(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;