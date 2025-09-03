CREATE OR REPLACE PROCEDURE retrieve_file(
    IN file_id BIGINT,
    OUT file_name VARCHAR,
    OUT file_data BYTEA
)
LANGUAGE plpgsql
AS $$
BEGIN
    SELECT f.file_name, f.file_data INTO file_name, file_data
    FROM file_storage f
    WHERE f.id = file_id;
END;
$$;