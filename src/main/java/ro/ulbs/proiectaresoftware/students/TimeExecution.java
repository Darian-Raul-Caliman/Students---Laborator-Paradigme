package ro.ulbs.proiectaresoftware.students;

import java.util.List;

public abstract class TimeExecution implements ITimeExecution {
    protected IStudentiExport exporter;

    public TimeExecution(IStudentiExport exporter) {
        this.exporter = exporter;
    }

    @Override
    public Long executionTime(List<Student> studenti) {

        long startTime = System.currentTimeMillis();

        exporter.doExport(studenti);

        long endTime = System.currentTimeMillis();


        return endTime - startTime;
    }
}
