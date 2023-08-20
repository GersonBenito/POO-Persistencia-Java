package com.mycompany.escuela;
import com.mycompany.escuela.logica.Alumno;
import com.mycompany.escuela.logica.Carrera;
import com.mycompany.escuela.logica.Controlador;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author Gerson Benito
 */
public class Escuela {

    public static void main(String[] args) {
        
        // creamos una instancia del controlador para acceder a sus metodos
        Controlador controlador = new Controlador();
        
        //System.out.println("********* Metodos para la clase carrera ***********");
        // crear una carrera
        //Carrera carrera = new Carrera(1, "Ingeniería de telecomunicaciones");
        
        // agregar una nueva carrera en la base de datos
        //controlador.crearCarrera(carrera);
        
        // obtener una carrera
        //Carrera onlyCarrera = controlador.obtenerCarrera(1);
        //System.out.println("Carrera: " + onlyCarrera.getNombre());
        
        System.out.println("\n********* Metodos para la clase alumno ***********");
        // creamos una instancia de un Alumno para crear un nuevo alumno
        // al crear un nuevo alumno le asociamos una carrera
        //Alumno alumno = new Alumno(1, "JAIME", "MOLINA", new Date(), onlyCarrera);
        
        // almacenamos el alumno en la base de datos
        //controlador.crearAlumno(alumno);
        
        System.out.println("******* DATOS DEL ALUMNO ********\n");
        Alumno datosAlumno = controlador.obtenerAlumno(3);
        System.out.println("Nombre: " + datosAlumno.getNombre());
        System.out.println("Apellido: " + datosAlumno.getApellido());
        System.out.println(datosAlumno.getFecha_nacimiento());
        System.out.println("Fecha de nacimiento: " + formatearFecha(datosAlumno.getFecha_nacimiento()));
        System.out.println("Carrera: " + datosAlumno.getCarrera().getNombre());
    }
    
    public static String formatearFecha(Date fecha){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("DST"));
        return dateFormat.format(fecha);
    }
}
