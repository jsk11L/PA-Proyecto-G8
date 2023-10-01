package modelos;

import java.io.*;
import java.util.*;

public class Cobertura {
    private String region;
    private String codigoRegion;
    private int cantPlanes;
    private ArrayList<Plan> listaPlanes;
    private HashMap<String, Plan> mapaPlanes;

    public Cobertura(String region, String codigoRegion) {
        this.region = region;
        this.codigoRegion = codigoRegion;
        this.cantPlanes = 0;
        this.listaPlanes = new ArrayList<>();
        this.mapaPlanes = new HashMap<>();
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

    public int getCantPlanes() {
        return cantPlanes;
    }

    public void setCantPlanes(int cantPlanes) {
        this.cantPlanes = cantPlanes;
    }
    
    // Método para agregar un Plan
    public void agregarPlan(Plan plan) {
        if(!mapaPlanes.containsKey(plan.getId())) { // Verifica que el plan no esté ya registrado
            listaPlanes.add(plan);
            mapaPlanes.put(plan.getId(), plan);
        }
    }

    // Método para eliminar un Plan por ID
    public boolean eliminarPlan(String idPlan) {
        Plan plan = mapaPlanes.remove(idPlan); 
        if(plan != null) {
            listaPlanes.remove(plan);
            return true; 
        }
        return false; 
    }

    // Método para obtener la lista de Planes
    public ArrayList<Plan> getPlanes() {
        ArrayList<Plan> copia = new ArrayList<>();
        
        for(int i = 0; i < listaPlanes.size(); i++){
            Plan aux = listaPlanes.get(i);
            copia.add(aux);
        }
        
        return copia;
    }

    // Método para obtener el número total de clientes en todos los planes
    public int numeroDeClientes() {
        int totalClientes = 0;
        for(int i = 0; i < listaPlanes.size(); i++){
            Plan aux = listaPlanes.get(i);
            totalClientes += aux.numeroDeClientes();
        }
        return totalClientes;
    }

    // Método para buscar un Plan por ID
    public Plan buscarPlan(String idPlan) {
        return mapaPlanes.get(idPlan);
    }
    
}