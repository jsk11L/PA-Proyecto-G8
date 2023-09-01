import java.io.*;
import java.util.*;

public class Cliente {
    private String nombre;
    private String rut;
    private String telefono;

    private Plan planes;

    public Cliente(String nombre, String rut, String telefono) {
        this.nombre = nombre;
        this.rut = rut;
        this.telefono = telefono;
        this.planes = new Plan();
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

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean revisarPlanA(){
        return planes.tienePlanA();
    }

    public void cambiarPlanA(boolean valor){
        planes.setPlanA(valor);
    }

    public boolean revisarPlanB(){
        return planes.tienePlanB();
    }

    public void cambiarPlanB(boolean valor){
        planes.setPlanB(valor);
    }

    public boolean revisarPlanC(){
        return planes.tienePlanC();
    }

    public void cambiarPlanC(boolean valor){
        planes.setPlanC(valor);
    }

    public boolean revisarPlanD(){
        return planes.tienePlanD();
    }

    public void cambiarPlanD(boolean valor){
        planes.setPlanD(valor);
    }
}