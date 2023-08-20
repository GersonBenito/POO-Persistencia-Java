package com.mycompany.escuela.logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gerson Benito
 */

@Entity // marcamos con la anotacion @Entity que la clase es una tabla en la base de datos
public class Alumno implements Serializable {
    
    @Id // marcamos con la anotacion @Id que el atributo debajo de este es una llave primaria
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // agregamos el tipo de estrategia para incrementar el id
    private int id;
    private String nombre;
    private String apellido;
    @Temporal(TemporalType.DATE) // marcamos con la anotacion @Temporal para indicar que es una fecha en la db
    private Date fecha_nacimiento;
    @OneToOne // anotacion para indicar a JPA que la relacion es de 1 a 1
    private Carrera carrera;
    
    public Alumno(){
        
    }
    
    public Alumno(int id, String nombre, String apellido, Date fecha_nacimiento, Carrera carrera){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.carrera = carrera;
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getApellido(){
        return this.apellido;
    }
    
    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    
    public Date getFecha_nacimiento(){
        return this.fecha_nacimiento;
    }
    
    public void setFecha_nacimiento(Date fecha_nacimiento){
        this.fecha_nacimiento = fecha_nacimiento;
    }
    
    public Carrera getCarrera(){
        return this.carrera;
    }
    
    public void setCarrera(Carrera carrera){
        this.carrera = carrera;
    }
}
