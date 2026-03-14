package ro.ulbs.proiectaresoftware.students;

public class Student {
    int numarMatricol;
    String prenume;
    String nume;
    String formațieDeStudiu;

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
    @Override
    public String toString() {
            return numarMatricol + "            " + nume + "    " + prenume + " " + formațieDeStudiu;
    }
}
