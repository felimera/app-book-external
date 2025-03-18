-- Creating or deleting tables.
drop table if exists tbl_book_category;
drop table if exists tbl_sub_category;
drop table if exists tbl_category;
drop table if exists tbl_book;

-- Building the tables.
create table tbl_book (
    boo_id int auto_increment primary key
);

create table tbl_sub_category (
    sub_id int auto_increment primary key,
    sub_name varchar(100) not null,
    sub_description varchar(1000) null,
    sub_id_category int not null
);

create table tbl_category (
    cat_id int auto_increment primary key,
    cat_name varchar(100) not null,
    cat_description varchar(1000) null
);

create table tbl_book_category (
    boca_id int auto_increment primary key,
    boca_id_book int not null,
    boca_id_category int not null
);

-- Related attributes of tables
alter table tbl_sub_category add foreign key (sub_id_category) references tbl_category(cat_id);

alter table tbl_book_category add foreign key (boca_id_book) references tbl_book(boo_id);
alter table tbl_book_category add foreign key (boca_id_category) references tbl_category(cat_id);

-- Categorías Principales
INSERT INTO tbl_category (cat_id, cat_name, cat_description) VALUES (1, 'Ficción', 'Libros de ficción en general');
INSERT INTO tbl_category (cat_id, cat_name, cat_description) VALUES (2, 'No Ficción', 'Libros de no ficción en general');
INSERT INTO tbl_category (cat_id, cat_name, cat_description) VALUES (3, 'Infantil', 'Libros para niños');
INSERT INTO tbl_category (cat_id, cat_name, cat_description) VALUES (4, 'Juvenil', 'Libros para jóvenes');
-- Subcategorías de Ficción
INSERT INTO tbl_sub_category (sub_id, sub_name, sub_description, sub_id_category) VALUES (5, 'Ciencia Ficción', 'Libros de ciencia ficción', 1);
INSERT INTO tbl_sub_category (sub_id, sub_name, sub_description, sub_id_category) VALUES (6, 'Fantasía', 'Libros de fantasía', 1);
INSERT INTO tbl_sub_category (sub_id, sub_name, sub_description, sub_id_category) VALUES (7, 'Terror', 'Libros de terror', 1);
INSERT INTO tbl_sub_category (sub_id, sub_name, sub_description, sub_id_category) VALUES (8, 'Romance', 'Libros de romance', 1);
-- Subcategorías de No Ficción
INSERT INTO tbl_sub_category (sub_id, sub_name, sub_description, sub_id_category) VALUES (9, 'Biografía', 'Libros de biografías', 2);
INSERT INTO tbl_sub_category (sub_id, sub_name, sub_description, sub_id_category) VALUES (10, 'Historia', 'Libros de historia', 2);
INSERT INTO tbl_sub_category (sub_id, sub_name, sub_description, sub_id_category) VALUES (11, 'Ciencia', 'Libros de ciencia', 2);
INSERT INTO tbl_sub_category (sub_id, sub_name, sub_description, sub_id_category) VALUES (12, 'Autoayuda', 'Libros de autoayuda', 2);
-- Subcategorías de Infantil
INSERT INTO tbl_sub_category (sub_id, sub_name, sub_description, sub_id_category) VALUES (13, 'Cuentos', 'Libros de cuentos infantiles', 3);
INSERT INTO tbl_sub_category (sub_id, sub_name, sub_description, sub_id_category) VALUES (14, 'Libros Ilustrados', 'Libros infantiles con ilustraciones', 3);
-- Subcategorías de Juvenil
INSERT INTO tbl_sub_category (sub_id, sub_name, sub_description, sub_id_category) VALUES (15, 'Aventura Juvenil', 'Libros de aventura para jóvenes', 4);
INSERT INTO tbl_sub_category (sub_id, sub_name, sub_description, sub_id_category) VALUES (16, 'Fantasía Juvenil', 'Libros de fantasía para jóvenes', 4);