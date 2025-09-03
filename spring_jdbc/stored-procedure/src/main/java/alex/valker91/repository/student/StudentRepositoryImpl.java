package alex.valker91.repository.student;

import alex.valker91.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public StudentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Student addStudent(Student student) {
        jdbcTemplate.update("CALL addstudent(?, ?, ?, ?, ?)",
                student.getName(),
                student.getSurname(),
                new Date(),
                student.getPhone_numbers(),
                student.getPrimary_skill());
        return student;
    }

    @Override
    public void updateStudentPhone(int id, String phone) {
        jdbcTemplate.update("CALL updatestudentphone(?, ?)",
                id,
                phone);
    }
}
