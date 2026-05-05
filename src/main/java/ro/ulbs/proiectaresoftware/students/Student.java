package ro.ulbs.proiectaresoftware.students;

import java.util.Objects;

public class Student {
    private final int numarMatricol;
    private final String prenume;
    private final String nume;
    private final String formațieDeStudiu;
    private final float nota;

    public Student(int numarMatricol, String prenume, String nume, String formațieDeStudiu, float nota) {
        this.numarMatricol = numarMatricol;
        this.prenume = prenume;
        this.nume = nume;
        this.formațieDeStudiu = formațieDeStudiu;
        this.nota = nota;
    }

    public int getNumarMatricol() { return numarMatricol; }
    public String getPrenume() { return prenume; }
    public String getNume() { return nume; }
    public String getFormatieDeStudiu() { return formațieDeStudiu; }
    public float getNota() { return nota; }

    public Student mutaInFormatie(String nouaFormatie) {
        return new Student(this.numarMatricol, this.prenume, this.nume, nouaFormatie, this.nota);
    }

    public Student updateNota(float nouaNota) {
        return new Student(this.numarMatricol, this.prenume, this.nume, this.formațieDeStudiu, nouaNota);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return numarMatricol == student.numarMatricol;
    }



    @Override
    public int hashCode() {
        return Objects.hash(numarMatricol);
    }

    @Override
    public String toString() {
        return String.format("%d %s %s %s %.2f", numarMatricol, nume, prenume, formațieDeStudiu, nota);
    }


}