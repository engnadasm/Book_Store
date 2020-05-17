USE `bookstore`;
DROP function IF EXISTS `order_book`;

DELIMITER $$
USE `bookstore`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `order_book`(ordered_ISBN varchar(15), copies_no int, user_email varchar(30), 
sell_date DATETIME) RETURNS varchar(500) CHARSET utf8
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

