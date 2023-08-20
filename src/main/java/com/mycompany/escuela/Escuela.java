package com.mycompany.escuela;
import com.mycompany.escuela.logica.Alumno;
import com.mycompany.escuela.logica.Carrera;
import com.mycompany.escuela.logica.Controlador;
import com.mycompany.escuela.logica.Materia;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
//import java.util.TimeZone;

/**
 *
 * @author Gerson Benito
 */
public class Escuela {

    public static void main(String[] args) {
        
        // creamos una instancia del controlador para acceder a sus metodos
        Controlador controlador = new Controlador();
        
        // -------------- Crear arreglo de materias ---------------------------
        ArrayList<Materia>materiasList = new ArrayList<>();
        
        // -------------- Crear carrera de materias ---------------------------
        Carrera carrera = new Carrera(1, "Ingeniería de sistemas", materiasList);
        
        // -------------- Guardar la carrera en la BD -------------------------
        controlador.crearCarrera(carrera);
        
        // -------------- Crear materias --------------------------------------
        Materia materia1 = new Materia(1, "Fundamentos de Programación", "Ciclo 1", carrera);
        Materia materia2 = new Materia(1, "Bases de Datos", "Ciclo 1", carrera);
        Materia materia3 = new Materia(1, "Análisis y Diseño de Sistemas", "Ciclo 2", carrera);
        // -------------- Guardar materias en la DB ---------------------------
        controlador.crearMateria(materia1);
        controlador.crearMateria(materia2);
        controlador.crearMateria(materia3);
        // -------------- Agregar las materias a la lista ---------------------
        materiasList.add(materia1);
        materiasList.add(materia2);
        materiasList.add(materia3);
        
        // -------------- Actualizar la materia con la lista de tareas --------
        carrera.setMateria(materiasList);
        controlador.actualizarCarrera(carrera);
        
        // -------------- Crear alumno ----------------------------------------
        Alumno alumno = new Alumno(1, "SUSANA", "ANDRADE", new Date(), carrera);
        
        // -------------- Guardar alumno en la DB -----------------------------
        controlador.crearAlumno(alumno);
    }
    
    /**
     * metodo para formatear fechas
     * @param fecha de tipo Date
     * @return una fecha en el formato dd/mm/yyyy
     */
    public static String formatearFecha(Date fecha){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        //dateFormat.setTimeZone(TimeZone.getTimeZone("DST"));
        return dateFormat.format(fecha);
    }
}
