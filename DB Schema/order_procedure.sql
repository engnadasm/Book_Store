USE `bookstore`;
DROP function IF EXISTS `order_book`;
DROP procedure IF EXISTS `order_insertion`;

DELIMITER $$
USE `bookstore`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `order_insertion`(IN ordered_ISBN varchar(15),IN copies_no int,IN user_email varchar(30), 
IN sell_date DATETIME)
BEGIN
	UPDATE book SET book.quantity = book.quantity - copies_no
	WHERE book.ISBN = ordered_ISBN;
	INSERT INTO book_sales VALUES(ordered_ISBN, user_email, copies_no, sell_date);
END$$

DELIMITER ;

