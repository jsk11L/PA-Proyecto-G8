package modelos;
import modelos.*;
import java.io.*;
import java.util.*;

public class Empresa {
    private HashMap<String, Cobertura> mapaCoberturas;
    private ArrayList<Cobertura> listaCoberturas;

    public Empresa() {
        this.listaCoberturas = new ArrayList<Cobertura>();
        this.mapaCoberturas = new HashMap<String, Cobertura>();
    }

    // Métodos
    public boolean agregarCobertura(Cobertura cobertura) {
        if(mapaCoberturas.containsKey(cobertura.getCodigoRegion())){
            return false;
        }
        else{
            mapaCoberturas.put(cobertura.getCodigoRegion(), cobertura);
            listaCoberturas.add(cobertura);
            return true;
        }
    }

    public Cobertura eliminarCobertura(Cobertura cobertura) {
        if(!mapaCoberturas.containsKey(cobertura.getCodigoRegion())){
            return null;
        }
        Cobertura aux = mapaCoberturas.remove(cobertura.getCodigoRegion());
        
        for(int i = 0; i < listaCoberturas.size(); i++){

            Cobertura aux2 = listaCoberturas.get(i);
            if(cobertura.getCodigoRegion().equals(aux.getCodigoRegion())){
                    listaCoberturas.remove(i);
                    break;
            }

        }

        return aux;
    }
    
    public Cobertura eliminarCobertura(String codigo) {
        if(!mapaCoberturas.containsKey(codigo)){
            return null;
        }
        Cobertura aux = mapaCoberturas.remove(codigo);

        for(int i = 0; i < listaCoberturas.size(); i++){

            Cobertura aux2 = listaCoberturas.get(i);
            if(codigo.equals(aux.getCodigoRegion())){
                    listaCoberturas.remove(i);
                    break;
            }

        }

        return aux;
    }

    public Cobertura buscarCobertura(String codigo) {
        if(!mapaCoberturas.containsKey(codigo)){
            return null;
        }

        Cobertura aux = null;
        for(int i = 0; i < listaCoberturas.size(); i++){
            aux = listaCoberturas.get(i);
            if(codigo.equals(aux.getCodigoRegion())) {
                break;
            }

        }
        return aux;
    }
    
    public ArrayList<Cobertura> getCoberturas(){
        ArrayList<Cobertura> copia = new ArrayList<>();
        
        for(int i = 0; i < listaCoberturas.size(); i++){
            Cobertura aux = listaCoberturas.get(i);
            copia.add(aux);
        }
        
        return copia;
    }

}