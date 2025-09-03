package alex.valker91;

import alex.valker91.model.Procedure;
import alex.valker91.repository.procedure.ProcedureRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProcedureTest {

    @Autowired
    private ProcedureRepository procedureRepository;

    @Test
    void addProcedureTest() {
        procedureRepository.addProcedure(Procedure.ADD_STUDENT);
        boolean containsAddStudent = procedureRepository.getAllProcedures().stream()
                .anyMatch(map -> Procedure.ADD_STUDENT.getName().toLowerCase().equals(map.get("procedure_name")));
        assertTrue(containsAddStudent);
        procedureRepository.deleteProcedure(Procedure.ADD_STUDENT);
    }

    @Test
    void deleteProcedure() {
        procedureRepository.addProcedure(Procedure.ADD_STUDENT);
        boolean containsAddStudent = procedureRepository.getAllProcedures().stream()
                .anyMatch(map -> Procedure.ADD_STUDENT.getName().toLowerCase().equals(map.get("procedure_name")));
        assertTrue(containsAddStudent);
        procedureRepository.deleteProcedure(Procedure.ADD_STUDENT);
        containsAddStudent = procedureRepository.getAllProcedures().stream()
                .anyMatch(map -> Procedure.ADD_STUDENT.getName().toLowerCase().equals(map.get("procedure_name")));
        assertFalse(containsAddStudent);
    }

    @Test
    void getAllProcedures() {
        procedureRepository.addProcedure(Procedure.ADD_STUDENT);
        boolean isContains = procedureRepository
                .getAllProcedures()
                .stream()
                .anyMatch(map -> Procedure.ADD_STUDENT.getName().toLowerCase().equals(map.get("procedure_name")));
        assertTrue(isContains);
        procedureRepository.deleteProcedure(Procedure.ADD_STUDENT);
    }
}
