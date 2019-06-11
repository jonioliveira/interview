CREATE SEQUENCE public.hibernate_sequence INCREMENT 1 START 1 MINVALUE 1;

CREATE TABLE slot(
  id   SERIAL PRIMARY KEY,
  startDate TIMESTAMP NOT NULL,
  endDate TIMESTAMP NOT NULL,
  interviewerId INT NOT NULL,
  candidateId INT NOT NULL,
  statusId INT NOT NULL,
  FOREIGN KEY (statusId) references slotStatus (id)
);

CREATE TABLE slotStatus(
  id SERIAL PRIMARY KEY,
  description VARCHAR(250) NOT NULL,
);

INSERT INTO slotStatus(id, description) VALUES (1, 'availability scheduled');
INSERT INTO slotStatus(id, description) VALUES (2, 'interview scheduled');