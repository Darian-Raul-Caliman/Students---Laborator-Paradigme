package ro.ulbs.proiectaresoftware.students;


import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Application {
    public static void main(String[] args) {

        Path inputFile = Paths.get("C:\\Users\\raulc\\IdeaProjects\\Students\\src\\main\\java\\ro\\ulbs\\proiectaresoftware\\students\\studenti_in.txt");
        Path outputFile = Paths.get("C:\\Users\\raulc\\IdeaProjects\\Students\\src\\main\\java\\ro\\ulbs\\proiectaresoftware\\students\\studenti_out.txt");
        Path outputFile2 = Paths.get("C:\\Users\\raulc\\IdeaProjects\\Students\\src\\main\\java\\ro\\ulbs\\proiectaresoftware\\students\\studenti_out_sorted.txt");
        List<Student> students = citireStudenti(inputFile);
        System.out.println("--- Studentii din fișier ---");
        for (Student s : students) {
            System.out.println(s);
        }
        students.sort(Comparator.comparing(Student::getNume));
        scriereStudenti(students, outputFile);

        students.sort(
                Comparator.comparing(Student::getFormațieDeStudiu)
                        .thenComparing(Student::getNume)
        );
        scriereStudenti(students, outputFile2);
    }

    private static void scriereStudenti(List<Student> students, Path outputFile) {
        try (BufferedWriter writer = Files.newBufferedWriter(outputFile)) {
            for (Student s : students) {
                writer.write(s.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Eroare la scriere: " + e.getMessage());
        }
    }

    public static List<Student> citireStudenti(Path path) {
        List<Student> listaStudenti = new ArrayList<>();
        try {
            List<String> linii = Files.readAllLines(path);
            for (String linie : linii) {
                String[] date = linie.split(",");

                if (date.length == 4) {
                    int numarMatricol = Integer.parseInt(date[0].trim());
                    String prenume = date[1].trim();
                    String nume = date[2].trim();
                    String formatie = date[3].trim();

                    listaStudenti.add(new Student(numarMatricol, prenume, nume, formatie));
                }
            }
        } catch (IOException e) {
            System.out.println("Eroare la citire: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Eroare la conversie : " + e.getMessage());
        }

        return listaStudenti;
    }

    public static boolean Exista(Student student, Set<Student> students) {
        return students.contains(student);
    }
}

