package alex.valker91;

import alex.valker91.model.Procedure;
import alex.valker91.repository.procedure.ProcedureRepository;
import alex.valker91.repository.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringstudensApplication implements CommandLineRunner {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ProcedureRepository procedureRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringstudensApplication.class, args);
    }

    @Override
    public void run(String... args) {
        studentProcedure();
    }

    private void studentProcedure() {
        System.out.println("studentProcedure size(): "+procedureRepository.getAllProcedures().size());
        procedureRepository.addProcedure(Procedure.ADD_STUDENT);
        procedureRepository.addProcedure(Procedure.DELETE_STUDENTS);
        procedureRepository.addProcedure(Procedure.UPDATE_STUDENT_PHONE);
        procedureRepository.addProcedure(Procedure.READ_STUDENT);
        System.out.println("studentProcedure size(): "+procedureRepository.getAllProcedures().size());
        studentRepository.updateStudentPhone(1, "+375291234567");
    }
}
