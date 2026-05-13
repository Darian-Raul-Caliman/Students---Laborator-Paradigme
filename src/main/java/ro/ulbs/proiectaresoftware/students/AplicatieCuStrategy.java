package ro.ulbs.proiectaresoftware.students;

import java.util.Arrays;
import java.util.List;

public class AplicatieCuStrategy {
    public static void main(String[] args) {
        List<Student> studenti = Arrays.asList(
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

        Exporter exporter = new Exporter();
        Importer importer = new Importer();

        // a) Export Consolă
        System.out.println("--- Export în Consolă ---");
        IStudentiExport strategyConsole = new StudentiInConsola();
        exporter.startExport(strategyConsole, studenti);

        // b) Export Text
        String fisierText = "studentiStrategyText.txt";
        IStudentiExport strategyFisierText = new StudentiInFisierText(fisierText);
        exporter.startExport(strategyFisierText, studenti);

        // c) Export Excel
        String fisierExcel = "studentiStrategyExcel.xls";
        IStudentiExport strategyFisierExcel = new StudentiInFisierXlsx(fisierExcel);
        exporter.startExport(strategyFisierExcel, studenti);

        // d) Import Text
        System.out.println("\n--- Import din Fișier Text ---");
        IStudentiImport strategyImportText = new StudentiDinFisierText(fisierText);
        List<Student> studentiDinText = importer.startImport(strategyImportText);
        studentiDinText.forEach(System.out::println);

        // e) Import Excel
        System.out.println("\n--- Import din Fișier Excel ---");
        IStudentiImport strategyImportExcel = new StudentiDinFisierXlsx(fisierExcel);
        List<Student> studentiDinExcel = importer.startImport(strategyImportExcel);
        studentiDinExcel.forEach(System.out::println);
    }
}