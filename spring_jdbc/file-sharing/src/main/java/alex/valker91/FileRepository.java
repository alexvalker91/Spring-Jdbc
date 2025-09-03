package alex.valker91;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.*;

@Repository
public class FileRepository {
    private final JdbcTemplate jdbcTemplate;

    public FileRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveFile(String fileName, byte[] fileData) {
        jdbcTemplate.execute((Connection connection) -> {
            try (CallableStatement callableStatement = connection.prepareCall("CALL save_file(?, ?)")) {
                callableStatement.setString(1, fileName);
                callableStatement.setBytes(2, fileData);
                callableStatement.execute();
                return null;
            }
        });
    }

    public FileEntity retrieveFile(int id) {
        return jdbcTemplate.execute((Connection connection) -> {
            try (CallableStatement callableStatement = connection.prepareCall("CALL retrieve_file(?, ?, ?)")) {
                callableStatement.setLong(1, id);
                callableStatement.registerOutParameter(2, Types.VARCHAR);
                callableStatement.registerOutParameter(3, Types.VARBINARY);
                callableStatement.execute();

                String fileName = callableStatement.getString(2);
                byte[] fileData = callableStatement.getBytes(3);

                return new FileEntity(id, fileName, fileData);
            }
        });
    }
}
