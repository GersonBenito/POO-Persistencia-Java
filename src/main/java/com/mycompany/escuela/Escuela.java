package com.mycompany.escuela;
import com.mycompany.escuela.logica.Alumno;
import com.mycompany.escuela.logica.Controlador;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Gerson Benito
 */
public class Escuela {

    public static void main(String[] args) {
        
        // creamos una instancia del controlador para acceder a sus metodos
        Controlador controlador = new Controlador();
        
        // creamos una instancia de un Alumno para crear un nuevo alumno
        //Alumno alumno = new Alumno(1, "Diana", "Iñiguez", new Date());
        
        // enviar los datos al controlador para crear el nuevo alumno
        //controlador.crearAlumno(alumno);
        
        // asignar los alumnos al arreglo
        ArrayList<Alumno> alumnos = controlador.obtenerAlumnos();
        
        // recorrer los alumnos
        for(Alumno alu : alumnos){
            System.out.println("Id: " + alu.getId());
            System.out.println("Nombe: " + alu.getNombre());
            System.out.println("Apellido: " + alu.getApellido());
            System.out.println("Fecha nacimiento: " + alu.getFecha_nacimiento());
        }
        
        // obtner un alumno por medio de un id
        Alumno onlyStudent = controlador.obtenerAlumno(2);
        System.out.println("****** Alumno **********");
        System.out.println("Id: " + onlyStudent.getId());
        System.out.println("Nombre: " + onlyStudent.getNombre());
        System.out.println("Apellido: " + onlyStudent.getApellido());
        System.out.println("Fecha nacimiento: " + onlyStudent.getFecha_nacimiento());
        
        // eliminar un alumno
        //controlador.eliminarAlumno(3);
        
        // actualizar un alumno
        //alumno.setApellido("Serrano");
        //controlador.actualizarAlumno(alumno);
    }
}
