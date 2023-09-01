import java.io.*;
import java.util.*;

//Clase "Main" (para probar el código):
public class Main {
    public static void main(String[] args) throws IOException{
        Empresa sistema = new Empresa();
        int opcion;
        String aux;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            System.out.println("=======================");
            System.out.println("        SISTEMA        ");
            System.out.println("=======================");
            System.out.println("1.- Agregar Cobertura");
            System.out.println("2.- Eliminar Cobertura");
            System.out.println("3.- Listar Coberturas");
            System.out.println("4.- Seleccionar Cobertura");
            System.out.println("5.- Salir del Sistema");
            System.out.println("");
            System.out.print("Escoge una opción: ");

            opcion = Integer.parseInt(lector.readLine());

            if(opcion == 1){

            }
            if(opcion == 2){

            }
            if(opcion == 3){

            }
            if(opcion == 4){

            }
            if(opcion == 5){

            }

        }
        System.out.println("Fin del programa!");
    }
}

