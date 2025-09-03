package alex.valker91;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.nio.file.Files;

@SpringBootApplication
public class FileSharingApplication  implements CommandLineRunner {

    @Autowired
    private FileService fileService;

    public static void main(String[] args) {
        SpringApplication.run(FileSharingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String fileName = "table.sql";
        ClassPathResource resource = new ClassPathResource(fileName);
        byte[] data = Files.readAllBytes(resource.getFile().toPath());

        fileService.saveFile(fileName, data);
        FileEntity fileEntity = fileService.getFile(1);
        System.out.println("Name: " + fileEntity.getFileName() + " , Id: " + fileEntity.getId());
    }
}
