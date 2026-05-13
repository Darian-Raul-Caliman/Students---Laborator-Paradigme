package ro.ulbs.proiectaresoftware.students;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StudentiInFisierText implements IStudentiExport {
    private final String fileName;

    public StudentiInFisierText(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void doExport(List<Student> studenti) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
            for (Student s : studenti) {
                writer.write(s.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Eroare la scriere text: " + e.getMessage());
        }
    }
}