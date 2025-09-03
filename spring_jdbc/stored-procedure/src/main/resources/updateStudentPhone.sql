CREATE OR REPLACE PROCEDURE updateStudentPhone(
    p_student_id INT,
    p_phone_number VARCHAR(20)
)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE students
    SET phone_numbers = p_phone_number,
        updated_datetime = NOW()
    WHERE id = p_student_id;
END;
$$;