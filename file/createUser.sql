CREATE TABLE T_Users (
IdUser int(4) PRIMARY KEY AUTO_INCREMENT,
Login varchar(20) NOT NULL,
Password varchar(20) NOT NULL
) ENGINE = InnoDB;

INSERT INTO T_Users (Login, Password) VALUES ('root', 'fms2022'); 