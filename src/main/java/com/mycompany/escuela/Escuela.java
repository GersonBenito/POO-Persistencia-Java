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
    private static final Controlador controldor = new Controlador();

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
        
        if(opcionSubMenu == 2){
            mostrar(opcionMantenimiento);
        }
        
        if(opcionSubMenu == 4){
            eliminar(scanner, opcionMantenimiento);
        }
    }
    
    public static String categoria(int opcionMantenimiento){
        return opcionMantenimiento == 1 ? "Carrera" : opcionMantenimiento == 2 ? "Materia": "Alumno";
    }
    
    public static void agregar(Scanner scanner, int opcionMantenimiento){
        if(opcionMantenimiento == 1){
            Carrera carrera = new Carrera();
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
                
                // crear instancia de materia para almacenar materias
                Materia materia = new Materia();
                
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
                for(Materia signature: materiasList){
                    controldor.crearMateria(signature);
                }
                
                // ------ Asignar materias a la carrera
                carrera.setMateria(materiasList);
                
                // -------- actualizar carrera con las materias de la base
                controldor.actualizarCarrera(carrera);
                
                System.out.println("--------Carrera guardado correctamente-------");
            }else{
                Materia materia = new Materia();
                // ---- limpiar clases y arreglo
                carrera.limpiar();
                materia.limpiar();
                materiasList.clear();
            }
        }
    }
    
    public static void mostrar(int opcionMantenimiento){
        if(opcionMantenimiento == 1){
            
            //usamos los metodos del controlador
            ArrayList<Carrera> carrerasList = controldor.obtenerCarreras();
            
            // recorrer las carreras si no esta vacio
            if(!carrerasList.isEmpty()){
                System.out.println("\n--------- Carreras -------------");
                // recorremos el arreglo de las carreras
                for(Carrera carrera : carrerasList){
                    System.out.println("Id carrera: " + carrera.getId());
                    System.out.println("Nombre carrera: " + carrera.getNombre());
                    System.out.println("Numero de materias: " + carrera.getMateria().size());
                    System.out.println("----Materias asociados a la carrera");
                    
                    // recorrer las materias asociados a la carrera
                    for(Materia materia : carrera.getMateria()){
                        System.out.println("Id materia: " + materia.getId());
                        System.out.println("Nombre materia: " + materia.getNombre());
                        System.out.println("Tipo materia: " + materia.getTipo());
                    }
                    System.out.println("----------------------------------\n");
                }
                
            }else{
                System.out.println("\n-----------------------------------------");
                System.out.println("| A un no hay carreras para mostrar     |");
                System.out.println("-----------------------------------------\n");
            }
        }
    }
    
    public static void eliminar(Scanner scanner, int opcionMantenimiento){
        
        boolean confirmacion = false;
        String opcion;
        int id;
        
        if(opcionMantenimiento == 1){
            do{
                System.out.print("Ingrese el id a eliminar: ");
                id = scanner.nextInt();
                // resetear la clase scanner
                scanner.nextLine();
                
                Carrera carrera = controldor.obtenerCarrera(id);
                if(carrera != null){
                    // confirmacion del usuario
                    System.out.print("Estas seguro de eliminar la carrera " + carrera.getNombre() + " [S/N]: ");
                    opcion = scanner.next();
                    
                    if(opcion.equals("S")){
                        // eliminar las carreras asociados a las carreras
                        for(Materia materia : carrera.getMateria()){
                            controldor.eliminarMateria(materia.getId());
                        }
                        
                        // eliminar la carrera
                        controldor.eliminarCarrera(carrera.getId());
                        System.out.println("Carrera " + carrera.getNombre() + " eliminado correctamente");
                        confirmacion = true;
                    }else{
                        System.out.print("Desea ingresar un nuevo id [S/N] ?: ");
                        opcion = scanner.next();
                        if(opcion.equals("S")){
                            confirmacion = false;
                        }else{
                            confirmacion = true;
                        }
                    }
                }else{
                    System.out.println("\n-----------------------------------------------");
                    System.out.println("| No se encontro ninguna carrera con el id      |");
                    System.out.println("-------------------------------------------------\n");
                    System.out.print("Desea ingresar un nuevo id [S/N] ?: ");
                    opcion = scanner.next();
                    if(opcion.equals("N")){
                        confirmacion = true;
                    }
                }
            
            }while(confirmacion == false);
        }
    }
}
