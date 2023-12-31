package com.mycompany.escuela.logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Gerson Benito
 */

@Entity
public class Materia implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String nombre;
    private String tipo;
    @ManyToOne // es necasario indicar la relacion para que sea una relacion bidireccional
    private Carrera carrera;
    
    public Materia(){
    
    }
    
    public Materia(int id, String nombre, String tipo, Carrera carrera){
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
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
    
    public String getTipo(){
        return this.tipo;
    }
    
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    
    public Carrera getCarrera(){
        return this.carrera;
    }
    
    public void setCarrera(Carrera carrera){
        this.carrera = carrera;
    }
    
    // limpiar materias
    public void limpiar(){
        this.id = 0;
        this.nombre = "";
        this.tipo = "";
        this.carrera.limpiar();
    }
}
