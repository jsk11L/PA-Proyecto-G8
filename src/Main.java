import java.io.*;
import java.util.*;

//Clase "Main" (para probar el código):
public class Main {
    public static void main(String[] args) throws IOException{
        Empresa sistema = new Empresa();
        int opcion;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            System.out.println("=======================");
            System.out.println("        SISTEMA        ");
            System.out.println("=======================");
            System.out.println("1.- Agregar Cobertura");
            System.out.println("2.- Eliminar Cobertura");
            System.out.println("3.- Listar Coberturas");
            System.out.println("4.- Mostrar Coberturas más Demsa");
            System.out.println("5.- Mostrar Coberturas menos Demsa");
            System.out.println("6.- Seleccionar Cobertura");
            System.out.println("7.- Salir del Sistema");
            System.out.println("");
            System.out.print("Escoge una opción: ");

            opcion = Integer.parseInt(lector.readLine());

            if(opcion == 1){

                System.out.print("Ingrese la región de la cobertura: ");
                String region = lector.readLine();
                System.out.print("Ingrese el codigo de la región de la cobertura: ");
                String codigo = lector.readLine();
                Cobertura aux = new Cobertura(region, codigo);
                if(sistema.agregarCobertura(aux)){
                    System.out.println("La cobertura fue agregada correctamente.");
                }
                else{
                    System.out.println("Ya existe una cobertura con ese código.");
                }

            }
            if(opcion == 2){

                System.out.print("Ingrese la región de la cobertura: ");
                String region = lector.readLine();
                System.out.print("Ingrese el codigo de la región de la cobertura: ");
                String codigo = lector.readLine();
                Cobertura aux = new Cobertura(region, codigo);
                if(sistema.eliminarCobertura(aux)){
                    System.out.println("La cobertura fue eliminada correctamente.");
                }
                else{
                    System.out.println("No se encontró la cobertura ingresada.");
                }

            }
            if(opcion == 3){
                ArrayList<Cobertura> lista = sistema.getCoberturas();
                for(int i = 0; i < lista.size(); i++){
                    Cobertura aux = lista.get(i);
                    System.out.println("=====" + (i+1) + "=====");
                    System.out.println("Cobertura de la Región: " + aux.getRegion());
                    System.out.println("Codigo de la Región: "+ aux.getCodigoRegion());
                    System.out.println("Numero de Suscriptores en la Región: "+ aux.getNumeroClientes());
                }

            }
            if(opcion == 4){
                ArrayList<Cobertura> lista = sistema.getCoberturasMenosDensas();
                for(int i = 0; i < lista.size(); i++){
                    Cobertura aux = lista.get(i);
                    System.out.println("====="+(i+1)+"=====");
                    System.out.println("Cobertura de la Región: "+ aux.getRegion());
                    System.out.println("Codigo de la Región: "+ aux.getCodigoRegion());
                    System.out.println("Numero de Suscriptores en la Región: "+ aux.getNumeroClientes());
                }
            }
            if(opcion == 5){
                ArrayList<Cobertura> lista = sistema.getCoberturasMasDensas();
                for(int i = 0; i < lista.size(); i++){
                    Cobertura aux = lista.get(i);
                    System.out.println("====="+(i+1)+"=====");
                    System.out.println("Cobertura de la Región: "+aux.getRegion());
                    System.out.println("Codigo de la Región: "+ aux.getCodigoRegion());
                    System.out.println("Numero de Suscriptores en la Región: "+ aux.getNumeroClientes());
                }
            }
            if(opcion == 6){
                System.out.print("Ingrese la región de la cobertura: ");
                String region = lector.readLine();
                System.out.print("Ingrese el codigo de la región de la cobertura: ");
                String codigo = lector.readLine();

                Cobertura aux = sistema.buscarCobertura(region, codigo);
                if(aux != null){
                    sistema.menuCoberturas(aux);
                }
                else{
                    System.out.println("No se encontró la cobertura buscada");
                }

            }
            if(opcion == 7){
                break;
            }

        }
        System.out.println("Fin del programa!");
    }

}

