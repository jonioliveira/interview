CREATE SEQUENCE public.hibernate_sequence INCREMENT 1 START 1 MINVALUE 1;

CREATE TABLE slot(
  id   SERIAL PRIMARY KEY,
  startDate TIMESTAMP NOT NULL,
  endDate TIMESTAMP NOT NULL,
  interviewerId INT NOT NULL,
  candidateId INT NULL,
  status INT NOT NULL
);