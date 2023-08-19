package com.mycompany.escuela.logica;

import com.mycompany.escuela.persistencia.ControladorPersistencia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gerson Benito
 */
public class Controlador {
    
    // creamos una instancia del controlador de persistencias
    ControladorPersistencia controladorPersistencia = new ControladorPersistencia();
    
    /**
     * Metodo encargado de crear un nuevo alumno
     * @param alumno objeto alumno de tipo Alumno
     */
    public void crearAlumno(Alumno alumno){
        controladorPersistencia.crearAlumno(alumno);
    }
    
    /**
     * Metodo para obtener todos los alumnos
     * @return 
     */
    public ArrayList<Alumno> obtenerAlumnos(){
        List<Alumno> alumnos = controladorPersistencia.obtenerAlumnos();
        // hacer casteo de list
        ArrayList<Alumno> alumnosList = new ArrayList<>(alumnos);
        return alumnosList;
    }
    
    /**
     * Metodo para obtener un alumno
     * @param id del alumno a obtener
     * @return 
     */
    public Alumno obtenerAlumno(int id){
        Alumno alumno = controladorPersistencia.obtenerAlumno(id);
        return alumno;
    }
    
    /**
     * Metodo para eliminar un alumno
     * @param id del alumno
     */
    public void eliminarAlumno(int id){
        controladorPersistencia.eliminarAlumno(id);
    }
    
    /**
     * Metodo para actualizar un alumno
     * @param alumno objeto de la clase Alumno
     */
    public void actualizarAlumno(Alumno alumno){
        controladorPersistencia.actualizarAlumno(alumno);
    }
    
}
