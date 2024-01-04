-- Insert users
INSERT INTO user (firstname, lastname, email, password, role) VALUES ('Parag', 'Sharma', 'parag@gmail.com', '1234', 'STUDENT');
INSERT INTO user (firstname, lastname, email, password, role) VALUES ('Jacob', 'Mathew', 'jacob@gmail.com', '1234', 'STUDENT');

-- Insert tokens
INSERT INTO token (token, tokenType, revoked, expired, user_id) VALUES ('token_value_1', 'BEARER', false, false, 1);
INSERT INTO token (token, tokenType, revoked, expired, user_id) VALUES ('token_value_2', 'BEARER', false, false, 2);

-- Insert students and associate with users
INSERT INTO students (roll_no, user_id) VALUES (101, 1);
INSERT INTO students (roll_no, user_id) VALUES (102, 2);

-- Insert bills for students
INSERT INTO bills (student_id, dsc, amount, date, deadline, status) VALUES (1, '1st bill', 500.99, '2023-11-01', '2023-12-01', 'Done');
INSERT INTO bills (student_id, dsc, amount, date, deadline, status) VALUES (1, '2nd bill', 750.99, '2023-11-01', '2023-12-01', 'Pending');
INSERT INTO bills (student_id, dsc, amount, date, deadline, status) VALUES (1, '3rd bill', 850.99, '2023-11-01', '2023-12-01', 'Pending');
INSERT INTO bills (student_id, dsc, amount, date, deadline, status) VALUES (1, '4th bill', 950.99, '2023-11-01', '2023-12-01', 'Pending');
INSERT INTO bills (student_id, dsc, amount, date, deadline, status) VALUES (1, '5th bill', 150.99, '2023-11-01', '2023-12-01', 'Pending');

INSERT INTO bills (student_id, dsc, amount, date, deadline, status) VALUES (2, '6th bill', 500.99, '2023-11-01', '2023-12-01', 'Done');
INSERT INTO bills (student_id, dsc, amount, date, deadline, status) VALUES (2, '7th bill', 750.99, '2023-11-01', '2023-12-01', 'Pending');
INSERT INTO bills (student_id, dsc, amount, date, deadline, status) VALUES (2, '8th bill', 850.99, '2023-11-01', '2023-12-01', 'Pending');
INSERT INTO bills (student_id, dsc, amount, date, deadline, status) VALUES (2, '9th bill', 950.99, '2023-11-01', '2023-12-01', 'Pending');
INSERT INTO bills (student_id, dsc, amount, date, deadline, status) VALUES (2, '10th bill', 150.99, '2023-11-01', '2023-12-01', 'Pending');

-- Insert stdpay for bills and students
INSERT INTO stdpay (student_id, bill_id, date) VALUES (1, 1, '2023-11-15');
INSERT INTO stdpay (student_id, bill_id, date) VALUES (2, 6, '2023-11-15');
