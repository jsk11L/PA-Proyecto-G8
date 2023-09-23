import java.io.*;
import java.util.*;

public class Empresa {
    private HashMap<String, Cobertura> mapaCoberturas;
    private ArrayList<Cobertura> listaCoberturas;

    public Empresa() {
        this.listaCoberturas = new ArrayList<>();
        this.mapaCoberturas = new HashMap<>();
    }

    // Métodos



    public boolean agregarCobertura(Cobertura cobertura) {
        if(mapaCoberturas.containsKey(cobertura.getCodigoRegion())){
            return false;
        }
        else{
            mapaCoberturas.put(cobertura.getCodigoRegion(), cobertura);
            listaCoberturas.add(cobertura);
            return true;
        }
    }

    public boolean eliminarCobertura(Cobertura cobertura) {
        if(!mapaCoberturas.containsKey(cobertura.getCodigoRegion())){
            return false;
        }
        mapaCoberturas.remove(cobertura.getCodigoRegion());

        for(int i = 0; i < listaCoberturas.size(); i++){

            Cobertura aux = listaCoberturas.get(i);
            if(cobertura.getCodigoRegion().equals(aux.getCodigoRegion())){
                    listaCoberturas.remove(i);
                    break;
            }

        }

        return true;
    }

   /* public ArrayList<Cobertura> getCoberturas() {
        return listaCoberturas;
    }
*/
    public ArrayList<Cobertura> getCoberturasMasDensas() {
        ArrayList<Cobertura> copiaCoberturas = new ArrayList<>(listaCoberturas);

        Collections.sort(copiaCoberturas, Comparator.comparingInt(Cobertura::getNumeroClientes).reversed());

        ArrayList<Cobertura> tresPrimerasCoberturas = new ArrayList<>();
        int limite = Math.min(3, copiaCoberturas.size());
        for (int i = 0; i < limite; i++) {
            tresPrimerasCoberturas.add(copiaCoberturas.get(i));
        }

        return tresPrimerasCoberturas;
    }

    public ArrayList<Cobertura> getCoberturasMenosDensas() {
        ArrayList<Cobertura> copiaCoberturas = new ArrayList<>(listaCoberturas);

        Collections.sort(copiaCoberturas, Comparator.comparingInt(Cobertura::getNumeroClientes));

        ArrayList<Cobertura> tresUltimasCoberturas = new ArrayList<>();
        int limite = Math.min(3, copiaCoberturas.size());
        for (int i = 0; i < limite; i++) {
            tresUltimasCoberturas.add(copiaCoberturas.get(i));
        }

        return tresUltimasCoberturas;
    }

    public Cobertura buscarCobertura(String nombre, String codigo) {
        if(!mapaCoberturas.containsKey(codigo)){
            return null;
        }

        Cobertura aux = null;
        for(int i = 0; i < listaCoberturas.size(); i++){
            aux = listaCoberturas.get(i);
            if(codigo.equals(aux.getCodigoRegion())) {
                break;
            }

        }
        return aux;
    }

    public Cobertura buscarCobertura(String codigo) {
        if(!mapaCoberturas.containsKey(codigo)){
            return null;
        }

        Cobertura aux = null;
        for(int i = 0; i < listaCoberturas.size(); i++){
            aux = listaCoberturas.get(i);
            if(codigo.equals(aux.getCodigoRegion())) {
                break;
            }

        }
        return aux;
    }

    public void menuCoberturas(Cobertura cobertura) throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        int opcion;
        while(true){
            System.out.println("=======================");
            System.out.println("  SISTEMA DE COBERTURA ");
            System.out.println("=======================");
            System.out.println("1.- Suscribir Cliente");
            System.out.println("2.- Desuscribir Cliente");
            System.out.println("3.- Listar Clientes");
            System.out.println("4.- Gestionar Cliente");
            System.out.println("5.- Salir del Sistema");
            System.out.println("");
            System.out.print("Escoge una opción: ");

            opcion = Integer.parseInt(lector.readLine());

            if(opcion == 1){
                System.out.print("Ingresa el nombre del cliente: ");
                String nombre = lector.readLine();
                System.out.print("Ingresa el rut del cliente: ");
                String rut = lector.readLine();
                System.out.print("Ingresa el teléfono del cliente: ");
                String fono = lector.readLine();

                Cliente aux = new Cliente(nombre, rut, fono);

                if(cobertura.agregarCliente(aux)){
                    System.out.println("Se agregó el cliente correctamente.");
                }
                else{
                    System.out.println("Ya existe un cliente con ese rut.");
                }
            }
            if(opcion == 2){
                System.out.print("Ingresa el nombre del cliente: ");
                String nombre = lector.readLine();
                System.out.print("Ingresa el rut del cliente: ");
                String rut = lector.readLine();
                System.out.print("Ingresa el teléfono del cliente: ");
                String fono = lector.readLine();

                Cliente aux = new Cliente(nombre, rut, fono);

                if(cobertura.eliminarCliente(aux)){
                    System.out.println("Se eliminó el cliente correctamente.");
                }
                else{
                    System.out.println("No se encontró un cliente con ese rut.");
                }
            }
            if(opcion == 3){
                ArrayList<Cliente> lista = cobertura.getClientes();
                for(int i = 0; i < lista.size(); i++){
                    Cliente aux = lista.get(i);
                    System.out.println("====="+ (i+1)+"=====");
                    System.out.println("Nombre del Cliente: "+ aux.getNombre());
                    System.out.println("Rut del Cliente: "+ aux.getRut());
                    System.out.println("Teléfono del Cliente: "+ aux.getTelefono());

                    if(aux.revisarPlanA() || aux.revisarPlanB() || aux.revisarPlanC() || aux.revisarPlanD()){
                        System.out.println("PLANES CONTRATADOS");
                        int cont = 1;
                        if(aux.revisarPlanA()){
                            System.out.println(cont +". Plan A");
                            cont++;
                        }
                        if(aux.revisarPlanB()){
                            System.out.println(cont+". Plan B");
                            cont++;
                        }
                        if(aux.revisarPlanC()){
                            System.out.println(cont+". Plan C");
                            cont++;
                        }
                        if(aux.revisarPlanD()){
                            System.out.println(cont+". Plan D");
                        }

                    }
                    else{
                        System.out.println("El cliente no ha contratado ningún plan.");
                    }


                }
            }
            if(opcion == 4){
                System.out.print("Ingresa el nombre del cliente: ");
                String nombre = lector.readLine();
                System.out.print("Ingresa el rut del cliente: ");
                String rut = lector.readLine();

                Cliente aux = cobertura.buscarCliente(nombre, rut);
                if(aux != null){
                    cobertura.menuClientes(aux);
                }
                else{
                    System.out.println("No se encontró el cliente buscado.");
                }
            }
            if(opcion == 5){
                break;
            }

        }
        System.out.println("Volviendo al menú principal...");
    }

}