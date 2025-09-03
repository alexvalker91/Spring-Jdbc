package alex.valker91.repository.procedure;

import alex.valker91.model.Procedure;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Repository
public class ProcedureRepositoryImpl implements ProcedureRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProcedureRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addProcedure(Procedure procedure) {
        try {
            ClassPathResource resource = new ClassPathResource(procedure.getPath());
            String sqlScript = FileCopyUtils.copyToString(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)
            );
            jdbcTemplate.execute(sqlScript);
            System.out.println("SQL script executed successfully: " + procedure.getPath());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read SQL script: " + procedure.getPath(), e);
        }
    }

    @Override
    public void deleteProcedure(Procedure procedure) {
        try {
            String sql = "DROP PROCEDURE IF EXISTS " + procedure.getName();
            jdbcTemplate.execute(sql);
            System.out.println("Procedure dropped successfully: " + procedure.getName());
        } catch (Exception e) {
            throw new RuntimeException("Failed to drop procedure: " + procedure.getName(), e);
        }
    }

    @Override
    public List<Map<String, Object>> getAllProcedures() {
        String sql =
                "SELECT " +
                        "    p.proname as procedure_name, " +
                        "    pg_get_function_arguments(p.oid) as parameters, " +
                        "    pg_get_function_result(p.oid) as return_type, " +
                        "    l.lanname as language, " +
                        "    p.prosrc as source_code, " +
                        "    obj_description(p.oid, 'pg_proc') as description " +
                        "FROM pg_proc p " +
                        "LEFT JOIN pg_language l ON p.prolang = l.oid " +
                        "LEFT JOIN pg_namespace n ON p.pronamespace = n.oid " +
                        "WHERE n.nspname NOT IN ('pg_catalog', 'information_schema') " +
                        "AND p.prokind = 'p' " +
                        "ORDER BY p.proname";

        return jdbcTemplate.queryForList(sql);
    }
}
