package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Student {
    String nume;
    int nota;
    List<Integer> preferinte;
    Camera camera;

    public Student(String nume, int nota, List<Integer> preferinte) {
        this.nume = nume;
        this.nota = nota;
        this.preferinte = preferinte;
        this.camera = null;
    }
}

class Camera {
    int numar;
    List<Student> locatari;

    public Camera(int numar) {
        this.numar = numar;
        this.locatari = new ArrayList<>();
    }
}

public class Main {
    public static void atribuieCamere(List<Student> studenti, List<Camera> camere) {
        // Sortez studentii dupa nota in ordine descrescatoare
        Collections.sort(studenti, (s1, s2) -> Integer.compare(s2.nota, s1.nota));

        for (Student student : studenti) {
            // Atribui studentului cu cea mai mare nota prima sa opțiune care nu este inca ocupata
            for (int preferinta : student.preferinte) {
                Camera camera = camere.get(preferinta - 1);
                if (camera.locatari.size() < 2) {
                    camera.locatari.add(student);
                    student.camera = camera;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Student> studenti = new ArrayList<>();
        studenti.add(new Student("Student1", 90, List.of(1, 2, 3)));
        studenti.add(new Student("Student2", 85, List.of(2, 1, 3)));
        studenti.add(new Student("Student3", 86, List.of(1, 2, 3)));
        studenti.add(new Student("Student4", 87, List.of(1, 4, 3)));
        studenti.add(new Student("Student5", 93, List.of(4, 2, 5)));
        studenti.add(new Student("Student6", 88, List.of(2, 5, 3)));
        studenti.add(new Student("Student7", 86, List.of(1, 2, 3, 5)));
        studenti.add(new Student("Student8", 87, List.of(1, 4, 3)));
        studenti.add(new Student("Student9", 91, List.of(4, 5, 3)));
        studenti.add(new Student("Student10", 75, List.of(5, 1, 3)));
        studenti.add(new Student("Student11", 86, List.of(4, 2, 3)));
        studenti.add(new Student("Student12", 87, List.of(1, 2, 4)));

        List<Camera> camere = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            camere.add(new Camera(i));
        }

        atribuieCamere(studenti, camere);

        for (Camera camera : camere) {
            System.out.print("Camera " + camera.numar + ": [");
            for (Student student : camera.locatari) {
                System.out.print(student.nume + " ");
            }
            System.out.println("]");
        }
    }
}
