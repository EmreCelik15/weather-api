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
    id
    BIGINT
    PRIMARY
    KEY
    AUTO_INCREMENT,
    user_id
    BIGINT,
    role_id
    BIGINT,
    FOREIGN KEY
(
    user_id
) REFERENCES users

(
    id
) ON DELETE SET NULL,
    FOREIGN KEY
(
    role_id
) REFERENCES roles
(
    id
)ON DELETE SET NULL
    );

--rollback DROP TABLE users_roles;
--rollback DROP TABLE users;
--rollback DROP TABLE roles;

--changeset Emre_Celik:2
INSERT INTO roles (name)
VALUES ('USER'),
       ('ADMIN'),
       ('MANAGER'),
       ('EDITOR'),
       ('VIEWER'),
       ('ANALYST'),
       ('DEVELOPER'),
       ('TESTER'),
       ('DESIGNER'),
       ('SUPPORT'),
       ('GUEST'),
       ('AUDITOR'),
       ('CONSULTANT'),
       ('ARCHITECT'),
       ('ADVISOR'),
       ('TRAINER'),
       ('WRITER'),
       ('REVIEWER'),
       ('MODERATOR'),
       ('SUPERVISOR');

--changeset Emre_Celik:3
INSERT INTO users (username, password)
VALUES ('john.doe@example.com', '$2a$10$X5hSGQnD7bWz8s5ZzQq3Ue6W7kLc1oVjJkG8d4wYbR2fN1vKm0SO'),
       ('jane.smith@example.com', '$2a$10$X5hSGQnD7bWz8s5ZzQq3Ue6W7kLc1oVjJkG8d4wYbR2fN1vKm0SO'),
       ('michael.brown@example.com', '$2a$10$X5hSGQnD7bWz8s5ZzQq3Ue6W7kLc1oVjJkG8d4wYbR2fN1vKm0SO'),
       ('emily.jones@example.com', '$2a$10$X5hSGQnD7bWz8s5ZzQq3Ue6W7kLc1oVjJkG8d4wYbR2fN1vKm0SO'),
       ('david.wilson@example.com', '$2a$10$X5hSGQnD7bWz8s5ZzQq3Ue6W7kLc1oVjJkG8d4wYbR2fN1vKm0SO'),
       ('sarah.miller@example.com', '$2a$10$X5hSGQnD7bWz8s5ZzQq3Ue6W7kLc1oVjJkG8d4wYbR2fN1vKm0SO'),
       ('james.taylor@example.com', '$2a$10$X5hSGQnD7bWz8s5ZzQq3Ue6W7kLc1oVjJkG8d4wYbR2fN1vKm0SO'),
       ('linda.anderson@example.com', '$2a$10$X5hSGQnD7bWz8s5ZzQq3Ue6W7kLc1oVjJkG8d4wYbR2fN1vKm0SO'),
       ('robert.thomas@example.com', '$2a$10$X5hSGQnD7bWz8s5ZzQq3Ue6W7kLc1oVjJkG8d4wYbR2fN1vKm0SO'),
       ('mary.jackson@example.com', '$2a$10$X5hSGQnD7bWz8s5ZzQq3Ue6W7kLc1oVjJkG8d4wYbR2fN1vKm0SO'),
       ('william.white@example.com', '$2a$10$X5hSGQnD7bWz8s5ZzQq3Ue6W7kLc1oVjJkG8d4wYbR2fN1vKm0SO'),
       ('elizabeth.harris@example.com', '$2a$10$X5hSGQnD7bWz8s5ZzQq3Ue6W7kLc1oVjJkG8d4wYbR2fN1vKm0SO'),
       ('charles.martin@example.com', '$2a$10$X5hSGQnD7bWz8s5ZzQq3Ue6W7kLc1oVjJkG8d4wYbR2fN1vKm0SO'),
       ('jennifer.thompson@example.com', '$2a$10$X5hSGQnD7bWz8s5ZzQq3Ue6W7kLc1oVjJkG8d4wYbR2fN1vKm0SO'),
       ('joseph.garcia@example.com', '$2a$10$X5hSGQnD7bWz8s5ZzQq3Ue6W7kLc1oVjJkG8d4wYbR2fN1vKm0SO'),
       ('patricia.martinez@example.com', '$2a$10$X5hSGQnD7bWz8s5ZzQq3Ue6W7kLc1oVjJkG8d4wYbR2fN1vKm0SO'),
       ('thomas.robinson@example.com', '$2a$10$X5hSGQnD7bWz8s5ZzQq3Ue6W7kLc1oVjJkG8d4wYbR2fN1vKm0SO'),
       ('susan.clark@example.com', '$2a$10$X5hSGQnD7bWz8s5ZzQq3Ue6W7kLc1oVjJkG8d4wYbR2fN1vKm0SO'),
       ('daniel.rodriguez@example.com', '$2a$10$X5hSGQnD7bWz8s5ZzQq3Ue6W7kLc1oVjJkG8d4wYbR2fN1vKm0SO'),
       ('margaret.lewis@example.com', '$2a$10$X5hSGQnD7bWz8s5ZzQq3Ue6W7kLc1oVjJkG8d4wYbR2fN1vKm0SO');

--changeset Emre_Celik:4
INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1),  -- John Doe -> USER
       (2, 2),  -- Jane Smith -> ADMIN
       (3, 3),  -- Michael Brown -> MANAGER
       (4, 4),  -- Emily Jones -> EDITOR
       (5, 5),  -- David Wilson -> VIEWER
       (6, 6),  -- Sarah Miller -> ANALYST
       (7, 7),  -- James Taylor -> DEVELOPER
       (8, 8),  -- Linda Anderson -> TESTER
       (9, 9),  -- Robert Thomas -> DESIGNER
       (10, 10), -- Mary Jackson -> SUPPORT
       (11, 11), -- William White -> GUEST
       (12, 12), -- Elizabeth Harris -> AUDITOR
       (13, 13), -- Charles Martin -> CONSULTANT
       (14, 14), -- Jennifer Thompson -> ARCHITECT
       (15, 15), -- Joseph Garcia -> ADVISOR
       (16, 16), -- Patricia Martinez -> TRAINER
       (17, 17), -- Thomas Robinson -> WRITER
       (18, 18), -- Susan Clark -> REVIEWER
       (19, 19), -- Daniel Rodriguez -> MODERATOR
       (20, 20); -- Margaret Lewis -> SUPERVISOR