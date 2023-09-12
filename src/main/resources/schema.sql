CREATE TABLE person
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    name     VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    age      INT          NOT NULL,
    password VARCHAR(255) NOT NULL
);
CREATE TABLE roles
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    role VARCHAR(255) NOT NULL
);
CREATE TABLE user_role
(
    id      INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    role_id INT NOT NULL
);
