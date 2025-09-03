package alex.valker91;

import org.springframework.stereotype.Service;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public void saveFile(String fileName, byte[] data) {
        fileRepository.saveFile(fileName, data);
    }

    public FileEntity getFile(int id) {
        return fileRepository.retrieveFile(id);
    }
}
