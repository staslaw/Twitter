package pl.coderslab.app;

import pl.coderslab.entity.Student;

public class Main {

    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(student);

        student.setDescription("nowy");
        System.out.println(student);


    }
}
