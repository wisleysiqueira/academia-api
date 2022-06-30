INSERT INTO permissao (descricao, dh_registro)
VALUES ("ROLE_USUARIO_INSERT", NOW());
INSERT INTO permissao (descricao, dh_registro)
VALUES ("ROLE_USUARIO_REMOVE", NOW());
INSERT INTO permissao (descricao, dh_registro)
VALUES ("ROLE_USUARIO_SEARCH", NOW());
INSERT INTO permissao (descricao, dh_registro)
VALUES ("ROLE_USUARIO_VIEW", NOW());
INSERT INTO permissao (descricao, dh_registro)
VALUES ("ROLE_USUARIO_UPDATE", NOW());

INSERT INTO permissao (descricao, dh_registro)
VALUES ("ROLE_FICHA_INSERT", NOW());
INSERT INTO permissao (descricao, dh_registro)
VALUES ("ROLE_FICHA_REMOVE", NOW());
INSERT INTO permissao (descricao, dh_registro)
VALUES ("ROLE_FICHA_VIEW", NOW());
INSERT INTO permissao (descricao, dh_registro)
VALUES ("ROLE_FICHA_SEARCH", NOW());
INSERT INTO permissao (descricao, dh_registro)
VALUES ("ROLE_FICHA_UPDATE", NOW());

INSERT INTO permissao (descricao, dh_registro)
VALUES ("ROLE_PERMISSAO_INSERT", NOW());
INSERT INTO permissao (descricao, dh_registro)
VALUES ("ROLE_PERMISSAO_REMOVE", NOW());
INSERT INTO permissao (descricao, dh_registro)
VALUES ("ROLE_PERMISSAO_VIEW", NOW());
INSERT INTO permissao (descricao, dh_registro)
VALUES ("ROLE_PERMISSAO_SEARCH", NOW());
INSERT INTO permissao (descricao, dh_registro)
VALUES ("ROLE_PERMISSAO_UPDATE", NOW());

INSERT INTO permissao (descricao, dh_registro)
VALUES ("ROLE_TREINOPAGO_INSERT", NOW());
INSERT INTO permissao (descricao, dh_registro)
VALUES ("ROLE_TREINOPAGO_REMOVE", NOW());
INSERT INTO permissao (descricao, dh_registro)
VALUES ("ROLE_TREINOPAGO_VIEW", NOW());
INSERT INTO permissao (descricao, dh_registro)
VALUES ("ROLE_TREINOPAGO_SEARCH", NOW());
INSERT INTO permissao (descricao, dh_registro)
VALUES ("ROLE_TREINOPAGO_UPDATE", NOW());

INSERT INTO permissao (descricao, dh_registro)
VALUES ("ROLE_TREINO_INSERT", NOW());
INSERT INTO permissao (descricao, dh_registro)
VALUES ("ROLE_TREINO_REMOVE", NOW());
INSERT INTO permissao (descricao, dh_registro)
VALUES ("ROLE_TREINO_VIEW", NOW());
INSERT INTO permissao (descricao, dh_registro)
VALUES ("ROLE_TREINO_SEARCH", NOW());
INSERT INTO permissao (descricao, dh_registro)
VALUES ("ROLE_TREINO_UPDATE", NOW());

INSERT INTO usuario (nome, telefone, email, dt_nascimento, user_name, senha, dh_registro)
VALUES ("Admin", "099999999999", "admin@academia.com", "1990-01-01", "admin", "$2a$10$zkfhwmG4tLaUwScBLVWpsegqx269yPuuNCuz7G5MMbsBUTZC1h5oa", NOW());

INSERT INTO usuario (nome, telefone, email, dt_nascimento, user_name, senha, dh_registro)
VALUES ("Felipe Victor Nelson Figueiredo", "06529355698", "felipe_figueiredo@betti.com.br", "1946-04-22", "felipe.academia", "$2a$10$HnoM6HzpVuFGkfldIZ7nfexRe70FSyIrIOut9tTswIGJSuQGUv7Hm", NOW());

INSERT INTO usuario (nome, telefone, email, dt_nascimento, user_name, senha, dh_registro)
VALUES ("Clarice Lara Larissa Porto", "08228258190", "clarice-porto91@gilconsultoria.com.br", "1966-02-19", "clarice.academia", "$2a$10$HnoM6HzpVuFGkfldIZ7nfexRe70FSyIrIOut9tTswIGJSuQGUv7Hm", NOW());

INSERT INTO usuario (nome, telefone, email, dt_nascimento, user_name, senha, dh_registro)
VALUES ("Erick Arthur Manuel Caldeira", "09526302315", "erick.arthur.caldeira@directnet.com", "1989-05-15", "erick.academia", "$2a$10$HnoM6HzpVuFGkfldIZ7nfexRe70FSyIrIOut9tTswIGJSuQGUv7Hm", NOW());

INSERT INTO usuario (nome, telefone, email, dt_nascimento, user_name, senha, dh_registro)
VALUES ("Francisco Eduardo Vieira", "06935769082", "francisco_eduardo_vieira@redealumni.com", "2002-02-18", "francisco.academia", "$2a$10$HnoM6HzpVuFGkfldIZ7nfexRe70FSyIrIOut9tTswIGJSuQGUv7Hm", NOW());

INSERT INTO usuario (nome, telefone, email, dt_nascimento, user_name, senha, dh_registro)
VALUES ("Heloise Julia da Conceição", "02729052616", "heloisejuliadaconceicao@gmeil.com", "1974-05-04", "heloise.academia", "$2a$10$HnoM6HzpVuFGkfldIZ7nfexRe70FSyIrIOut9tTswIGJSuQGUv7Hm", NOW());

INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (1, 1);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (1, 2);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (1, 3);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (1, 4);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (1, 5);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (1, 6);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (1, 7);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (1, 8);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (1, 9);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (1, 10);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (1, 11);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (1, 12);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (1, 13);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (1, 14);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (1, 15);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (1, 16);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (1, 17);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (1, 18);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (1, 19);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (1, 20);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (1, 21);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (1, 22);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (1, 23);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (1, 24);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (1, 25);

INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (2, 2);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (2, 8);

INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (3, 2);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (3, 8);

INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (4, 2);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (4, 8);

INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (5, 2);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (5, 8);

INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (6, 2);
INSERT INTO usuario_permissao (id_usuario, id_permissao)
VALUES (6, 8);