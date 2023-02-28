INSERT INTO roles (name)
VALUES ('ADMINISTRATOR');

INSERT INTO users (id, full_name, username, password, role_id)
SELECT 1, 'Pompilio Paez Pinto', 'ppaez', '$2a$10$vv0kkRNWoopcUqviz7n/peWAUMU9CUcNIIZzmrXBZWIPwEudaRT12', id
FROM roles
WHERE name = 'ADMINISTRATOR';

INSERT INTO book (id, id_lib, name)
VALUES (1, 1234, 'Book 1'), (2, 4567, 'Book 2'), (3, 7890, 'Book 3'),
       (4, 4334, 'Book 4'), (5, 98765678, 'Book 5');

INSERT INTO reader_info (id, number_id, names, last_names, birth_date, cell_phone)
VALUES (1, 1140123558, 'Andrés', 'Lopez', '1991-02-28', '300455211'),
       (2, 1140123557, 'Andrea', 'Gomez', '1989-04-28', '300455211'),
       (3, 1140123556, 'Alvaro', 'Rodriguez', '1992-10-28', '300455211'),
       (4, 1140123555, 'Adriana', 'Martinez', '1990-06-28', '300455211');

INSERT INTO lend (id, user_id, reader_info_id, created_on, return_on)
VALUES (1, 1, 1, CURRENT_TIMESTAMP, DATEADD('DAY',2, CURRENT_TIMESTAMP)),
       (2, 1, 2, DATEADD('DAY',1, CURRENT_TIMESTAMP), DATEADD('DAY',3, DATEADD('DAY',1, CURRENT_TIMESTAMP))),
       (3, 1, 3, DATEADD('DAY',3, CURRENT_TIMESTAMP), DATEADD('DAY',2, DATEADD('DAY',3, CURRENT_TIMESTAMP))),
       (4, 1, 4, DATEADD('DAY',5, CURRENT_TIMESTAMP), DATEADD('DAY',3, DATEADD('DAY',5, CURRENT_TIMESTAMP))),
       (5, 1, 4, DATEADD('DAY',7, CURRENT_TIMESTAMP), DATEADD('DAY',4, DATEADD('DAY',7, CURRENT_TIMESTAMP)));

INSERT INTO lend_items (lend_id, book_id)
VALUES (1, 1), (1, 2), (2, 3), (2, 4), (3, 2), (3, 3), (4, 1), (4, 3),
       (5, 5);



