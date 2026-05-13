package ro.ulbs.proiectaresoftware.students;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        String excelPath = "laborator8_students.xls";
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

        List<Student> listaInitiala = new ArrayList<>();
        listaInitiala.add(new Student(1, "Andrei", "Popa", "TI", 7.0f));
        listaInitiala.add(new Student(2, "Bianca", "Vecerdea", "TI", 7.0f));
        listaInitiala.add(new Student(3, "Anamaria", "Prodan", "TI", 6.0f));


        exportaInExcel(listaInitiala, excelPath);


        List<Student> listaImportata = importaDinExcel(excelPath);
        System.out.println("\n--- Studenți importați din Excel ---");
        listaImportata.forEach(System.out::println);



        List<Student> studentiCuNote = Arrays.asList(
                new Student(1025, "Andrei", "Popa", "ISM141/2", 8.70f),
                new Student(1024, "Ioan", "Mihalcea", "ISM141/1", 10.0f),
                new Student(1026, "Anamaria", "Prodan", "TI131/1", 8.90f),
                new Student(1029, "Bianca", "Popescu", "TI131/1", 10.0f),
                new Student(1029, "Maria", "Pana", "TI131/2", 4.10f),
                new Student(1029, "Gabriela", "Mohanu", "TI131/2", 7.33f),
                new Student(1029, "Marius", "Nasta", "TI131/2", 3.20f),
                new Student(1029, "Marius", "Nasta", "TI131/1", 5.12f),
                new Student(1029, "Andrei", "Dobrescu", "TI131/2", 2.22f)
        );

        System.out.println("\n--- a) Studenții cu nota 10 ---");
        studentiCuNote.stream()
                .filter(s -> s.getNota() == 10.0f)
                .forEach(System.out::println);

        System.out.println("\n--- b) Studenții cu nota sub 5 ---");
        studentiCuNote.stream()
                .filter(s -> s.getNota() < 5.0f)
                .forEach(s -> s.getNota());

        System.out.println("\n--- c) Studenți cu nota < 4 modificată în 4 ---");
        List<Student> listaTransformata = studentiCuNote.stream()
                .map(s -> s.getNota() < 4.0f ? s.updateNota(4.0f) : s)
                .collect(Collectors.toList());
        listaTransformata.forEach(System.out::println);

        float sumaNote = studentiCuNote.stream()
                .map(Student::getNota)
                .reduce(0.0f, Float::sum);
        System.out.println("\n--- d) Suma notelor: " + sumaNote + " ---");

        float media = sumaNote / studentiCuNote.size();
        System.out.println("--- e) Media notelor: " + media + " ---");
    }


    public static void exportaInExcel(List<Student> studenti, String numeFisier) {
        try (Workbook workbook = new HSSFWorkbook();
             FileOutputStream fileOut = new FileOutputStream(numeFisier)) {

            Sheet sheet = workbook.createSheet("Studenti");
            String[] header = {"Nr Crt", "Nume", "Prenume", "Formatie", "Nota"};

            // Creare Header
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < header.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(header[i]);
            }

            // Adăugare date
            int rowIdx = 1;
            for (Student s : studenti) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(s.getNumarMatricol());
                row.createCell(1).setCellValue(s.getNume());
                row.createCell(2).setCellValue(s.getPrenume());
                row.createCell(3).setCellValue(s.getFormatieDeStudiu());
                row.createCell(4).setCellValue(s.getNota());
            }

            workbook.write(fileOut);
            System.out.println("Exportul în " + numeFisier + " a fost finalizat cu succes.");
        } catch (IOException e) {
            System.err.println("Eroare la export: " + e.getMessage());
        }
    }

    public static List<Student> importaDinExcel(String numeFisier) {
        List<Student> studenti = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(numeFisier);
             Workbook workbook = new HSSFWorkbook(fileIn)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) rowIterator.next(); // Sărim peste header

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                int nrMatricol = (int) row.getCell(0).getNumericCellValue();
                String nume = row.getCell(1).getStringCellValue();
                String prenume = row.getCell(2).getStringCellValue();
                String formatie = row.getCell(3).getStringCellValue();
                float nota = (float) row.getCell(4).getNumericCellValue();

                studenti.add(new Student(nrMatricol, prenume, nume, formatie, nota));
            }
        } catch (IOException e) {
            System.err.println("Eroare la import: " + e.getMessage());
        }
        return studenti;
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