--liquibase formatted sql

--changeset Emre_Celik:1
CREATE TABLE IF NOT EXISTS roles
(
    id
    BIGINT
    PRIMARY
    KEY
    AUTO_INCREMENT,
    name
    VARCHAR
(
    50
) NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS users
(
    id
    BIGINT
    PRIMARY
    KEY
    AUTO_INCREMENT,
    username
    VARCHAR
(
    255
) NOT NULL UNIQUE,
    password VARCHAR
(
    255
) NOT NULL
    );

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id
    BIGINT,
    role_id
    BIGINT,
    PRIMARY
    KEY
(
    user_id,
    role_id
),
    FOREIGN KEY
(
    user_id
) REFERENCES users
(
    id
),
    FOREIGN KEY
(
    role_id
) REFERENCES roles
(
    id
)
    );

--rollback DROP TABLE users_roles;
--rollback DROP TABLE users;
--rollback DROP TABLE roles;

--changeset Emre_Celik:2
INSERT INTO roles (name)
VALUES ('USER'),
       ('ADMIN');

--changeset Emre_Celik:3
INSERT INTO users (username, password)
VALUES ('user@example.com', '$2a$10$X5hSGQnD7bWz8s5ZzQq3Ue6W7kLc1oVjJkG8d4wYbR2fN1vKm0SO'),
       ('admin@example.com', '$2a$10$X5hSGQnD7bWz8s5ZzQq3Ue6W7kLc1oVjJkG8d4wYbR2fN1vKm0SO');

--changeset Emre_Celik:4
INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1),
       (2, 1),
       (2, 2);