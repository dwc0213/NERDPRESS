CREATE TABLE member
(
    id          NUMBER(4)	NOT NULL PRIMARY KEY,
    userId      VARCHAR2(10)NOT NULL,
    name        VARCHAR2(10)NOT NULL,
    PW          VARCHAR2(20)NOT NULL,
    email       VARCHAR2(50)NOT NULL,
    birthDate   NUMBER(8)   NOT NULL,
    grade       NUMBER(1)   NOT NULL,
    male        NUMBER(1)   NOT NULL
);