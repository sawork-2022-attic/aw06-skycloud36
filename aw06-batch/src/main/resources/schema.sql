CREATE TABLE IF NOT EXISTS `products` (
	`main_cat` varchar(63),
	`title` varchar(63),
	`asin` char(15),
	`price` double,
	`category` varchar(63),
	`imageURLHighRe` varchar(255),
	PRIMARY KEY (`asin`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

