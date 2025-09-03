CREATE TABLE file_storage (
        id SERIAL PRIMARY KEY,
        file_name VARCHAR(20),
        file_data BYTEA
    );