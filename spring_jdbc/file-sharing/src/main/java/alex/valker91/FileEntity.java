package alex.valker91;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("file_storage")
public class FileEntity {
    @Id
    private int id;
    private String fileName;
    private byte[] fileData;

    public FileEntity(int id, String fileName, byte[] fileData) {
        this.id = id;
        this.fileName = fileName;
        this.fileData = fileData;
    }

    public int getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getFileData() {
        return fileData;
    }
}
