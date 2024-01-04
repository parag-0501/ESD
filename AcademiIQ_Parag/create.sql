-- Checking for dababase 
DROP DATABASE IF EXISTS academiq;
CREATE DATABASE IF NOT EXISTS academiq;
USE academiq;

-- Checking for all tables
DROP TABLE IF EXISTS user, token, students, bills, stdpay;

-- Creating table user
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255)
);

-- Creating table token
CREATE TABLE token (
    id INT AUTO_INCREMENT PRIMARY KEY,
    token VARCHAR(255) UNIQUE,
    tokenType VARCHAR(255),
    revoked BOOLEAN,
    expired BOOLEAN,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

-- Creating table students
CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    roll_no INT UNIQUE NOT NULL,
    user_id INT UNIQUE,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

-- Creating table bills
CREATE TABLE bills (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    dsc VARCHAR(100),
    amount DOUBLE NOT NULL,
    date DATE NOT NULL,
    deadline DATE,
    status VARCHAR(255) NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(id)
);

-- Creating table stdpay -> Student_Payment
CREATE TABLE stdpay (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    bill_id INT UNIQUE,
    date DATE NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (bill_id) REFERENCES bills(id)
);
