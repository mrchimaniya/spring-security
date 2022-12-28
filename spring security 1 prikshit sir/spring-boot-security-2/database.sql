CREATE TABLE IF NOT EXISTS `users` (
`id` INT NOT NULL AUTO_INCREMENT,
`username` VARCHAR(45) NOT NULL,
`password` TEXT NOT NULL,
`algorithm` VARCHAR(45) NOT NULL,
PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `authorities` (
`id` INT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
`user` INT NOT NULL,
PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `product` (
`id` INT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
`price` VARCHAR(45) NOT NULL,
`currency` VARCHAR(45) NOT NULL,
PRIMARY KEY (`id`));

-- password is 12345
INSERT IGNORE INTO `users` (`id`, `username`, `password`, `algorithm`) VALUES ('1', 'john', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'BCRYPT');
INSERT IGNORE INTO `authorities` (`id`, `name`, `user`) VALUES ('1', 'READ', '1');
INSERT IGNORE INTO `authorities` (`id`, `name`, `user`) VALUES ('2', 'WRITE', '1');
INSERT IGNORE INTO `product` (`id`, `name`, `price`, `currency`) VALUES ('1', 'Chocolate', '10', 'USD');
