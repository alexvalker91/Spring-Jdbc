package alex.valker91.repository.procedure;

import alex.valker91.model.Procedure;

import java.util.List;
import java.util.Map;

public interface ProcedureRepository {

    void addProcedure(Procedure procedure);

    void deleteProcedure(Procedure procedure);

    List<Map<String, Object>> getAllProcedures();
}
