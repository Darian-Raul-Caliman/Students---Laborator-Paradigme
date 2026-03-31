package ro.ulbs.proiectaresoftware.students;

import java.util.Objects;

public class Student {
    int numarMatricol;
    String prenume;
    String nume;
    String formațieDeStudiu;
    float nota;

    public Student(int numarMatricol, String prenume, String nume, String formațieDeStudiu) {
        this.numarMatricol = numarMatricol;
        this.prenume = prenume;
        this.nume = nume;
        this.formațieDeStudiu = formațieDeStudiu;
    }

    public int getNumarMatricol() {
        return numarMatricol;
    }

    public void setNumarMatricol(int numarMatricol) {
        this.numarMatricol = numarMatricol;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getFormațieDeStudiu() {
        return formațieDeStudiu;
    }

    public void setFormațieDeStudiu(String formațieDeStudiu) {
        this.formațieDeStudiu = formațieDeStudiu;
    }

    public void setNota(float nota) {
        this.nota = nota;
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
        return numarMatricol + "            " + nume + "    " + prenume + " " + formațieDeStudiu + " " + nota;
    }
}