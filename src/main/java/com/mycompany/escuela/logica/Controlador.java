package com.mycompany.escuela.logica;

import com.mycompany.escuela.persistencia.ControladorPersistencia;

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
    
}
