-- Create User table

CREATE TABLE "User" (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    roles VARCHAR(255) NOT NULL

);


INSERT INTO "User" (password, roles, username) VALUES ('your_password', 'user', 'your_username');

