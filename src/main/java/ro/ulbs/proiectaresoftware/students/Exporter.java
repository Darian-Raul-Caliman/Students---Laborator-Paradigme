package ro.ulbs.proiectaresoftware.students;

import java.util.List;

public class Exporter {
    public void startExport(IStudentiExport strategyInstance, List<Student> students) {
        strategyInstance.doExport(students);
    }
}