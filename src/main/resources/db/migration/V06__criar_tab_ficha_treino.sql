CREATE TABLE ficha_treino (
    id_ficha BIGINT(20) NOT NULL,
    id_treino BIGINT(20) NOT NULL,
    PRIMARY KEY (id_ficha, id_treino),
    FOREIGN KEY (id_ficha) REFERENCES ficha(id),
    FOREIGN KEY (id_treino) REFERENCES treino(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;