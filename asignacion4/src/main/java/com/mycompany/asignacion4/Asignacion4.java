package com.mycompany.asignacion4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Asignacion4 {

    public static void main(String[] args) throws IOException {
        
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> alumnos = new ArrayList();
        
        Path ruta = Path.of("alumnos_registrados_NIO.txt");
        
        while (true) {
            //menu
            System.out.println("==MENU==");
            System.out.println("1. Registrar");
            System.out.println("2. Ver archivo");
            System.out.println("3. Buscar por id");
            System.out.println("4. Editar");
            System.out.println("5. Eliminar");
            System.out.println("6. Salir");
            System.out.println("");
            //campo para ingresar la opcion
            System.out.println("Ingrese su eleccion a continuacion");
            int eleccion = scanner.nextInt();
            scanner.nextLine();
            
            //funcionamiento
            switch (eleccion) {
                
                case 1: // registro
                    try {
                        System.out.println("Nombre del alumno: ");
                        String nombreRegistro = scanner.nextLine();
                        System.out.println("ID del alumno:");
                        String idRegistro = scanner.nextLine();
                        
                        String Registrar = idRegistro + " - " + nombreRegistro + "\n";
                        Files.writeString(ruta, Registrar,   // \n es salto de linea \tabulacion \r retorno carro
                        StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                        System.out.println("Archivo guardado con exito");    

                    } catch (IOException e) {
                        System.err.println("Error en archivo: " + e.getMessage());
                    }
                    break;
                
                case 2: //lectura
                    List<String> lineas = Files.readAllLines(ruta);
                    lineas.forEach(System.out::println); //String correspondiente por iteracion
                    break;
                    
                case 3: //busqueda
                    
                    List<String> lineasBusqueda = Files.readAllLines(ruta);                    
                    System.out.println("Ingrese el ID a buscar:");
                    String idBuscar = scanner.nextLine().trim();
                    boolean encontrado = false;
                    
                    for (String registro : lineasBusqueda) {
                        if (registro.startsWith(idBuscar)) {
                            encontrado = true;
                            break;
                        }
                    }
            
                    if (encontrado){
                        System.out.println("La ID "+ idBuscar + " si existe en el archivo");
                    }else{
                        System.out.println("La ID "+ idBuscar + " no existe en el archivo");
                    }
                    break;
                
                case 4: //editar alumno por id
                    
                    List<String> lineasEditar = Files.readAllLines(ruta);                    
                    System.out.println("Ingrese el ID a editar:");
                    idBuscar = scanner.nextLine().trim();
                    boolean editar = false;
                    
                    for (int i = 0; i < lineasEditar.size(); i++) { //
                        String registro = lineasEditar.get(i);
                        
                        if (registro.startsWith(idBuscar + " -")); {
                        
                            System.out.println("ID encontrado, ingrese el nombre nuevo:");
                            String nuevoNombre = scanner.nextLine();
                            String registroActualizado = idBuscar + " - " + nuevoNombre;
                            lineasEditar.set(i, registroActualizado);
                            
                            editar = true;
                            break;
                        }
                    }
                    
                    if (editar) {
                        Files.write(ruta, lineasEditar);
                        System.out.println("Nombre actualizado");
                    }
                    else {
                        System.out.println("No se encontro la ID");
                    }
                    
                    
                    break;
                
                case 5:
                    List<String> lineasEliminar = Files.readAllLines(ruta);                    
                    System.out.println("Ingrese el ID a eliminar:");
                    idBuscar = scanner.nextLine().trim();
                    boolean eliminar = false;
                    
                    for (int i = 0; i < lineasEliminar.size(); i++) { //
                        String registro = lineasEliminar.get(i);
                        
                        if (registro.startsWith(idBuscar + " -")); {
                        
                            System.out.println("ID encontrado, eliminar?");
                            System.out.println("1. SI - 2. NO");
                            int idEliminar = scanner.nextInt();
                            
                            if (idEliminar == 1) {
                                scanner.nextLine();
                                eliminar = true;
                                lineasEliminar.remove(i);
                                break;
                            }
                            if (idEliminar == 2) {
                                scanner.nextLine();
                                System.out.println("Cancelando operacion.");
                            }
                            else {
                                scanner.nextLine();
                                System.out.println("ERROR");
                            }
                            
                        }
                    }
                    
                    if (eliminar) {
                        System.out.println("Alumno eliminado.");
                    }
                    else {
                        System.out.println("No se encontro la ID.");
                    }
                    break;
                    
                case 6: //finalizar programa
                    System.out.println("Saliendo");
                    return; 
                
                default: //en caso de opcion fuera del menu
                    System.out.println("Opcion no valida");
                    continue;
                    
            }
            
        }
                
    
  }
}
