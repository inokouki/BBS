CREATE  TABLE `kadai4`.`users` (
 
 `id` INT NOT NULL AUTO_INCREMENT ,
 
 `login_id` VARCHAR(20) NOT NULL ,
 
 `password` VARCHAR(255) NOT NULL ,
 
 `name` VARCHAR(10) NOT NULL ,
 
 `branch_id` INT(11) NOT NULL ,
 
 `department_id` INT(11) NOT NULL ,
 
 `available` TINYINT NOT NULL ,
  
 `created` TIMESTAMP ,
 `modified` TIMESTAMP ,
PRIMARY KEY (`id`) ,
  
UNIQUE INDEX `login_id_UNIQUE` (`login_id` ASC) ;