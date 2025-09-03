package alex.valker91;

import alex.valker91.repository.student.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PerformanceTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testPerformanceComparison() {
        int id = 7;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            studentRepository.updateStudentPhone(id, "1234");
        }
        long storedProcedureTime = System.currentTimeMillis() - startTime;
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            jdbcTemplate.update("UPDATE students SET phone_numbers = '1234', updated_datetime = NOW() WHERE id = ?", id);
        }
        long directQueryTime = System.currentTimeMillis() - startTime;
        assertTrue(storedProcedureTime > directQueryTime);
    }
}
