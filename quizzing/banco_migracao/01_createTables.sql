START TRANSACTiON; 

CREATE TABLE `teacher` (  
	`id` int NOT NULL AUTO_INCREMENT,  
	`name` varchar(20) NOT NULL, 
    `instituicao` varchar(20) NOT NULL,
    `login` varchar(20) NOT NULL,
    `senha` varchar(20) NOT NULL,
    PRIMARY KEY (`id`)  
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;  
       
CREATE TABLE `question` (  
	`id` int NOT NULL AUTO_INCREMENT,  
	`question` varchar(200) NOT NULL,
    `type` varchar(200) NOT NULL,
    `idTeacher` varchar(200) NOT NULL,
	PRIMARY KEY (`id`),
    CONSTRAINT `fk_teacher_question` FOREIGN KEY 
	(`idTeacher`) REFERENCES `teacher` (`id`) ON DELETE CASCADE 
    ON UPDATE CASCADE 
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;  

CREATE TABLE `options` (  
	`id` int NOT NULL AUTO_INCREMENT,  
	`description` varchar(200) NOT NULL,
    `correct` boolean NOT NULL,
    `idQuestion` varchar(200) NOT NULL,
	PRIMARY KEY (`id`),
    CONSTRAINT `fk_question_option` FOREIGN KEY 
	(`idQuestion`) REFERENCES `question` (`id`) ON DELETE CASCADE 
    ON UPDATE CASCADE 
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;  


CREATE TABLE `migracoes` (  
	`id` int NOT NULL AUTO_INCREMENT, 
   `description` varchar(200) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8; 

COMMIT;

START TRANSACTiON; 

INSERT INTO `migracoes`(description) VALUES (1);

COMMIT;