--1.1
DROP DATABASE IF EXISTS Shop;
CREATE DATABASE Shop;
USE Shop;

CREATE TABLE T_Articles (
    IdArticle int(4) PRIMARY KEY AUTO_INCREMENT,
    Description varchar(30) NOT NULL,
    Brand varchar(30) NOT NULL,
    UnitaryPrice float(8) NOT NULL DEFAULT 0
) ENGINE = InnoDB;

INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Souris', 'Logitoch', 65); 
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Clavier', 'Microhard', 49.5); 
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Système d''exploitation', 'Fenetres Vistouille', 100); 
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Tapis souris', 'Chapeau bleu', 5); 
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Clé USB 8 To', 'Syno', 65); 
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Laptop', 'PH', 1199); 
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('CD x 500', 'CETME', 250); 
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('DVD-R x 100', 'CETME', 99); 
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('DVD+R x 100', 'Logitech', 105); 
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Batterie Laptop', 'PH', 80); 
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Casque Audio', 'Syno', 105); 
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Webcam', 'Logitoch', 755); 

--1.2
SHOW tables;

--1.3
DESCRIBE T_Articles;

--1.4
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Chaise de bureau', 'Ikea', 60);

--1.5
UPDATE T_Articles
SET UnitaryPrice = 70
WHERE IdArticle = 13;


SELECT *
FROM T_Articles;

--1.6
DELETE
FROM T_Articles
WHERE IdArticle = 12;

--1.7
SELECT *
FROM T_Articles
WHERE UnitaryPrice > 100;

--1.8
SELECT *
FROM T_Articles
WHERE UnitaryPrice > 50 AND UnitaryPrice < 100;

--1.9
SELECT *
FROM T_Articles
ORDER BY UnitaryPrice;

--1.10
SELECT Description
FROM T_Articles;

--1.11
SELECT Description
FROM T_Articles
WHERE Brand = 'Logitoch'
ORDER BY UnitaryPrice;

--1.12
CREATE TABLE T_Categories (
    IdCategory int(4) PRIMARY KEY AUTO_INCREMENT,
    CatName varchar(30) NOT NULL
) ENGINE = InnoDB;

INSERT INTO T_Categories (IdCategory, CatName) VALUES (1, 'Materiel informatique'); 
INSERT INTO T_Categories (IdCategory, CatName) VALUES (2, 'Logiciel');
INSERT INTO T_Categories (IdCategory, CatName) VALUES (3, 'Autre');

ALTER TABLE T_Articles
ADD COLUMN IdCategory INT(4);

ALTER TABLE T_Articles
ADD FOREIGN KEY(IdCategory) REFERENCES T_Categories(IdCategory);

UPDATE T_Articles
SET IdCategory = 1
WHERE IdArticle = 1 OR IdArticle = 2 OR IdArticle = 4 OR IdArticle = 5 OR IdArticle = 6 OR IdArticle = 7;

UPDATE T_Articles
SET IdCategory = 2
WHERE IdArticle = 3;

UPDATE T_Articles
SET IdCategory = 3
WHERE IdCategory = NULL;

--1.13
SELECT *
FROM T_Articles JOIN T_Categories ON T_Articles.IdCategory = T_Categories.IdCategory
WHERE IdArticle > 10
ORDER BY UnitaryPrice;