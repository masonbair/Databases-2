
DROP DATABASE Project2;

CREATE DATABASE Project2;

USE Project2;

SET @encryption_key = "vcqAQuAB6zC6yCTN3B+PAw==";

CREATE TABLE Book(
	title VARCHAR(30) NOT NULL UNIQUE,
	language VARCHAR(10),
	pages INT,
	year_prod INT,
	subject VARCHAR(15),
	author VARCHAR(15),
	publisher VARCHAR(15),
	PRIMARY KEY (title)
)

CREATE TABLE BookCopy(
	barcode INT NOT NULL UNIQUE AUTO_INCREMENT,
	book_ref VARCHAR(30),
	price INT,
	date_prod DATE default (CURRENT_DATE),
	rack_num INT,
	copy_language VARCHAR(15),
	PRIMARY KEY (barcode),
	FOREIGN KEY (book_ref) REFERENCES Book(title)
)

DROP TABLE Computer ;

CREATE TABLE Computer(
	barcode INT NOT NULL UNIQUE,
	model varchar(15),
	date_prod DATE default (CURRENT_DATE),
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
	student_id INT NOT NULL UNIQUE AUTO_INCREMENT,
	first_name varchar(10),
	last_name varchar(10),
	address varchar(15),
	email varchar(20) CHECK (REGEXP_LIKE(email, '^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$')) default "null@null.com",
	phone varchar(9),
	student_password VARCHAR(255),
	attends_uni BOOLEAN DEFAULT TRUE,
	PRIMARY KEY(student_id)
);


-- I HAVE NOT ABLE TO GET THE ENCRYPTION TO WORK FOR THE SQL ---------------------------------------------------------------------------------------------------

-- ENCRYPTION ON INSERT
-- DROP TRIGGER EncryptPasswordBeforeInsert;
-- 
-- CREATE TRIGGER EncryptPasswordBeforeInsert
-- BEFORE INSERT ON Student
-- FOR EACH ROW
-- BEGIN
--    IF NEW.student_password IS NOT NULL AND NEW.student_password != '' THEN
--         SET NEW.student_password = HEX(AES_ENCRYPT(NEW.student_password, @encryption_key));
--     END IF;
-- END;
-- 
-- DROP TRIGGER EncryptPasswordBeforeUpdate;
-- 
-- CREATE TRIGGER EncryptPasswordBeforeUpdate
-- BEFORE UPDATE ON Student
-- FOR EACH ROW
-- BEGIN
--     IF NEW.student_password IS NOT NULL AND NEW.student_password != '' THEN
--         SET NEW.student_password = HEX(AES_ENCRYPT(NEW.student_password, @encryption_key));
--     END IF;
-- END;



-- DROP TRIGGER check_number_of_books;


CREATE TABLE Borrow_book(
	book_id INT,
	student_id INT,
	date_start DATE default (CURRENT_DATE),
	date_due DATE,
	FOREIGN KEY(book_id) REFERENCES BookCopy(barcode),
	FOREIGN KEY(student_id) REFERENCES Student(student_id)
)

-- FOR WORK ON MAKING SURE THE NUMBER OF BOOKS BORROWED STAY BELOW % OR SOMEIMES 1

CREATE TRIGGER check_number_of_books
BEFORE INSERT ON Borrow_book
FOR EACH ROW
BEGIN
    DECLARE num_books INT;
   DECLARE max_books_allowed INT;
  
	SELECT CASE
		WHEN attends_uni = TRUE THEN 5
		ELSE 1
	END INTO max_books_allowed
	FROM Student
	WHERE student_id = new.student_id;
   
   SELECT COUNT(*) INTO num_books
   FROM Borrow_book 
   WHERE student_id = new.student_id;

   
   IF num_books >= max_books_allowed THEN
	   SIGNAL SQLSTATE '45000'
	  	SET MESSAGE_TEXT = "The student has reached the borrowing limit.", MYSQL_ERRNO = 1001;
	END IF;
END;

CREATE TRIGGER check_for_book_borrow BEFORE INSERT ON Borrow_book
FOR EACH ROW 
BEGIN 
	IF EXISTS(SELECT 1 FROM Borrow_book WHERE book_id = new.book_id) THEN 
		SIGNAL SQLSTATE '45000'
	  	SET MESSAGE_TEXT = "This book is already checked out", MYSQL_ERRNO=1001;
	 END  IF;
END;
	

CREATE TRIGGER set_date_due BEFORE INSERT ON Borrow_book
FOR EACH ROW
BEGIN
    IF NEW.date_due IS NULL THEN
        SET NEW.date_due = DATE_ADD(CURRENT_DATE, INTERVAL 7 DAY);
    END IF;
END;

CREATE TABLE Borrow_computer(
	computer_id INT,
	student_id INT,
	date_start DATE default (CURRENT_DATE),
	date_due DATE,
	FOREIGN KEY(computer_id) REFERENCES Computer(barcode),
	FOREIGN KEY(student_id) REFERENCES Student(student_id)
);

CREATE TRIGGER check_for_computer_borrow BEFORE INSERT ON Borrow_computer
FOR EACH ROW 
BEGIN 
	IF EXISTS(SELECT 1 FROM Borrow_computer WHERE computer_id = new.computer_id) THEN 
		SIGNAL SQLSTATE '45000'
	  	SET MESSAGE_TEXT = "This computer is already checked out", MYSQL_ERRNO=1001;
	 END  IF;
END;

CREATE TRIGGER set_computer_due_date BEFORE INSERT ON Borrow_computer
FOR EACH ROW
BEGIN
    IF NEW.date_due IS NULL THEN
        SET NEW.date_due = DATE_ADD(CURRENT_DATE, INTERVAL 7 DAY);
    END IF;
END;

CREATE TABLE Borrow_room(
	room_num INT,
	student_id INT,
	date_start DATE default (CURRENT_DATE),
	date_due DATE,
	FOREIGN KEY(room_num) REFERENCES Room(room_num),
	FOREIGN KEY(student_id) REFERENCES Student(student_id)
);

DROP TRIGGER check_for_room_borrow;

CREATE TRIGGER check_for_room_borrow BEFORE INSERT ON Borrow_room
FOR EACH ROW 
BEGIN 
	IF EXISTS(SELECT 1 FROM Borrow_room WHERE room_num = new.room_num) THEN 
		SIGNAL SQLSTATE '45000'
	  	SET MESSAGE_TEXT = "This room is already checked out", MYSQL_ERRNO=1001;
	 END  IF;
END;


CREATE TRIGGER set_room_date_due 
BEFORE INSERT ON Borrow_room
FOR EACH ROW
BEGIN
    IF NEW.date_due IS NULL THEN
        SET NEW.date_due = DATE_ADD(CURRENT_DATE, INTERVAL 7 DAY);
    END IF;
END;


CREATE TABLE library_card(
	library_num INT UNIQUE NOT NULL AUTO_INCREMENT,
	date_act DATE default (CURRENT_DATE),
	status ENUM('Active', 'Deactive') DEFAULT 'Active',
	room BOOLEAN DEFAULT FALSE,
	book BOOLEAN DEFAULT FALSE,
	computer BOOLEAN DEFAULT FALSE,
	PRIMARY KEY(library_num)
)

ALTER TABLE Student ADD COLUMN library_card INT;
ALTER TABLE Student ADD FOREIGN KEY (library_card) REFERENCES library_card(library_num);


-- SELECT * FROM Student;
-- 
-- INSERT INTO library_card( date_act) VALUES (CURRENT_TIME);
-- 
-- INSERT INTO Student(student_id, first_name, last_name, address, email, phone, student_password, attends_uni, library_card)
-- VALUES (1, "Mason", "Bair", "hahaha", "mason@gmail.com", "440440", "1234", TRUE, 1);
-- 
-- INSERT INTO Student(student_id, first_name, last_name, address, email, phone, student_password, library_card)
-- VALUES (2, "Mason", "Bair", "hahaha", "mason@gmail.com", "440440", "1234", 1);
-- 
-- INSERT INTO resource_card(r_number, date_act, status, resource)
-- VALUES (1, CURRENT_DATE, "Active", "Book");

DELIMITER $$

CREATE TRIGGER check_room_before_insert
BEFORE INSERT ON Borrow_room
FOR EACH ROW
BEGIN
    -- Check if the student's card has 'book' set to TRUE
    DECLARE is_room_allowed BOOLEAN DEFAULT FALSE;

    -- Get the 'book' value from the student's card
    SELECT C.room INTO is_room_allowed
    FROM Student S
    INNER JOIN library_card C ON C.library_num = S.library_card
    WHERE S.student_id = NEW.student_id;

    -- If 'book' is not TRUE, raise an error
    IF is_room_allowed != TRUE THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: The student is not allowed to borrow a room.', MYSQL_ERRNO=1001;
    END IF;
END;

DELIMITER ;

DELIMITER $$

DROP TRIGGER check_book_before_insert;

CREATE TRIGGER check_book_before_insert
BEFORE INSERT ON Borrow_book
FOR EACH ROW
BEGIN
    -- Check if the student's card has 'book' set to TRUE
    DECLARE is_book_allowed BOOLEAN DEFAULT FALSE;

    -- Get the 'book' value from the student's card
    SELECT C.book INTO is_book_allowed
    FROM Student S
    INNER JOIN library_card C ON C.library_num = S.library_card
    WHERE S.student_id = NEW.student_id;

    -- If 'book' is not TRUE, raise an error
    IF is_book_allowed != TRUE THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: The student is not allowed to borrow a book.', MYSQL_ERRNO=1001;
    END IF;
END;

DELIMITER ;

DELIMITER $$

CREATE TRIGGER check_pc_before_insert
BEFORE INSERT ON Borrow_computer
FOR EACH ROW
BEGIN
    -- Check if the student's card has 'book' set to TRUE
    DECLARE is_pc_allowed BOOLEAN DEFAULT FALSE;

    -- Get the 'book' value from the student's card
    SELECT C.computer INTO is_pc_allowed
    FROM Student S
    INNER JOIN library_card C ON C.library_num = S.library_card
    WHERE S.student_id = NEW.student_id;

    -- If 'book' is not TRUE, raise an error
    IF is_pc_allowed != TRUE THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: The student is not allowed to borrow a Computer.', MYSQL_ERRNO=1001;
    END IF;
END;

DELIMITER ;

-- SELECT * FROM Borrow_book
-- LEFT JOIN Student ON Student.student_id = Borrow_book.student_id 
-- LEFT JOIN BookCopy ON BookCopy.barcode = Borrow_book.book_id
-- WHERE Student.student_id = 1 AND student_password = "admin"; 

-- INSERT INTO Borrow_book(student_id, book_id) VALUES (1, 1);

-- INSERT INTO Student(student_id, student_password) values (4,'1');
-- UPDATE Student SET student_password=1 WHERE student_id=1;
-- 
-- SELECT AES_DECRYPT(UNHEX(student_password), @encrpytion_key) AS decrypted_password, student_password 
-- FROM Student
-- WHERE student_id=4;

