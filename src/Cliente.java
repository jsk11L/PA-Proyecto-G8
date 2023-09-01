import java.io.*;
import java.util.*;

public class Cliente {
    private String nombre;
    private String rut;
    private String telefono;

    private ArrayList<String> planesContratados;

    public Cliente(String nombre, String rut, String telefono) {
        this.nombre = nombre;
        this.rut = rut;
        this.telefono = telefono;
        this.planesContratados = new ArrayList<>();
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getTelefono() {
        return telefono;
    }

    public ArrayList<String> getPlanesContratados() {
        return planesContratados;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void agregarPlan(String nombrePlan){
        if(planesContratados.size() >= 10){
            System.out.print("No se pueden agregar más planes");
        }
        else{
            planesContratados.add(nombrePlan);
        }
    }

    public void agregarPlan(String nombrePlan, String planAReemplazar){
        for(int i = 0 ; i < planesContratados.size() ; i++){
            if(planesContratados.get(i).equals(planAReemplazar)){
                planesContratados.set(i, nombrePlan);
            }
        }
    }

    public void eliminarPlan(String nombrePlan) {
        planesContratados.removeIf(plan -> plan.equals(nombrePlan));
    }

    public void eliminarPlan(int indice) {
        if (indice >= 0 && indice < planesContratados.size()) {
            planesContratados.remove(indice);
        }
        else {
            System.out.println("Índice fuera de rango.");
        }
    }
}