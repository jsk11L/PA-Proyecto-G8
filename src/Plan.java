/*import java.util.*;
import java.io.*;*/
public class Plan {
    private float precio;
    private String id;
    private ArrayList<Cliente> = listaClientes;
    private HashMap<String, Cliente> = mapaClientes;

    public Plan() {
        this.precio = precio;
        this.id = id;
        listaClientes = new ArrayList<>();
        mapaClientes = new HashMap<>();
    }

    public void setId(String id){
        this.id = id;
    }
    
    public void setPrecio(float precio){
        this.precio = precio;
    }
    
    public String getId(){
        return this.id;
    }
    
    public float getPrecio(){
        return this.precio;
    }
    
}

