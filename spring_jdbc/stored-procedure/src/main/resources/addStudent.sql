CREATE OR REPLACE PROCEDURE addStudent(
    studentName VARCHAR(20),
    studentSurname VARCHAR(20),
    studentDateOfBirth DATE,
    studentPhoneNumbers VARCHAR(20),
    studentPrimarySkill VARCHAR(20)
)
AS $$
BEGIN
    INSERT INTO students (
        name, surname, date_of_birth, phone_numbers, primary_skill, created_datetime, updated_datetime
    ) VALUES (
        studentName, studentSurname, studentDateOfBirth, studentPhoneNumbers, studentPrimarySkill, NOW(), NOW()
    );
END;
$$ LANGUAGE plpgsql;