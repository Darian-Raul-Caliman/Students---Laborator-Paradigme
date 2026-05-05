package ro.ulbs.proiectaresoftware.students;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Application {
    public static void main(String[] args) {
        Path inputFile = Paths.get("src/main/java/ro/ulbs/proiectaresoftware/students/studenti_in.txt");
        List<Student> students = citireStudenti(inputFile);

        int n = students.size();
        int mijloc = (n + 1) / 2;

        List<Student> formatia1 = new ArrayList<>();
        List<Student> formatia2 = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i < mijloc) {
                formatia1.add(students.get(i).mutaInFormatie("FORMATIE_A"));
            } else {
                formatia2.add(students.get(i).mutaInFormatie("FORMATIE_B"));
            }
        }

        System.out.println("--- NOUA LISTA: FORMATIA A ---");
        formatia1.forEach(System.out::println);
        System.out.println("\n--- NOUA LISTA: FORMATIA B ---");
        formatia2.forEach(System.out::println);
    }

    public static List<Student> citireStudenti(Path path) {
        List<Student> listaStudenti = new LinkedList<>();
        try {
            List<String> linii = Files.readAllLines(path);
            for (String linie : linii) {
                String[] date = linie.split(",");
                if (date.length == 4) {
                    listaStudenti.add(new Student(
                            Integer.parseInt(date[0].trim()),
                            date[1].trim(),
                            date[2].trim(),
                            date[3].trim(),
                            0.0f
                    ));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Eroare: " + e.getMessage());
        }
        return listaStudenti;
    }

    public static void scriereStudenti(List<? extends Student> students, Path outputFile) {
        try (BufferedWriter writer = Files.newBufferedWriter(outputFile)) {
            for (Student s : students) {
                writer.write(s.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Eroare la scriere: " + e.getMessage());
        }
    }
}