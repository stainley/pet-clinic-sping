DROP TABLE IF EXISTS CLIENT;

CREATE TABLE CLIENT (
                               id INT AUTO_INCREMENT  PRIMARY KEY,
                               name VARCHAR(250) NOT NULL,
                               status VARCHAR(250) NOT NULL
);
