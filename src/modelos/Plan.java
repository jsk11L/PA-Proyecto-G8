package modelos;

import java.util.*;
import excepciones.*;

/**
 * Representa un plan de servicios con múltiples clientes asociados.
 */
public class Plan {
    private String id;
    private float precio;
    private ArrayList<Cliente> listaClientes;
    private HashMap<String, Cliente> mapaClientes; 

    /**
     * Construye un nuevo objeto Plan con el ID y precio especificados.
     *
     * @param id El identificador único del plan.
     * @param precio El precio del plan.
     */
    public Plan(String id, float precio) {
        this.id = id;
        this.precio = precio;
        this.listaClientes = new ArrayList<>();
        this.mapaClientes = new HashMap<>();
    }
    
    /**
     * Establece el precio del plan.
     *
     * @param precio El nuevo precio del plan.
     */
    public void setPrecio(float precio){
        this.precio = precio;
    }
    
    /**
     * Establece el ID del plan.
     *
     * @param id El nuevo identificador del plan.
     */
    public void setId(String id){
        this.id = id;
    }
    
    /**
     * Devuelve el precio del plan.
     *
     * @return El precio del plan.
     */
    public float getPrecio(){
        return this.precio;
    }
    
    /**
     * Devuelve el ID del plan.
     *
     * @return El identificador del plan.
     */
    public String getId(){
        return this.id;
    }
    
    /**
     * Devuelve una lista con todos los clientes asociados al plan.
     *
     * @return Una lista de objetos Cliente.
     */
    public ArrayList<Cliente> getClientes(){
        ArrayList<Cliente> copia = new ArrayList<>();
        for(int i = 0; i < listaClientes.size(); i++){
            Cliente aux = listaClientes.get(i);
            copia.add(aux);
        }
        
        return copia;
    }
    
    /**
     * Verifica si un cliente específico ya está registrado en el plan.
     *
     * @param cliente El cliente a verificar.
     * @return true si el cliente ya está registrado, de lo contrario false.
     */
    public boolean tieneCliente(Cliente cliente) {
        return mapaClientes.containsKey(cliente.getRut()); 
    }

    /**
     * Agrega un nuevo cliente al plan.
     *
     * @param cliente El cliente a agregar.
     * @throws ClienteYaRegistradoException si el cliente ya está registrado en el plan.
     */
    public void agregarCliente(Cliente cliente) throws ClienteYaRegistradoException {
        if(!mapaClientes.containsKey(cliente.getRut())) {
            listaClientes.add(cliente);
            mapaClientes.put(cliente.getRut(), cliente);
        } else {
            throw new ClienteYaRegistradoException("El cliente con rut: " + id + " ya fue registrado.");
        }
    }

    /**
     * Elimina un cliente del plan.
     *
     * @param cliente El cliente a eliminar.
     * @throws ClienteNoEncontradoException si el cliente no está registrado en el plan.
     */
    public void eliminarCliente(Cliente cliente) throws ClienteNoEncontradoException{
        Cliente clienteEliminado = mapaClientes.remove(cliente.getRut());
        if(clienteEliminado != null) {
            listaClientes.remove(clienteEliminado);
        }
        else{
            throw new ClienteNoEncontradoException("El cliente con rut: " + id + " no fue encontrado.");
        }
    }

    /**
     * Calcula y devuelve el número total de clientes asociados al plan.
     *
     * @return El total de clientes en el plan.
     */
    public int numeroDeClientes() {
        return listaClientes.size();
    }
    
    /**
     * Busca y devuelve un cliente especifico por su rut.
     *
     * @param rut El cliente a ser buscado
     * @return el Cliente buscado si es que existe
     * @throws ClienteYaRegistradoException si ya se encuentra registrado este Cliente
     */
    public Cliente buscarCliente(String rut) throws ClienteYaRegistradoException{
        if(mapaClientes.containsKey(rut)){
            return mapaClientes.get(rut);
        }
        throw new ClienteYaRegistradoException("El cliente de rut " + rut + " ya está suscrito a este plan!.");
    }
    
    /**
     * Indica si un cliente especifico existe en un plan por su rut, sirve para la carga de archivos CSV.
     *
     * @param cliente El cliente a ser buscado
     * @return true si se encuentra, false si no.
     */
    public boolean buscarCliente(Cliente cliente){
        if(mapaClientes.containsKey(cliente.getRut())) return true;
        return false;
    }
    
}

