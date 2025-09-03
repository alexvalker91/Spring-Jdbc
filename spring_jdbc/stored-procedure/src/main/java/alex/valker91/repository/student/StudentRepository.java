package alex.valker91.repository.student;

import alex.valker91.model.Student;

public interface StudentRepository {

    Student addStudent(Student student);

    void updateStudentPhone(int id, String phone);
}
