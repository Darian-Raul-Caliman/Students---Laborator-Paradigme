package ro.ulbs.proiectaresoftware.students;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class StudentiDinFisierText implements IStudentiImport {
    private final String fileName;

    public StudentiDinFisierText(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Student> doImport() {
        List<Student> listaStudenti = new LinkedList<>();
        try {
            List<String> linii = Files.readAllLines(Paths.get(fileName));
            for (String linie : linii) {
                String[] date = linie.split(" ");
                if (date.length >= 5) {
                    listaStudenti.add(new Student(
                            Integer.parseInt(date[0].trim()),
                            date[2].trim(), // prenume
                            date[1].trim(), // nume
                            date[3].trim(), // formatie
                            Float.parseFloat(date[4].trim().replace(",", ".")) // nota
                    ));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Eroare la citire text: " + e.getMessage());
        }
        return listaStudenti;
    }
}