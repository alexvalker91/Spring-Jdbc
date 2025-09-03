CREATE OR REPLACE PROCEDURE readStudent(p_student_id INT)
LANGUAGE plpgsql
AS $$
BEGIN
    SELECT *  FROM students WHERE id = p_student_id;
END;
$$;