-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Bookstore
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Bookstore
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Bookstore` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema bookstore
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bookstore
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bookstore` DEFAULT CHARACTER SET utf8 ;
USE `Bookstore` ;

-- -----------------------------------------------------
-- Table `Bookstore`.`Publisher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bookstore`.`Publisher` (
  `p_name` VARCHAR(50) NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  `phone_no` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`p_name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Bookstore`.`Book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bookstore`.`Book` (
  `ISBN` VARCHAR(15) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `p_year` YEAR NOT NULL,
  `publisher_name` VARCHAR(50) NULL,
  `category` CHAR(1) NOT NULL,
  `price` INT NOT NULL,
  `threshold` INT NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`ISBN`),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE,
  INDEX `p_name_idx` (`publisher_name` ASC) VISIBLE,
  CONSTRAINT `p_name`
    FOREIGN KEY (`publisher_name`)
    REFERENCES `Bookstore`.`Publisher` (`p_name`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Bookstore`.`Author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bookstore`.`Author` (
  `author_name` VARCHAR(60) NOT NULL,
  `book_ISBN` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`author_name`, `book_ISBN`),
  INDEX `author_isbn_idx` (`book_ISBN` ASC) VISIBLE,
  CONSTRAINT `author_isbn`
    FOREIGN KEY (`book_ISBN`)
    REFERENCES `Bookstore`.`Book` (`ISBN`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Bookstore`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bookstore`.`users` (
  `email` VARCHAR(30) NOT NULL,
  `user_name` VARCHAR(55) NOT NULL,
  `password_user` VARCHAR(30) NOT NULL,
  `first_name` VARCHAR(30) NOT NULL,
  `last_name` VARCHAR(30) NOT NULL,
  `shipping_address` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(12) NOT NULL,
  `is_manager` TINYINT(1) NULL,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  PRIMARY KEY (`email`),
  UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Bookstore`.`Book_sales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bookstore`.`Book_sales` (
  `ISBN` VARCHAR(15) NOT NULL,
  `email` VARCHAR(30) NOT NULL,
  `no_of_copies` INT NOT NULL,
  `date` DATETIME NOT NULL,
  `totalprice` INT,
  INDEX `sales_isbn_idx` (`ISBN` ASC) VISIBLE,
  INDEX `sales_email_idx` (`email` ASC) VISIBLE,
  CONSTRAINT `sales_isbn`
    FOREIGN KEY (`ISBN`)
    REFERENCES `Bookstore`.`Book` (`ISBN`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `sales_email`
    FOREIGN KEY (`email`)
    REFERENCES `Bookstore`.`users` (`email`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Bookstore`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bookstore`.`orders` (
  `ISBN` VARCHAR(15) NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`ISBN`),
  CONSTRAINT `order_isbn2`
    FOREIGN KEY (`ISBN`)
    REFERENCES `Bookstore`.`Book` (`ISBN`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

USE `bookstore` ;

-- -----------------------------------------------------
-- Table `bookstore`.`publisher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookstore`.`publisher` (
  `p_name` VARCHAR(50) NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  `phone_no` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`p_name`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bookstore`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookstore`.`book` (
  `ISBN` VARCHAR(15) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `p_year` YEAR NOT NULL,
  `publisher_name` VARCHAR(50) NULL DEFAULT NULL,
  `category` CHAR(1) NOT NULL,
  `price` INT NOT NULL,
  `threshold` INT NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`ISBN`),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE,
  INDEX `p_name_idx` (`publisher_name` ASC) VISIBLE,
  CONSTRAINT `p_name`
    FOREIGN KEY (`publisher_name`)
    REFERENCES `bookstore`.`publisher` (`p_name`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bookstore`.`author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookstore`.`author` (
  `author_name` VARCHAR(60) NOT NULL,
  `book_ISBN` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`author_name`, `book_ISBN`),
  INDEX `author_isbn_idx` (`book_ISBN` ASC) VISIBLE,
  CONSTRAINT `author_isbn`
    FOREIGN KEY (`book_ISBN`)
    REFERENCES `bookstore`.`book` (`ISBN`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bookstore`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookstore`.`users` (
  `email` VARCHAR(30) NOT NULL,
  `user_name` VARCHAR(55) NOT NULL,
  `password_user` VARCHAR(30) NOT NULL,
  `first_name` VARCHAR(30) NOT NULL,
  `last_name` VARCHAR(30) NOT NULL,
  `shipping_address` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(12) NOT NULL,
  `is_manager` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`email`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bookstore`.`book_sales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookstore`.`book_sales` (
  `ISBN` VARCHAR(15) NOT NULL,
  `email` VARCHAR(30) NOT NULL,
  `no_of_copies` INT NOT NULL,
  `sell_date` DATETIME NOT NULL,
  INDEX `sales_isbn_idx` (`ISBN` ASC) VISIBLE,
  INDEX `sales_email_idx` (`email` ASC) VISIBLE,
  CONSTRAINT `sales_email`
    FOREIGN KEY (`email`)
    REFERENCES `bookstore`.`users` (`email`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `sales_isbn`
    FOREIGN KEY (`ISBN`)
    REFERENCES `bookstore`.`book` (`ISBN`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `bookstore` ;

-- -----------------------------------------------------
-- function add_book
-- -----------------------------------------------------

DELIMITER $$
USE `bookstore`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `add_book`(_ISBN varchar(15),
	_title varchar(100) ,
	_p_year date ,
	_publisher_name varchar(50) ,
	_category char(1) ,
	_price int ,
	_threshold int ,
	_quantity int) RETURNS varchar(500) CHARSET utf8
    DETERMINISTIC
BEGIN
declare msg varchar(500);
IF(SELECT EXISTS(SELECT * FROM book WHERE ISBN = _ISBN) = 1) THEN
	SET msg = concat('A book with this ISBN already exists.');
ELSE
    INSERT INTO book VALUES(_ISBN, _title, _p_year, _publisher_name, _category, _price, _threshold,
    _quantity);
    set msg = concat('The book is added successfully.');
END IF;
RETURN msg;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function log_in
-- -----------------------------------------------------

DELIMITER $$
USE `bookstore`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `log_in`(email_user varchar(30), pass_user varchar(30)) RETURNS varchar(500) CHARSET utf8
    DETERMINISTIC
BEGIN
declare msg varchar(500);
if (SELECT EXISTS(SELECT * from users WHERE email = email_user) = 0)
then set msg = concat('Please check your email, you should register before.....');
elseif (! (select(SELECT password_user from users WHERE email = email_user) LIKE pass_user))
then set msg = concat('password is incorrect, Please check your password.....');
else
set msg = concat('You log_in Successfully.....');
end if ;
return msg ;
End$$

DELIMITER ;

-- -----------------------------------------------------
-- function order_book
-- -----------------------------------------------------

DELIMITER $$
USE `bookstore`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `order_book`(ordered_ISBN varchar(15), copies_no int, user_email varchar(30), 
sell_date DATE) RETURNS varchar(500) CHARSET utf8
    DETERMINISTIC
BEGIN
declare msg varchar(500);
declare num int;
SELECT quantity into num FROM book
WHERE book.ISBN = ordered_ISBN;
IF copies_no <=  num  THEN set msg = concat('The order is done correctly.'); 
ELSE set msg = concat("The number of books can't be negative");
END IF; 
RETURN msg;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function sign_up
-- -----------------------------------------------------

DELIMITER $$
USE `bookstore`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `sign_up`(	email_user varchar(30) ,
	user_name varchar(55) ,
	password_user varchar(30) ,
	first_name varchar(30) ,
	last_name varchar(30) ,
	shipping_address varchar(50) ,
	phone varchar(12)) RETURNS varchar(500) CHARSET utf8
    DETERMINISTIC
BEGIN
declare msg varchar(500);
if (SELECT EXISTS(SELECT * from users WHERE email = email_user) = 1)
then set msg = concat('Please check your email, this email exists already.....');
else
set msg = concat('You sign_up Successfully.....');
INSERT INTO users VALUES(email_user , user_name  , password_user , first_name ,
	last_name , shipping_address , phone, false );
end if ;
return msg ;
End$$

DELIMITER ;
-- -----------------------------------------------------
-- procedure order_insertion
-- -----------------------------------------------------

USE `bookstore`;
DROP procedure IF EXISTS `order_insertion`;

DELIMITER $$
USE `bookstore`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `order_insertion`(IN ordered_ISBN varchar(15),IN copies_no int,IN user_email varchar(30), 
IN sell_date DATETIME)
BEGIN
	declare book_price int;
	SELECT price into book_price FROM book
    WHERE ordered_ISBN = book.ISBN;
	UPDATE book SET book.quantity = book.quantity - copies_no
	WHERE book.ISBN = ordered_ISBN;
	INSERT INTO book_sales VALUES(ordered_ISBN, user_email, copies_no, sell_date, book_price * copies_no); 
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function update_user_info
-- -----------------------------------------------------

DELIMITER $$
USE `bookstore`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `update_user_info`(user_email varchar(30) ,
	new_user_name varchar(55), new_password_user varchar(30) ,
    new_first_name varchar(30) , new_last_name varchar(30) ,
	new_shipping_address varchar(50) , new_phone varchar(12)) RETURNS tinyint(1)
    DETERMINISTIC
BEGIN
declare done boolean DEFAULT true;
UPDATE users
 SET user_name = new_user_name ,
	password_user = new_password_user,
	first_name = new_first_name,
    last_name = new_last_name,
    shipping_address = new_shipping_address,
    phone = new_phone
WHERE email = user_email;
return done ;
End$$

DELIMITER ;
USE `Bookstore`;

DELIMITER $$
USE `bookstore`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `bookstore`.`cofirm_order`
BEFORE DELETE ON `bookstore`.`orders`
FOR EACH ROW
BEGIN
	UPDATE book 
	SET book.quantity = book.quantity + OLD.quantity
	WHERE book.ISBN = OLD.ISBN; 
END$$


DELIMITER ;
USE `bookstore`;

DELIMITER $$
USE `bookstore`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `bookstore`.`modify_existing_books`
BEFORE UPDATE ON `bookstore`.`book`
FOR EACH ROW
BEGIN
IF NEW.quantity < 0 THEN
	SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = "The number of books can't be negative";
END IF;
END$$

USE `bookstore`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `bookstore`.`orders_on_books`
AFTER UPDATE ON `bookstore`.`book`
FOR EACH ROW
BEGIN
IF NEW.quantity < NEW.threshold THEN
	if (SELECT EXISTS(SELECT * from orders WHERE orders.ISBN = new.ISBN) = 0) then
		INSERT INTO orders VALUES(NEW.ISBN,2 * NEW.threshold);
	end if;

END IF;
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
