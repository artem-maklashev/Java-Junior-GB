CREATE DATABASE IF NOT EXISTS SchoolDB;
CREATE TABLE IF NOT EXISTS  SchoolDB.courses (
                                  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
                                  title VARCHAR(255),
                                  duration INT
);