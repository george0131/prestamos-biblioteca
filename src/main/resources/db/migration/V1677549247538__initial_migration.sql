CREATE TABLE users
(
    id        int PRIMARY KEY AUTO_INCREMENT,
    full_name text NOT NULL,
    username  text NOT NULL,
    password  text NOT NULL,
    role_id   int  NOT NULL
);

CREATE TABLE roles
(
    id   int PRIMARY KEY AUTO_INCREMENT,
    name text NOT NULL
);

CREATE TABLE book
(
    id   int PRIMARY KEY AUTO_INCREMENT,
    id_lib int,
    name text NOT NULL
);

CREATE TABLE reader_info
(
    id         int PRIMARY KEY AUTO_INCREMENT,
    number_id  int UNIQUE  NOT NULL,
    names      text        NOT NULL,
    last_names text        NOT NULL,
    birth_date varchar(15) NOT NULL,
    cell_phone varchar(10) NOT NULL
);

CREATE TABLE lend_items
(
    lend_id int NOT NULL,
    book_id int NOT NULL
);

CREATE TABLE lend
(
    id             int PRIMARY KEY,
    user_id        int       NOT NULL,
    reader_info_id int       NOT NULL,
    created_on     timestamp DEFAULT now(),
    return_on      timestamp NOT NULL
);

CREATE UNIQUE INDEX lend_items_uk ON lend_items (lend_id, book_id);

ALTER TABLE users
    ADD CONSTRAINT users_role_id_fk FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE lend_items
    ADD CONSTRAINT lend_items_lend_id_fk FOREIGN KEY (lend_id) REFERENCES lend (id);

ALTER TABLE lend_items
    ADD CONSTRAINT lend_items_book_id_fk FOREIGN KEY (book_id) REFERENCES book (id);

ALTER TABLE lend
    ADD CONSTRAINT lend_user_id_fk FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE lend
    ADD CONSTRAINT lend_reader_info_id_fk FOREIGN KEY (reader_info_id) REFERENCES reader_info (id);
