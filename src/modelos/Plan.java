package modelos;

import modelos.Cliente;
import java.util.*;
import java.io.*;
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
    
    public boolean tieneCliente(Cliente cliente) {
        return mapaClientes.containsKey(cliente.getRut()); 
    }

    // Método para agregar un Cliente
    public void agregarCliente(Cliente cliente) {
        if(!tieneCliente(cliente)) {
            listaClientes.add(cliente);
            mapaClientes.put(cliente.getRut(), cliente);
        }
    }

    // Método para eliminar un Cliente
    public boolean eliminarCliente(Cliente cliente) {
        Cliente clienteEliminado = mapaClientes.remove(cliente.getRut());
        if(clienteEliminado != null) {
            listaClientes.remove(clienteEliminado);
            return true;
        }
        return false;
    }

    public int numeroDeClientes() {
        return listaClientes.size();
    }
    
}

