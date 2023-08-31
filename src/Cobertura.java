import java.io.*;
import java.util.*;

public class Cobertura {
    private String region;
    private int codigoRegion;
    private int numeroClientes;
    private List<Cliente> clientes;

    public Cobertura(String region, int codigoRegion) {
        this.region = region;
        this.codigoRegion = codigoRegion;
        this.numeroClientes = 0;
        this.clientes = new ArrayList<>();
    }

    // Getters y setters
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getCodigoRegion() {
        return codigoRegion;
    }

    public void setCodigoRegion(int codigoRegion) {
        this.codigoRegion = codigoRegion;
    }

    public int getNumeroClientes() {
        return numeroClientes;
    }

    public void setNumeroClientes(int numeroClientes) {
        this.numeroClientes = numeroClientes;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
        numeroClientes++;
    }
}