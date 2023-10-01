package modelos;

import modelos.Cliente;
import java.util.*;
import java.io.*;
import excepciones.*;

public class Plan {
    private String id;
    private float precio;
    private ArrayList<Cliente> listaClientes;
    private HashMap<String, Cliente> mapaClientes; 

    public Plan(String id, float precio) {
        this.id = id;
        this.precio = precio;
        this.listaClientes = new ArrayList<>();
        this.mapaClientes = new HashMap<>();
    }
    
    public void setPrecio(float precio){
        this.precio = precio;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public float getPrecio(){
        return this.precio;
    }
    
    public String getId(){
        return this.id;
    }
    
    public ArrayList<Cliente> getClientes(){
        ArrayList<Cliente> copia = new ArrayList<>();
        for(int i = 0; i < listaClientes.size(); i++){
            Cliente aux = listaClientes.get(i);
            copia.add(aux);
        }
        
        return copia;
    }
    
    public boolean tieneCliente(Cliente cliente) {
        return mapaClientes.containsKey(cliente.getRut()); 
    }

    // Método para agregar un Cliente
    public void agregarCliente(Cliente cliente) throws ClienteYaRegistradoException {
        if(!mapaClientes.containsKey(cliente.getRut())) {
            listaClientes.add(cliente);
            mapaClientes.put(cliente.getRut(), cliente);
        } else {
            throw new ClienteYaRegistradoException("El cliente con rut: " + id + " ya fue registrado.");
        }
    }

    // Método para eliminar un Cliente
    public void eliminarCliente(Cliente cliente) throws ClienteNoEncontradoException{
        Cliente clienteEliminado = mapaClientes.remove(cliente.getRut());
        if(clienteEliminado != null) {
            listaClientes.remove(clienteEliminado);
        }
        else{
            throw new ClienteNoEncontradoException("El cliente con rut: " + id + " no fue encontrado.");
        }
    }

    public int numeroDeClientes() {
        return listaClientes.size();
    }
    
}

