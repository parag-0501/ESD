-- Add refence in token and associate with user
ALTER TABLE token
ADD FOREIGN KEY (user_id) REFERENCES user(id);

-- Add refence in student and associate with user
ALTER TABLE students
ADD FOREIGN KEY (user_id) REFERENCES user(id);

-- Add refence in bills and associate with student
ALTER TABLE bills
ADD FOREIGN KEY (student_id) REFERENCES students(id);

-- Add refence in stdpay and associate with students and bills
ALTER TABLE stdpay
ADD FOREIGN KEY (student_id) REFERENCES students(id),
ADD FOREIGN KEY (bill_id) REFERENCES bills(id);
