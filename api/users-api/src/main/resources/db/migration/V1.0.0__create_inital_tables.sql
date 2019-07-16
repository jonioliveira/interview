CREATE SEQUENCE public.hibernate_sequence INCREMENT 1 START 1 MINVALUE 1;

CREATE TABLE usersType
(
  id   SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);


CREATE TABLE users
(
  id   SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  userTypeId INTEGER NOT NULL,
  FOREIGN KEY (userTypeId) references usersType (id)
);

INSERT INTO usersType(id, name) VALUES (1, 'interview');
INSERT INTO usersType(id, name) VALUES (2, 'candidate');