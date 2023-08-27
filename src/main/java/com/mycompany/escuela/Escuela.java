package com.mycompany.escuela;

import com.mycompany.escuela.logica.Alumno;
import com.mycompany.escuela.logica.Carrera;
import com.mycompany.escuela.logica.Controlador;
import com.mycompany.escuela.logica.Materia;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

        do {
            // ---------- Menu mantenimiento---------------
            System.out.println("\n---------- Menu mantenimiento -------------------");
            System.out.println("| [1] Carrera                                    |");
            //System.out.println("| [2] Materia                                    |");
            System.out.println("| [2] Alumno                                     |");
            System.out.println("-------------------------------------------------\n");
            System.out.println("Ingrese un numero del menu o ingreso -1 para salir: ");
            opcionMantenimiento = scanner.nextInt();

            // ---------- verificar opcion seleccionado
            if (opcionMantenimiento != -1) {
                subMenu(scanner, opcionMantenimiento);
            } else {
                System.out.println("\n-----------------------------------------");
                System.out.println("| Muchas gracias por usar el programa   |");
                System.out.println("-----------------------------------------\n");
                salir = true;
                scanner.close();
            }

        } while (!salir);

    }

    public static void subMenu(Scanner scanner, int opcionMantenimiento) {
        int opcionSubMenu;
        // ---------- SubMenu mantenimiento---------------
        System.out.println("\n---------- Opciones CRUD ----------------------");
        System.out.println("| [1] Agregar " + categoria(opcionMantenimiento) + "        |");
        System.out.println("| [2] Mostrar " + categoria(opcionMantenimiento) + "        |");
        System.out.println("| [3] Actualizar " + categoria(opcionMantenimiento) + "     |");
        System.out.println("| [4] Eliminar " + categoria(opcionMantenimiento) + "       |");
        System.out.println("-------------------------------------------------\n");
        System.out.println("Ingrese un numero del submenu: ");
        opcionSubMenu = scanner.nextInt();

        // ---- resetear scanner 
        scanner.nextLine();

        if (opcionSubMenu == 1) {
            agregar(scanner, opcionMantenimiento);
        }

        if (opcionSubMenu == 2) {
            mostrar(opcionMantenimiento);
        }

        if (opcionSubMenu == 3) {
            actualizar(scanner, opcionMantenimiento);
        }

        if (opcionSubMenu == 4) {
            eliminar(scanner, opcionMantenimiento);
        }
    }

    public static String categoria(int opcionMantenimiento) {
        return opcionMantenimiento == 1 ? "Carrera" : opcionMantenimiento == 2 ? "Alumno" : "";
    }

    public static void agregar(Scanner scanner, int opcionMantenimiento) {
        if (opcionMantenimiento == 1) {
            Carrera carrera = new Carrera();
            ArrayList<Materia> materiasList = new ArrayList<>();

            int numeroMaterias;
            String confirmacion;

            // ------- Ingresar datos
            carrera.setId(1);
            System.out.println("Igrese el nombre de la " + categoria(opcionMantenimiento) + " : ");
            carrera.setNombre(scanner.nextLine());
            System.out.println("Ingrese el numero de materias: ");
            numeroMaterias = scanner.nextInt();

            // ---- resetear scanner 
            scanner.nextLine();

            // ------- agregar las materias a la lista
            for (int i = 0; i < numeroMaterias; i++) {

                // crear instancia de materia para almacenar materias
                Materia materia = new Materia();

                materia.setId(i);
                System.out.println("Ingrese el nombre de la materia " + (i + 1) + " : ");
                materia.setNombre(scanner.nextLine());
                System.out.println("Ingrese el tipo de la materia " + (i + 1) + " : ");
                materia.setTipo(scanner.nextLine());
                materia.setCarrera(carrera);
                materiasList.add(materia);

            }

            // --------- Cofirmacion
            System.out.print("Esta seguro de guardar la carrera ingresado [S/N]: ");
            confirmacion = scanner.next();

            if (confirmacion.equals("S")) {
                // ---- Guardar carrera en la base de datos
                controldor.crearCarrera(carrera);

                // Guard en base de datos las materias
                for (Materia signature : materiasList) {
                    controldor.crearMateria(signature);
                }

                // ------ Asignar materias a la carrera
                carrera.setMateria(materiasList);

                // -------- actualizar carrera con las materias de la base
                controldor.actualizarCarrera(carrera);

                System.out.println("--------Carrera guardado correctamente-------");
            } else {
                Materia materia = new Materia();
                // ---- limpiar clases y arreglo
                carrera.limpiar();
                materia.limpiar();
                materiasList.clear();
            }
        }

        if (opcionMantenimiento == 2) {
            // Crear instancia de la clase alumno
            Alumno alumno = new Alumno();

            // Ingreso de datos
            System.out.println("Ingrese el nombre del " + categoria(opcionMantenimiento) + " : ");
            alumno.setNombre(scanner.nextLine());
            System.out.println("Ingrese el apellido del " + categoria(opcionMantenimiento) + ": ");
            alumno.setApellido(scanner.nextLine());
            alumno.setFecha_nacimiento(new Date());
            mostrar(1);
            System.out.println("Ingrese el id de una de las carreras disponible: ");

            // obtener la carrera seleccionado
            Carrera carreraSeleccioado = controldor.obtenerCarrera(scanner.nextInt());

            if (carreraSeleccioado != null) {
                alumno.setCarrera(carreraSeleccioado);
                // guardar alumno en la DB
                controldor.crearAlumno(alumno);
                System.out.println("Alumno agregado correctamente\n");
            } else {
                System.out.println("\n-----------------------------------------");
                System.out.println("| Carrera seleccionado no se encuentra    |");
                System.out.println("-----------------------------------------\n");
            }
        }
    }

    public static void mostrar(int opcionMantenimiento) {
        if (opcionMantenimiento == 1) {

            //usamos los metodos del controlador
            ArrayList<Carrera> carrerasList = controldor.obtenerCarreras();

            // recorrer las carreras si no esta vacio
            if (!carrerasList.isEmpty()) {
                System.out.println("\n--------- Carreras -------------");
                // recorremos el arreglo de las carreras
                for (Carrera carrera : carrerasList) {
                    System.out.println("Id carrera: " + carrera.getId());
                    System.out.println("Nombre carrera: " + carrera.getNombre());
                    System.out.println("Numero de materias: " + carrera.getMateria().size());
                    System.out.println("----Materias asociados a la carrera");

                    // recorrer las materias asociados a la carrera
                    for (Materia materia : carrera.getMateria()) {
                        System.out.println("Id materia: " + materia.getId());
                        System.out.println("Nombre materia: " + materia.getNombre());
                        System.out.println("Tipo materia: " + materia.getTipo());
                    }
                    System.out.println("----------------------------------\n");
                }

            } else {
                System.out.println("\n-----------------------------------------");
                System.out.println("| A un no hay carreras para mostrar     |");
                System.out.println("-----------------------------------------\n");
            }
        }

        if (opcionMantenimiento == 2) {
            // obtener los alumnos de la base de datos
            ArrayList<Alumno> alumnosList = controldor.obtenerAlumnos();
            if (!alumnosList.isEmpty()) {
                System.out.println("\n--------- Alumnos -------------");
                for (Alumno alumno : alumnosList) {
                    System.out.println("Id alumno: " + alumno.getId());
                    System.out.println("Nombre del alumno: " + alumno.getNombre());
                    System.out.println("Apellido del alumno: " + alumno.getApellido());
                    System.out.println("Fecha de nacimiento del alumno: " + formatearFecha(alumno.getFecha_nacimiento()));
                    System.out.println("Carrera seleccioado: " + alumno.getCarrera().getNombre());
                    System.out.println("----------------------------------\n");
                }
            } else {
                System.out.println("\n----------------------------------------");
                System.out.println("| A un no hay alumnos para mostrar      |");
                System.out.println("-----------------------------------------\n");
            }
        }
    }

    public static void eliminar(Scanner scanner, int opcionMantenimiento) {

        boolean confirmacion = false;
        String opcion;
        int id;

        do {
            System.out.print("Ingrese el id a eliminar: ");
            id = scanner.nextInt();
            // resetear la clase scanner
            scanner.nextLine();

            if (opcionMantenimiento == 1) {
                Carrera carrera = controldor.obtenerCarrera(id);
                if (carrera != null) {
                    // confirmacion del usuario
                    System.out.print("Estas seguro de eliminar la carrera " + carrera.getNombre() + " [S/N]: ");
                    opcion = scanner.next();

                    if (opcion.equals("S")) {
                        // eliminar las carreras asociados a las carreras
                        for (Materia materia : carrera.getMateria()) {
                            controldor.eliminarMateria(materia.getId());
                        }

                        // eliminar la carrera
                        controldor.eliminarCarrera(carrera.getId());
                        System.out.println("Carrera " + carrera.getNombre() + " eliminado correctamente");
                        confirmacion = true;
                    } else {
                        System.out.print("Desea ingresar un nuevo id [S/N] ?: ");
                        opcion = scanner.next();
                        confirmacion = !opcion.equals("S");
                    }
                } else {
                    System.out.println("\n-----------------------------------------------");
                    System.out.println("| No se encontro ninguna carrera con el id      |");
                    System.out.println("-------------------------------------------------\n");
                    System.out.println("Desea ingresar un nuevo id [S/N] ?: ");
                    opcion = scanner.next();
                    if (opcion.equals("N")) {
                        confirmacion = true;
                    }
                }
            }

            if (opcionMantenimiento == 2) {
                Alumno alumno = controldor.obtenerAlumno(id);
                if (alumno != null) {
                    // confirmacion del usuario
                    System.out.print("Estas seguro de eliminar el alumno " + alumno.getNombre() + " [S/N]: ");
                    opcion = scanner.next();

                    if (opcion.equals("S")) {
                        // eliminar el usuario
                        controldor.eliminarAlumno(alumno.getId());
                        System.out.println("Alumno " + alumno.getNombre() + " eliminado correctamente");
                        confirmacion = true;
                    } else {
                        System.out.print("Desea ingresar un nuevo id [S/N] ?: ");
                        opcion = scanner.next();
                        confirmacion = !opcion.equals("S");
                    }
                } else {
                    System.out.println("\n-----------------------------------------------");
                    System.out.println("| No se encontro ningun alumno con el id        |");
                    System.out.println("-------------------------------------------------\n");
                    System.out.println("Desea ingresar un nuevo id [S/N] ?: ");
                    opcion = scanner.next();
                    if (opcion.equals("N")) {
                        confirmacion = true;
                    }
                }
            }
        } while (!confirmacion);
    }

    public static void actualizar(Scanner scanner, int opcionMantenimiento) {

        boolean confirmacion = false;
        String opcion;
        int id;

        do {

            System.out.println("\n------- Actualizar datos ------------------\n");
            System.out.println("Ingrese el id a actualizar: ");
            id = scanner.nextInt();
            // resetear la clase scanner
            scanner.nextLine();

            if (opcionMantenimiento == 1) {

                // buscar la carrera con id proporcionado
                Carrera carrera = controldor.obtenerCarrera(id);

                // mostrarle los datos actuales
                if (carrera != null) {
                    System.out.println("\n--------- Datos de la carrera a actualizar -------------");
                    System.out.println("Id carrera: " + carrera.getId());
                    System.out.println("Nombre carrera: " + carrera.getNombre());
                    System.out.println("Numero de materias: " + carrera.getMateria().size());
                    System.out.println("----Materias asociados a la carrera");

                    // recorrer las materias asociados a la carrera
                    for (Materia materia : carrera.getMateria()) {
                        System.out.println("Id materia: " + materia.getId());
                        System.out.println("Nombre materia: " + materia.getNombre());
                        System.out.println("Tipo materia: " + materia.getTipo());
                    }
                    System.out.println("----------------------------------\n");

                    System.out.println("Ingrese el nuevo nombre de la carrera: ");
                    carrera.setNombre(scanner.nextLine());

                    // confirmacion del usuario
                    System.out.println("Estas seguro de actualizar la carrera " + carrera.getNombre() + " [S/N]: ");
                    opcion = scanner.next();

                    if (opcion.equals("S")) {
                        controldor.actualizarCarrera(carrera);
                        System.out.println("------- Carrera " + carrera.getNombre() + " actualizado correctamente -----------");
                        confirmacion = true;
                    } else {
                        System.out.println("Desea ingresar un nuevo id [S/N] ?: ");
                        opcion = scanner.next();
                        confirmacion = !opcion.equals("S");
                    }

                } else {
                    System.out.println("\n-----------------------------------------------");
                    System.out.println("| No se encontro ninguna carrera con el id      |");
                    System.out.println("-------------------------------------------------\n");
                    System.out.println("Desea ingresar un nuevo id [S/N] ?: ");
                    opcion = scanner.next();
                    if (opcion.equals("N")) {
                        confirmacion = true;
                    }
                }
            }

            if (opcionMantenimiento == 2) {
                
                // instancia de la clase para actualizar
                Alumno alumno = new Alumno();
                // buscar alumno de la DB
                Alumno alumnoSelect = controldor.obtenerAlumno(id);

                String cambioCarrera;

                if (alumnoSelect != null) {
                    System.out.println("\n--------- Datos del alumno a actualizar -------------");
                    System.out.println("Id alumno: " + alumnoSelect.getId());
                    System.out.println("Nombre del alumno: " + alumnoSelect.getNombre());
                    System.out.println("Apellido del alumno: " + alumnoSelect.getApellido());
                    System.out.println("Fecha de nacimiento del alumno: " + formatearFecha(alumnoSelect.getFecha_nacimiento()));
                    System.out.println("Carrera seleccioado: " + alumnoSelect.getCarrera().getNombre());
                    System.out.println("----------------------------------\n");

                    alumno.setId(alumnoSelect.getId());
                    // solicitar nuevos datos
                    System.out.println("Ingrese el nuevo nombre del alumno: ");
                    alumno.setNombre(scanner.nextLine());
                    System.out.println("Ingrese el nuevo apellido del alumno: ");
                    alumno.setApellido(scanner.nextLine());

                    // consultar si desde cambiar de carrera
                    System.out.println("Desea cambiar de carrera al alumno [S/N]: ");
                    cambioCarrera = scanner.next();

                    if (cambioCarrera.equals("S")) {
                        // bandera para verificar el ingreso del id
                        boolean isValid = false;
                        String cambio;
                        // mostrarle la lista de carreras disponibles
                        mostrar(1);

                        // soolicitar el ingreso de la nueva carrera
                        do {
                            System.out.println("Ingrese el id de la carrera: ");
                            Carrera carrera = controldor.obtenerCarrera(scanner.nextInt());

                            if (carrera != null) {
                                alumno.setCarrera(carrera);
                                alumno.setFecha_nacimiento(alumnoSelect.getFecha_nacimiento());

                            } else {
                                System.out.println("\n-----------------------------------------------");
                                System.out.println("| No se encontro ningun carrera con el id      |");
                                System.out.println("-------------------------------------------------\n");
                                System.out.println("Desea ingresar un nuevo id [S/N] ?: ");
                                cambio = scanner.next();

                                isValid = !cambio.equals("S");
                            }
                        } while (isValid);

                        // confirmacion del usuario
                        System.out.println("Estas seguro de actualizar el alumno " + alumnoSelect.getNombre() + " con los nuevos datos? [S/N]");
                        opcion = scanner.next();

                        if (opcion.equals("S")) {
                            controldor.actualizarAlumno(alumno);
                            System.out.println("------- Alumno " + alumnoSelect.getNombre() + " actualizado correctamente -----------");
                            confirmacion = true;
                        } else {
                            System.out.println("Desea ingresar un nuevo id [S/N] ?: ");
                            opcion = scanner.next();
                            confirmacion = !opcion.equals("S");
                        }
                    } else {
                        // confirmacion del usuario
                        System.out.println("Estas seguro de actualizar el alumno " + alumnoSelect.getNombre() + " con los nuevos datos? [S/N]");
                        opcion = scanner.next();

                        if (opcion.equals("S")) {
                            // asignamos las carreras y fecha que ya tenia el alumno
                            alumno.setCarrera(alumnoSelect.getCarrera());
                            alumno.setFecha_nacimiento(alumnoSelect.getFecha_nacimiento());
                            controldor.actualizarAlumno(alumno);
                            System.out.println("------- Alumno " + alumnoSelect.getNombre() + " actualizado correctamente -----------");
                            confirmacion = true;
                        } else {
                            System.out.println("Desea ingresar un nuevo id [S/N] ?: ");
                            opcion = scanner.next();
                            confirmacion = !opcion.equals("S");
                        }
                    }

                } else {
                    System.out.println("\n-----------------------------------------------");
                    System.out.println("| No se encontro ningun alumno con el id      |");
                    System.out.println("-------------------------------------------------\n");
                    System.out.println("Desea ingresar un nuevo id [S/N] ?: ");
                    opcion = scanner.next();
                    if (opcion.equals("N")) {
                        confirmacion = true;
                    }
                }

            }

        } while (!confirmacion);
    }

    /**
     * metodo para formatear fechas
     *
     * @param fecha de tipo Date
     * @return una fecha en el formato dd/mm/yyyy
     */
    public static String formatearFecha(Date fecha) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        //dateFormat.setTimeZone(TimeZone.getTimeZone("DST"));
        return dateFormat.format(fecha);
    }
}
