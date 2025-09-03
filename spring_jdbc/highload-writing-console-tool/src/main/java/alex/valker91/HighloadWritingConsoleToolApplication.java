package alex.valker91;

import alex.valker91.model.Table;
import alex.valker91.model.Type;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class HighloadWritingConsoleToolApplication implements CommandLineRunner {

    private final TableService tableService;
    private final int threadPoolSize = 10;
    private static final Random random = new Random();

    public HighloadWritingConsoleToolApplication(TableService tableService) {
        this.tableService = tableService;
    }

    public static void main(String[] args) {
        SpringApplication.run(HighloadWritingConsoleToolApplication.class, args);
    }

    private static List<Table> randomTableGeneration() {
        int randomTableNumber = random.nextInt(10) + 1;
        List<Table> tables = new ArrayList<>();
        for (int i = 0; i < randomTableNumber; i ++) {
            String tableName = "Table_" + i;
            int randomColumnNumber = random.nextInt(10) + 1;
            Map<String, Type> columNamesAndType = new HashMap<>();
            for (int j = 0; j < randomColumnNumber; j ++) {
                String columnName = "Column_" + j;
                Type columnType = Type.getRandomType();
                columNamesAndType.put(columnName, columnType);
            }
            Table table = new Table(tableName, columNamesAndType);
            tables.add(table);
        }
        return tables;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Table> tables = randomTableGeneration();

        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
        tables.forEach(table -> executor.submit(() -> {
            try {
                tableService.createTable(table);
                tableService.populateTable(table, random.nextInt(10) + 1);
            } catch (Exception e) {
                System.out.println("Error processing table");
            }
        }));

        executor.shutdown();
    }
}
