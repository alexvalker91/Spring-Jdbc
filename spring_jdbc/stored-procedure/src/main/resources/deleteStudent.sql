CREATE OR REPLACE PROCEDURE deleteStudent(
    p_student_id INT
)
LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM students
    WHERE id = p_student_id;
END;
$$;