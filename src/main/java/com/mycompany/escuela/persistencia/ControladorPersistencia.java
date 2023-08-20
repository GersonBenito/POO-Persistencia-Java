package com.mycompany.escuela.persistencia;
import com.mycompany.escuela.logica.Alumno;
import com.mycompany.escuela.logica.Carrera;
import com.mycompany.escuela.persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gerson Benito
 */
public class ControladorPersistencia {
    
    // creamos una instancia del contola de alumnos de JPA
    AlumnoJpaController alumnoControllerJPA = new AlumnoJpaController();
    CarreraJpaController carreraControllerJPA = new CarreraJpaController();
    
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
    public ArrayList<Alumno> obtenerAlumnos(){
        List<Alumno> alumnos = alumnoControllerJPA.findAlumnoEntities();
        
        // hace un casteo de un list a un ArrayList
        ArrayList<Alumno> alunmosList = new ArrayList<>(alumnos);
        return alunmosList;
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

    // metodos para la clase de carrera
    public void crearCarrera(Carrera carrera) {
        carreraControllerJPA.create(carrera);
    }
    
    public ArrayList<Carrera> obtenerCarreras(){
        List<Carrera> carreras = carreraControllerJPA.findCarreraEntities();
        ArrayList<Carrera> carrerasList = new ArrayList<>(carreras);
        return carrerasList;
    }
    
    public Carrera obtenerCarrera(int id){
        Carrera carrera = carreraControllerJPA.findCarrera(id);
        return carrera;
    }
    
    public void actualizarCarrera(Carrera carrera){
        try{
            carreraControllerJPA.edit(carrera);
            System.out.println("Carrera actualizado correctamente");
        }catch(Exception e){
            System.out.println("Error ala ctualizar una carrera: " + e.getMessage());
        }
    }
    
    public void eliminarCarrera(int id){
        try{
            carreraControllerJPA.destroy(id);
            System.out.println("Carrera eliminado correctamente");
        }catch(NonexistentEntityException e){
            System.out.println("Error al eliminar " + e.getMessage());
        }
    }
}
