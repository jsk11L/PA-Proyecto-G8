import java.io.*;
import java.util.*;

public class Cobertura {
    private String region;
    private String codigoRegion;
    private int numeroClientes;
    private ArrayList<Cliente> listaClientes;
    private HashMap<String, Cliente> mapaClientes;

    public Cobertura(String region, String codigoRegion) {
        this.region = region;
        this.codigoRegion = codigoRegion;
        this.numeroClientes = 0;
        this.listaClientes = new ArrayList<>();
        this.mapaClientes = new HashMap<>();
    }

    // Getters y setters
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCodigoRegion() {
        return codigoRegion;
    }

    public void setCodigoRegion(String codigoRegion) {
        this.codigoRegion = codigoRegion;
    }

    public int getNumeroClientes() {
        return numeroClientes;
    }

    public void setNumeroClientes(int numeroClientes) {
        this.numeroClientes = numeroClientes;
    }

    public ArrayList<Cliente> getClientes() {
        return listaClientes;
    }

    public boolean agregarCliente(Cliente cliente) {
        if(mapaClientes.containsKey(cliente.getRut())){
            return false;
        }
        else{
            mapaClientes.put(cliente.getRut(), cliente);
            listaClientes.add(cliente);
            return true;
        }
    }

    public boolean eliminarCliente(Cliente cliente) {
        if(mapaClientes.containsKey(cliente.getRut()) == false){
            return false;
        }
        mapaClientes.remove(cliente.getRut());

        for(int i = 0; i <listaClientes.size(); i++){

            Cliente aux = listaClientes.get(i);
            if(cliente.getRut().equals(aux.getRut())){
                listaClientes.remove(i);
                break;
            }
        }

        return true;
    }

    public Cliente buscarCliente(String nombre, String rut) {
        if(mapaClientes.containsKey(rut) == false){
            return null;
        }

        Cliente aux = null;
        for(int i = 0; i < listaClientes.size(); i++) {

            aux = listaClientes.get(i);
            if (aux.getRut().equals(rut)) {
                break;
            }

        }
        return aux;
    }

    public void menuClientes(Cliente cliente) throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        int opcion, opcion2;
        while(true){
            System.out.println("=======================");
            System.out.println("  SISTEMA DE CLIENTES ");
            System.out.println("=======================");
            System.out.println("1.- Listar Planes");
            System.out.println("2.- Suscribir Plan");
            System.out.println("3.- Desuscribir Plan");
            System.out.println("4.- Salir del Sistema");
            System.out.println("");
            System.out.print("Escoge una opción: ");

            opcion = Integer.parseInt(lector.readLine());

            if(opcion == 1){
                System.out.println("=PLANES DISPONIBLES=");
                System.out.println("1.- Plan A");
                System.out.println("2.- Plan B");
                System.out.println("3.- Plan C");
                System.out.println("4.- Plan D");
            }
            if(opcion == 2){
                if(cliente.revisarPlanA() && cliente.revisarPlanB() && cliente.revisarPlanC() && cliente.revisarPlanD()){

                    System.out.println("El cliente ya ha contratado todos los planes disponibles.");

                }
                else{
                    System.out.println("PLANES NO CONTRATADOS");
                    if(cliente.revisarPlanA() == false){
                        System.out.println("1. Plan A");
                    }
                    if(cliente.revisarPlanB() == false){
                        System.out.println("2. Plan B");
                    }
                    if(cliente.revisarPlanC() == false){
                        System.out.println("3. Plan C");
                    }
                    if(cliente.revisarPlanD() == false){
                        System.out.println("4. Plan D");
                    }

                    System.out.print("Escoge una opción: ");
                    opcion2 = Integer.parseInt(lector.readLine());

                    if(opcion2 == 1){
                        cliente.cambiarPlanA(true);
                    }
                    if(opcion2 == 2){
                        cliente.cambiarPlanB(true);
                    }
                    if(opcion2 == 3){
                        cliente.cambiarPlanC(true);
                    }
                    if(opcion2 == 4){
                        cliente.cambiarPlanD(true);
                    }

                }

            }
            if(opcion == 3){
                if(cliente.revisarPlanA() || cliente.revisarPlanB() || cliente.revisarPlanC() || cliente.revisarPlanD()){
                    System.out.println("PLANES CONTRATADOS DEL CLIENTE");
                    if(cliente.revisarPlanA()){
                        System.out.println("1. Plan A");
                    }
                    if(cliente.revisarPlanB()){
                        System.out.println("2. Plan B");
                    }
                    if(cliente.revisarPlanC()){
                        System.out.println("3. Plan C");
                    }
                    if(cliente.revisarPlanD()){
                        System.out.println("4. Plan D");
                    }

                    System.out.print("Escoge una opción: ");
                    opcion2 = Integer.parseInt(lector.readLine());

                    if(opcion2 == 1){
                        cliente.cambiarPlanA(false);
                    }
                    if(opcion2 == 2){
                        cliente.cambiarPlanB(false);
                    }
                    if(opcion2 == 3){
                        cliente.cambiarPlanC(false);
                    }
                    if(opcion2 == 4){
                        cliente.cambiarPlanD(false);
                    }


                }
                else{
                    System.out.println("El cliente no ha contratado ningún plan.");
                }

            }
            if(opcion == 4){
                break;
            }


        }
    }
}