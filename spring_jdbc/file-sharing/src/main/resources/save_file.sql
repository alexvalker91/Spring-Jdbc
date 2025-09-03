CREATE OR REPLACE PROCEDURE save_file(
    p_file_name VARCHAR,
    p_file_data BYTEA
)
LANGUAGE SQL
AS $$
    INSERT INTO file_storage (file_name, file_data)
    VALUES (p_file_name, p_file_data);
$$;