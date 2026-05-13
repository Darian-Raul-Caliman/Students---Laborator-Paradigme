package ro.ulbs.proiectaresoftware.students;

import java.util.List;

public class Importer {
    public List<Student> startImport(IStudentiImport strategyInstance) {
        return strategyInstance.doImport();
    }
}