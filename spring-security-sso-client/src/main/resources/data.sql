----------------------  USERS -------------------------------------

INSERT INTO USERS (EMAIL, NAME, SURNAME, PASSWORD)
VALUES ('admin@mail.ru', 'Admin', 'Admin', '{noop}admin'),
       ('user@mail.ru', 'User', 'User', '{noop}user'),
       ('konstantin@mail.ru', 'Константин Константинович', 'Константинопольский', '{noop}konstantinov');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('ROLE_ADMIN', 1),
       ('ROLE_USER', 1),
       ('ROLE_USER', 2),
       ('ROLE_USER', 3);