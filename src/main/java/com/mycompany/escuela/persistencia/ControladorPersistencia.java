package com.mycompany.escuela.persistencia;
import com.mycompany.escuela.logica.Alumno;
import com.mycompany.escuela.persistencia.exceptions.NonexistentEntityException;
import java.util.List;

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
    
    /**
     * metodo encargado de obtener todos los alumnos de la base de datos
     * @return 
     */
    public List<Alumno> obtenerAlumnos(){
        List<Alumno> alumnos = alumnoControllerJPA.findAlumnoEntities();
        return alumnos;
    }
    
    /**
     * metodo obtener un alumno
     * @param id del alumno a obtener
     * @return 
     */
    public Alumno obtenerAlumno(int id){
        Alumno alumno = alumnoControllerJPA.findAlumno(id);
        return alumno;
    }
    
    /**
     * metodo para eliminar un alumno
     * @param id del alumno a eliminar
     */
    public void eliminarAlumno(int id){
        try{
            alumnoControllerJPA.destroy(id);
            System.out.println("Alumno eliminado correctamente");
        }catch(NonexistentEntityException e){
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }
    
    /**
     * metodo para actualizar un alumno
     * @param alumno objeto de la clase Alumno
     */
    public void actualizarAlumno(Alumno alumno){
        try{
            alumnoControllerJPA.edit(alumno);
            System.out.println("Alumno actualizado correctamente");
        }catch(Exception e){
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }
}
