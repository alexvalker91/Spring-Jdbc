package alex.valker91;

import alex.valker91.model.Table;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TableService {
    private final JdbcTemplate jdbcTemplate;

    public TableService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTable(Table table) {
        String sql = generateCreateTableSQL(table);
        jdbcTemplate.execute(sql);
    }

    private String generateCreateTableSQL(Table table) {
        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append(table.getName()).append(" (");
        table.getColumNamesAndType().forEach((name, type) ->
                sql.append(name).append(" ").append(type.getName()).append(", ")
        );
        sql.setLength(sql.length() - 2);
        sql.append(")");
        return sql.toString();
    }

    @Transactional
    public void populateTable(Table table, int rows) {
        String sql = generateInsertSQL(table);
        List<Object[]> batchArgs = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            Object[] values = generateRandomData(table);
            batchArgs.add(values);
        }

        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    private String generateInsertSQL(Table table) {
        String columns = String.join(", ", table.getColumNamesAndType().keySet());
        String placeholders = String.join(", ",
                Collections.nCopies(table.getColumNamesAndType().size(), "?"));
        return String.format("INSERT INTO %s (%s) VALUES (%s)",
                table.getName(), columns, placeholders);
    }

    private Object[] generateRandomData(Table table) {
        Random random = new Random();
        return table.getColumNamesAndType().values().stream()
                .map(type -> {
                    switch (type) {
                        case INTEGER: return random.nextInt();
                        case VARCHAR: return UUID.randomUUID().toString();
                        case BOOLEAN: return random.nextBoolean();
                        case TIMESTAMP: return new Date();
                        default: throw new IllegalArgumentException("Unknown type");
                    }
                }).toArray();
    }
}
