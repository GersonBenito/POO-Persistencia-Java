package com.mycompany.escuela.logica;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Gerson Benito
 */

@Entity
public class Carrera implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String nombre;
    @OneToMany(mappedBy = "carrera") // relacion de 1 a n bidireccional, indicando con que atributo se va hacer la relacion
    private ArrayList<Materia> materia;
    
    public Carrera(){
    
    }
    
    public Carrera(int id, String nombre, ArrayList<Materia> materia){
        this.id = id;
        this.nombre = nombre;
        this.materia = materia;
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
    
    public ArrayList<Materia> getMateria(){
        return this.materia;
    }
    
    public void setMateria(ArrayList<Materia> materia){
        this.materia = materia;
    }
    
    // metodo limpiar datos
    public void limpiar(){
        this.id = 0;
        this.nombre = "";
        this.materia.clear();
    }
}
