package ro.ulbs.proiectaresoftware.students;
import ro.ulbs.proiectaresoftware.students.TimeExecutionDecorator;

import java.util.Arrays;
import java.util.List;

public class AplicatieCuDecorator {
    public static void main(String[] args) {

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

        List<IStudentiExport> strategies = Arrays.asList(
                new StudentiInConsola(),
                new StudentiInFisierText("studentiStrategyText.txt"),
                new StudentiInFisierXlsx("studentiStrategyExcel.xlsx")
        );

        for (IStudentiExport strategy : strategies) {
            TimeExecutionDecorator decorator = new TimeExecutionDecorator(strategy, studentiCuNote);


            long time = decorator.executionTime();

            System.out.println("Execution time: " + time + " ms for " + strategy.getClass().getSimpleName());
            System.out.println("----------------");
        }
    }
}