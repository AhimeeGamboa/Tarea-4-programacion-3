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
                        
                        String Registrar = idRegistro + " - " + nombreRegistro;
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
                    System.out.println("Ingrese el ID a buscar:");
                    String idBuscar = scanner.nextLine();
                    if (alumnos.contains(idBuscar)){
                        System.out.println("La ID "+ idBuscar + " si existe en el archivo");
                    }else{
                        System.out.println("La ID "+ idBuscar + " no existe en el archivo");
                    }
                    break;
                
                case 4: //editar alumno por id
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
