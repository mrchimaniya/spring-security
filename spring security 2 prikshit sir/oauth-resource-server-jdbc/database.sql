CREATE TABLE IF NOT EXISTS `oauth_access_token` (
`token_id` varchar(255) NOT NULL,
`token` blob,
`authentication_id` varchar(255) DEFAULT NULL,
`user_name` varchar(255) DEFAULT NULL,
`client_id` varchar(255) DEFAULT NULL,
`authentication` blob,
`refresh_token` varchar(255) DEFAULT NULL,
PRIMARY KEY (`token_id`));
CREATE TABLE IF NOT EXISTS `oauth_refresh_token` (
`token_id` varchar(255) NOT NULL,
`token` blob,
`authentication` blob,
PRIMARY KEY (`token_id`));


CREATE TABLE IF NOT EXISTS `user` (
`id` INT NOT NULL AUTO_INCREMENT,
`username` VARCHAR(45) NOT NULL,
`password` TEXT NOT NULL,
`algorithm` VARCHAR(45) NOT NULL,
PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `authority` (
`id` INT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
`user` INT NOT NULL,
PRIMARY KEY (`id`));

INSERT IGNORE INTO `user` (`id`, `username`, `password`, `algorithm`) VALUES ('1', 'john', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'BCRYPT');
INSERT IGNORE INTO `authority` (`id`, `name`, `user`) VALUES ('1', 'READ', '1');
INSERT IGNORE INTO `authority` (`id`, `name`, `user`) VALUES ('2', 'WRITE', '1');


