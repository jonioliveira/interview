CREATE SEQUENCE public.hibernate_sequence INCREMENT 1 START 1 MINVALUE 1;

CREATE TABLE interview
(
  id   SERIAL PRIMARY KEY,
  startDate TIMESTAMP NOT NULL,
  endDate TIMESTAMP NOT NULL,
  candidateId INT NOT NULL,
  interviewerId INT NOT NULL
);


CREATE TABLE availability
(
  id   SERIAL PRIMARY KEY,
  startDate TIMESTAMP NOT NULL,
  endDate TIMESTAMP NOT NULL,
  interviewerId INT NOT NULL
);