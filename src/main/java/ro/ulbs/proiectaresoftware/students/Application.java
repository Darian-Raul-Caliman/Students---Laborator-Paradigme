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
        Path outputFile = Paths.get("studenti_out.txt");
        Path outputFile2 = Paths.get("studenti_out_sorted.txt");
        Path note = Paths.get("C:\\Users\\raulc\\IdeaProjects\\Students\\src\\main\\java\\ro\\ulbs\\proiectaresoftware\\students\\note_anon.txt");

        List<Student> students = citireStudenti(inputFile);
        System.out.println("--- Studentii din fisier ---");

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
        noteStudenti(students, note);

        Map<String, Student> tineri = new HashMap<>();
        for (Student s : students) {
            tineri.put(s.getPrenume() + " " + s.getNume(), s);
        }
        float notaM = gasesteNota("Bianca", "Popescu", tineri);
        float notaN = gasesteNota("Ioan", "Popa", tineri);

        System.out.println("Nota Bianca Popescu: " + notaM);
        System.out.println("Nota Ioan Popa: " + notaN);

        System.out.println("\n--- Salvare studentilor bursieri ---");
        List<StudentBursier> bursieri = new ArrayList<>();
        bursieri.add(new StudentBursier(1025, "Andrei", "Popa", "ISM141/2", 8.70, 725.50));
        bursieri.add(new StudentBursier(1024, "Ioan", "Mihalcea", "ISM141/1", 9.80, 801.10));
        bursieri.add(new StudentBursier(1026, "Anamaria", "Prodan", "TI131/1", 8.90, 745.50));
        bursieri.add(new StudentBursier(1029, "Bianca", "Popescu", "TI131/1", 9.10, 780.80));

        Path caleBursieriOut = Paths.get("bursieri_out.txt");

        scriereStudenti(bursieri, caleBursieriOut);
        System.out.println("Studentii bursieri au fost salvati in: " + caleBursieriOut.getFileName());
    }

    public static float gasesteNota(String prenume, String nume, Map<String, Student> mapa) {
        String cheie = prenume + " " + nume;
        Student studentGasit = mapa.get(cheie);

        if (studentGasit != null) {
            return studentGasit.nota;
        }
        return 0.0f;
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

    public static void noteStudenti(List<Student> students, Path path) {
        Map<Integer, Student> mapaStudenti = new HashMap<>();
        for (Student s : students) {
            mapaStudenti.put(s.getNumarMatricol(), s);
        }

        try {
            List<String> linii = Files.readAllLines(path);

            for (String linie : linii) {
                if (linie.trim().isEmpty()) {
                    continue;
                }

                String[] date = linie.split(",");

                if (date.length == 2) {
                    try {
                        int numarMatricol = Integer.parseInt(date[0].trim());
                        float nota = Float.parseFloat(date[1].trim());

                        Student studentGasit = mapaStudenti.get(numarMatricol);
                        if (studentGasit != null) {
                            studentGasit.setNota(nota);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Eroare conversie număr pe linia: [" + linie + "]");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Nu am putut găsi/citi fișierul la calea: " + path.toAbsolutePath());
        }
    }

    public static List<Student> citireStudenti(Path path) {
        List<Student> listaStudenti = new LinkedList<>();
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