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
        listaCoberturas.add(cobertura);
    }

    public boolean eliminarCobertura(Cobertura cobertura) {
        listaCoberturas.add(cobertura);
    }

    public ArrayList<Cobertura> getCoberturas() {
        return listaCoberturas;
    }
}