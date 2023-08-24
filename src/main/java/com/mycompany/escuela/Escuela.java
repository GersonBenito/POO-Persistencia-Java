package com.mycompany.escuela;

import com.mycompany.escuela.logica.Carrera;
import com.mycompany.escuela.logica.Controlador;
import com.mycompany.escuela.logica.Materia;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Gerson Benito
 */
public class Escuela {
    
    // Intancia de la clase controlador de persistencia a nivel global de la clase
    private static Controlador controldor = new Controlador();

    public static void main(String[] args) {
        
        int opcionMantenimiento;
        boolean salir = false;
        
        // ---------- Instancia de la clase Scanner
        Scanner scanner = new Scanner(System.in);
        
        do{
            // ---------- Menu mantenimiento---------------
            System.out.println("\n---------- Menu mantenimiento -------------------");
            System.out.println("| [1] Carrera                                    |");
            System.out.println("| [2] Materia                                    |");
            System.out.println("| [3] Alumno                                     |");
            System.out.println("-------------------------------------------------\n");
            System.out.print("Ingrese un numero del menu o ingreso -1 para salir: ");
            opcionMantenimiento = scanner.nextInt();
            
            // ---------- verificar opcion seleccionado
            if(opcionMantenimiento != -1){
                subMenu(scanner, opcionMantenimiento);
            }else{
                salir = true;
                scanner.close();
            }
            
        }while(salir == false);
        
    }
    
    public static void subMenu(Scanner scanner, int opcionMantenimiento){
        int opcionSubMenu;
        // ---------- SubMenu mantenimiento---------------
        System.out.println("\n---------- Opciones CRUD ----------------------");
        System.out.println("| [1] Agregar "+categoria(opcionMantenimiento)+"        |");
        System.out.println("| [2] Mostrar "+categoria(opcionMantenimiento)+"        |");
        System.out.println("| [3] Actualizar "+categoria(opcionMantenimiento)+"     |");
        System.out.println("| [4] Eliminar "+categoria(opcionMantenimiento)+"       |");
        System.out.println("-------------------------------------------------\n");
        System.out.print("Ingrese un numero del submenu: ");
        opcionSubMenu = scanner.nextInt();
        
        // ---- resetear scanner 
        scanner.nextLine();
        
        if(opcionSubMenu == 1){
            agregar(scanner, opcionMantenimiento);
        }
    }
    
    public static String categoria(int opcionMantenimiento){
        return opcionMantenimiento == 1 ? "Carrera" : opcionMantenimiento == 2 ? "Materia": "Alumno";
    }
    
    public static void agregar(Scanner scanner, int opcionMantenimiento){
        if(opcionMantenimiento == 1){
            Carrera carrera = new Carrera();
            Materia materia = new Materia();
            ArrayList<Materia> materiasList = new ArrayList<>();
            
            int numeroMaterias;
            String confirmacion;
            
            // ------- Ingresar datos
            carrera.setId(1);
            System.out.print("Igrese el nombre de la " +categoria(opcionMantenimiento)+" : ");
            carrera.setNombre(scanner.nextLine());
            System.out.print("Ingrese el numero de materias: ");
            numeroMaterias = scanner.nextInt();
            
            // ---- resetear scanner 
            scanner.nextLine();
            
            // ------- agregar las materias a la lista
            for(int i = 0; i < numeroMaterias; i++){
                materia.setId(i);
                System.out.print("Ingrese el nombre de la materia "+(i+1)+" : ");
                materia.setNombre(scanner.nextLine());
                System.out.print("Ingrese el tipo de la materia "+(i+1)+" : ");
                materia.setTipo(scanner.nextLine());
                materia.setCarrera(carrera);
                materiasList.add(materia);
            }
            
            // --------- Cofirmacion
            System.out.print("Esta seguro de guardar la carrera ingresado [S/N]: ");
            confirmacion = scanner.next();
            
            if(confirmacion.equals("S")){
                // ---- Guardar carrera en la base de datos
                controldor.crearCarrera(carrera);
                
                // Guard en base de datos las materias
                for(Materia signatue: materiasList){
                    controldor.crearMateria(signatue);
                }
                
                // ------ Asignar materias a la carrera
                carrera.setMateria(materiasList);
                
                // -------- actualizar carrera con las materias de la base
                controldor.actualizarCarrera(carrera);
                
                System.out.println("--------Carrera guardado correctamente-------");
            }else{
                // ---- limpiar clases y arreglo
                carrera.limpiar();
                materia.limpiar();
                materiasList.clear();
            }
        }
    }
}
