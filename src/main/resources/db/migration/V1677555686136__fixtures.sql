INSERT INTO ROLES (ID, NAME)
VALUES (1, 'ADMINISTRATOR');

ALTER TABLE ROLES ALTER COLUMN ID RESTART WITH 2;

INSERT INTO USERS (ID, FULL_NAME, USERNAME, PASSWORD, ROLE_ID)
SELECT 1, 'Pompilio Paez Pinto', 'ppaez', '$2a$10$vv0kkRNWoopcUqviz7n/peWAUMU9CUcNIIZzmrXBZWIPwEudaRT12', id
FROM ROLES
WHERE NAME = 'ADMINISTRATOR';

ALTER TABLE USERS ALTER COLUMN ID RESTART WITH 2;

INSERT INTO BOOK (ID, ID_LIB, NAME)
VALUES (1, 1234, 'Book 1'), (2, 4567, 'Book 2'), (3, 7890, 'Book 3'),
       (4, 4334, 'Book 4'), (5, 98765678, 'Book 5'),
       (6, 987656789, 'Palindrome book'), (7, 987659, 'Book sum digits');

ALTER TABLE BOOK ALTER COLUMN ID RESTART WITH 8;

INSERT INTO READER_INFO (ID, NUMBER_ID, NAMES, LAST_NAMES, BIRTH_DATE, CELL_PHONE)
VALUES (1, 1140123558, 'Andrés', 'Lopez', '1991-02-28', '300455211'),
       (2, 1140123557, 'Andrea', 'Gomez', '1989-04-28', '300455211'),
       (3, 1140123556, 'Alvaro', 'Rodriguez', '1992-10-28', '300455211'),
       (4, 1140123555, 'Adriana', 'Martinez', '1990-06-28', '300455211');

ALTER TABLE READER_INFO ALTER COLUMN ID RESTART WITH 5;

INSERT INTO LEND (ID, USER_ID, READER_INFO_ID, CREATED_ON, RETURN_ON)
VALUES (1, 1, 1, CURRENT_DATE, DATEADD('DAY',2, CURRENT_DATE)),
       (2, 1, 2, DATEADD('DAY',1, CURRENT_DATE), DATEADD('DAY',3, DATEADD('DAY',1, CURRENT_DATE))),
       (3, 1, 3, DATEADD('DAY',3, CURRENT_DATE), DATEADD('DAY',2, DATEADD('DAY',3, CURRENT_DATE))),
       (4, 1, 4, DATEADD('DAY',5, CURRENT_DATE), DATEADD('DAY',3, DATEADD('DAY',5, CURRENT_DATE))),
       (5, 1, 4, DATEADD('DAY',7, CURRENT_DATE), DATEADD('DAY',4, DATEADD('DAY',7, CURRENT_DATE)));


ALTER TABLE LEND ALTER COLUMN ID RESTART WITH 6;

INSERT INTO LEND_ITEMS (LEND_ID, BOOK_ID)
VALUES (1, 1), (2, 3), (3, 2), (4, 4),
       (5, 5);



