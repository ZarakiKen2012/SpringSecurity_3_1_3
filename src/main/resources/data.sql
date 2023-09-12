-- Заполняем таблицы данными
INSERT INTO person (name, lastname, age, password) VALUES ('Name1', 'LastName1', 1, 123325);
INSERT INTO person (name, lastname, age, password) VALUES ('Name2', 'LastName2', 2, 123325);
INSERT INTO person (name, lastname, age, password) VALUES ('Name3', 'LastName3', 3, 123325);

INSERT INTO roles (role) VALUES ('ADMIN');
INSERT INTO roles (role) VALUES ('USER');

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO user_role (user_id, role_id) VALUES (3, 2);

