package ro.ulbs.proiectaresoftware.students;


import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(){

        Student s1 = new Student(112, "Ioan", "Popa", "TI21/1");
        Student s2 = new Student(112, "Maria", "Oprea", "TI21/1");
        Student s3 = new Student(120, "Alis", "Popa", "TI21/2");
        Student s4 = new Student(122, "Mihai", "Vecerdea", "TI22/1");
        Student s5 = new Student(122, "Eugen", "Uritescu", "TI22/2");
        Student s6 = new Student(120, "Alis", "Popa", "TI21/2");
        Student s7 = new Student(112, "Maria", "Popa", "TI21/1");
        System.out.println("Numar Matricol Prenume Nume Formatie Studiu");
        //System.out.println(s1);
        //System.out.println(s2);
        //System.out.println(s3);
        //System.out.println(s4);
        //System.out.println(s5);

        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
        students.forEach(System.out::println);

        System.out.println(Exista(s6,students));
        System.out.println(Exista(s7,students));

    }

public static Boolean Exista(Student student, List<Student> Students){
    if(Students.contains(student)){
        return true;
    }
    else{
        return false;
    }
}
}

