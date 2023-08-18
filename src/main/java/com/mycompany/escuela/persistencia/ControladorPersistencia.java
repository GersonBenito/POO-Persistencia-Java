package com.mycompany.escuela.persistencia;
import com.mycompany.escuela.logica.Alumno;

/**
 *
 * @author Gerson Benito
 */
public class ControladorPersistencia {
    
    // creamos una instancia del contola de alumnos de JPA
    AlumnoJpaController alumnoControllerJPA = new AlumnoJpaController();
    
    /**
     * metodo encargado de crear un nuevo alumno
     * @param alumno de tipo Alumno
     */
    public void crearAlumno(Alumno alumno){
        alumnoControllerJPA.create(alumno);
    }
}
