CREATE  TABLE `kadai4`.`contributions` (
 
 `id` INT NOT NULL AUTO_INCREMENT ,
 
 `title` VARCHAR(50) NOT NULL ,
 
 `text` TEXT NOT NULL ,
 
 `category` VARCHAR(10) NOT NULL ,
 
 `created` TIMESTAMP NULL ,
 
 `modified` TIMESTAMP NULL ,
  
 `users_id` INT(11) NOT NULL ;
PRIMARY KEY (`id`) );