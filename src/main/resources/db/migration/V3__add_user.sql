CREATE TABLE users
(
  user_name         VARCHAR(100) PRIMARY KEY NOT NULL,
  encoded_password VARCHAR(255)
);

INSERT INTO users (user_name, encoded_password)
VALUES ('user1', '$2a$10$P/Hwaf4hi3WZjRpyVqr.0.1755n9nljwXxlukzCeVzeX8LF5Gb4F.');
INSERT INTO users (user_name, encoded_password)
VALUES ('user2', '$2a$10$P/Hwaf4hi3WZjRpyVqr.0.1755n9nljwXxlukzCeVzeX8LF5Gb4F.');

ALTER TABLE customers
  ADD user_name VARCHAR(100) NOT NULL DEFAULT 'user1';
ALTER TABLE customers
  ADD CONSTRAINT FK_CUSTOMERS_USERNAME FOREIGN KEY (user_name) REFERENCES users;
