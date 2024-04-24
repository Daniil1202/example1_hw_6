create table notes(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    text VARCHAR(50) NOT NULL,
    localDateTime DATE NOT NULL
);