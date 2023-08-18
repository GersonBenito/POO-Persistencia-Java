package com.mycompany.escuela;
import com.mycompany.escuela.logica.Alumno;
import com.mycompany.escuela.logica.Controlador;
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
        Alumno alumno = new Alumno(1, "Gerson", "Benito", new Date());
        
        // enviar los datos al controlador para crear el nuevo alumno
        controlador.crearAlumno(alumno);
    }
}
