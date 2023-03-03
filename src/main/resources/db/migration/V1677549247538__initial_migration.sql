CREATE TABLE users
(
    id       int PRIMARY KEY AUTO_INCREMENT,
    username text NOT NULL,
    password text NOT NULL,
    enabled  BOOLEAN DEFAULT TRUE
);

CREATE TABLE roles
(
    id   int PRIMARY KEY AUTO_INCREMENT,
    name text NOT NULL
);

CREATE TABLE authorities
(
    id        int PRIMARY KEY AUTO_INCREMENT,
    username  text NOT NULL,
    authority text NOT NULL
);

CREATE TABLE book
(
    id     int PRIMARY KEY AUTO_INCREMENT,
    id_lib int,
    name   text NOT NULL
);

CREATE TABLE reader_info
(
    id         int PRIMARY KEY AUTO_INCREMENT,
    number_id  int UNIQUE  NOT NULL,
    names      text        NOT NULL,
    last_names text        NOT NULL,
    birth_date date        NOT NULL,
    cell_phone varchar(10) NOT NULL
);

CREATE TABLE lend_items
(
    id      int PRIMARY KEY AUTO_INCREMENT,
    lend_id int NOT NULL,
    book_id int NOT NULL
);

CREATE TABLE lend
(
    id             int PRIMARY KEY AUTO_INCREMENT,
    username       text NOT NULL,
    reader_info_id int  NOT NULL,
    created_on     date DEFAULT now(),
    return_on      date NOT NULL
);

ALTER TABLE lend_items
    ADD CONSTRAINT lend_items_uk UNIQUE (lend_id, book_id);

ALTER TABLE lend_items
    ADD CONSTRAINT lend_items_lend_id_fk FOREIGN KEY (lend_id) REFERENCES lend (id);

ALTER TABLE lend_items
    ADD CONSTRAINT lend_items_book_id_fk FOREIGN KEY (book_id) REFERENCES book (id);

ALTER TABLE lend
    ADD CONSTRAINT lend_reader_info_id_fk FOREIGN KEY (reader_info_id) REFERENCES reader_info (id);
