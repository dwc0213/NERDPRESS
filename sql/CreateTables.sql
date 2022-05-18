CREATE TABLE member
(
    id          NUMBER(4)	NOT NULL PRIMARY KEY,
    userId      VARCHAR2(10),
    name        VARCHAR2(10),
    PW          VARCHAR2(20),
    birthDate   NUMBER(8),
    grade       NUMBER(1),
    male        NUMBER(1),
);