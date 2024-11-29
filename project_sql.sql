
DROP DATABASE Project2;

CREATE DATABASE Project2;

USE Project2;

CREATE TABLE Book(
	reference_id INT NOT NULL UNIQUE,
	title VARCHAR(15),
	language VARCHAR(10),
	pages INT,
	year_prod INT,
	subject VARCHAR(15),
	author VARCHAR(15),
	publisher VARCHAR(15),
	PRIMARY KEY (reference_id)
)

CREATE TABLE BookCopy(
	barcode INT NOT NULL UNIQUE,
	book_ref INT,
	price INT,
	date_prod DATE,
	rack_num INT,
	PRIMARY KEY (barcode),
	FOREIGN KEY (book_ref) REFERENCES Book(reference_id)
)

CREATE TABLE Computer(
	barcode INT NOT NULL UNIQUE,
	model INT,
	date_prod DATE,
	rack_num INT,
	PRIMARY KEY (barcode)
)

CREATE TABLE Room(
	room_num INT NOT NULL UNIQUE,
	number_of_people INT,
	PRIMARY KEY (room_num)
);

DROP TABLE Student;

CREATE TABLE Student(
	student_id INT NOT NULL UNIQUE,
	first_name varchar(10),
	last_name varchar(10),
	address varchar(15),
	email varchar(20) CHECK (REGEXP_LIKE(email, '^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$')),
	phone varchar(9),
	student_password varchar(12),
	attends_uni BOOLEAN DEFAULT TRUE,
	PRIMARY KEY(student_id)
);

CREATE TABLE Borrow_book(
	book_id INT,
	student_id INT,
	date_start DATE,
	date_due DATE,
	FOREIGN KEY(book_id) REFERENCES BookCopy(barcode),
	FOREIGN KEY(student_id) REFERENCES Student(student_id)
)

CREATE TABLE Borrow_computer(
	computer_id INT,
	student_id INT,
	date_start DATE,
	date_due DATE,
	FOREIGN KEY(computer_id) REFERENCES Computer(barcode),
	FOREIGN KEY(student_id) REFERENCES Student(student_id)
)

CREATE TABLE Borrow_room(
	room_num INT,
	student_id INT,
	date_start DATE,
	date_due DATE,
	FOREIGN KEY(room_num) REFERENCES Room(room_num),
	FOREIGN KEY(student_id) REFERENCES Student(student_id)
)

CREATE TABLE library_card(
	library_num INT UNIQUE NOT NULL AUTO_INCREMENT,
	date_act DATE,
	status ENUM('Active', 'Deactive') DEFAULT 'Active',
	PRIMARY KEY(library_num)
)

CREATE TABLE resource_card(
	r_number INT NOT NULL UNIQUE,
	date_act DATE,
	status ENUM('Active', 'Deactive') DEFAULT 'Active',
	resource ENUM('Book', 'Room','Computer') DEFAULT'Book',
	PRIMARY KEY(r_number)
)

ALTER TABLE library_card ADD COLUMN cards INT;
ALTER TABLE library_card ADD FOREIGN KEY (cards) REFERENCES resource_card(r_number);

ALTER TABLE Student ADD COLUMN library_card INT;
ALTER TABLE Student ADD FOREIGN KEY (library_card) REFERENCES library_card(library_num);


SELECT * FROM Student;

INSERT INTO library_card( date_act) VALUES (CURRENT_TIME);

INSERT INTO Student(student_id, first_name, last_name, address, email, phone, student_password, attends_uni, library_card)
VALUES (1, "Mason", "Bair", "hahaha", "mason@gmail.com", "440440", "1234", TRUE, 1);

INSERT INTO Student(student_id, first_name, last_name, address, email, phone, student_password, library_card)
VALUES (2, "Mason", "Bair", "hahaha", "mason@gmail.com", "440440", "1234", 1);

INSERT INTO resource_card(r_number, date_act, status, resource)
VALUES (1, CURRENT_DATE, "Active", "Book");
