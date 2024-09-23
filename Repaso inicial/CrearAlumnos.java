/**
 * Implementar una clase Alumno con el nombre, apellidos, edad y curso. Debéis incorporar el constructor,
 * getters/setters, toString. Implementar una lista dinámica en el main, que permita leer un número de objetos pedidos
 * desde teclado. Después, deberéis sacar por pantalla toda la lista de Alumnos.
 */

import java.util.Scanner;
import java.util.ArrayList;

class Alumno {
    // Atributos
    private String nombre;
    private String apellidos;
    private int edad;
    private int curso;

    // Constructor
    public Alumno(String nombre, String apellidos, int edad, int curso) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.curso = curso;
    }

    // Getters y setters
    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getApellidos(){
        return apellidos;
    }

    public void setApellidos(){
        this.apellidos = apellidos;
    }

    public int getEdad(){
        return edad;
    }

    public void setEdad ( int edad){
        this.edad = edad;
    }

    public int getCurso () {
        return curso;
    }

    public void setCurso ( int curso){
        this.curso = curso;
    }

    // toString
    @java.lang.Override
    public java.lang.String toString() {
        return "Alumno{" +
                "Nombre='" + nombre +
                ", Apellidos='" + apellidos +
                ", Edad=" + edad +
                ", Curso=" + curso +
                '}';
    }
}

public class CrearAlumnos {
    public static void main(String[] args) {
        int numAlumnos;
        String nombre, apellidos;
        int edad, curso;
        // Scanner
        Scanner sc = new Scanner(System.in);
        ArrayList<Alumno> alumnos = new ArrayList<>();

        // Introducir alumnos
        System.out.println("¿Cuántos alumnos quieres introducrir");
        numAlumnos = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numAlumnos ; i++){
            System.out.println("Alumno " + (i+1));
            System.out.print("Nombre: ");
            nombre = sc.nextLine();
            System.out.print("Apellidos: ");
            apellidos = sc.nextLine();
            System.out.print("Edad: ");
            edad = sc.nextInt();
            System.out.print("Curso: ");
            curso = sc.nextInt();
            sc.nextLine();

            Alumno alumno = new Alumno(nombre,apellidos,edad,curso);
            alumnos.add(alumno);
        }

        // Mostrar alumnos
        System.out.println("Lista de alumnos:");
        for (int i = 0; i<numAlumnos; i++){
            System.out.println(alumnos.get(i).toString());
        }
    }
}
