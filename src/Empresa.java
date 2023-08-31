import java.io.*;
import java.util.*;

public class Empresa {
    private String nombre;
    private List<Cobertura> coberturas;

    public Empresa(String nombre) {
        this.nombre = nombre;
        this.coberturas = new ArrayList<>();
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cobertura> getCoberturas() {
        return coberturas;
    }

    public void agregarCobertura(Cobertura cobertura) {
        coberturas.add(cobertura);
    }
}