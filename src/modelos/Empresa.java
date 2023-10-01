package modelos;
import modelos.*;
import java.io.*;
import java.util.*;
import excepciones.*;

public class Empresa {
    private HashMap<String, Cobertura> mapaCoberturas;
    private ArrayList<Cobertura> listaCoberturas;

    public Empresa() {
        this.listaCoberturas = new ArrayList<Cobertura>();
        this.mapaCoberturas = new HashMap<String, Cobertura>();
    }

    // Métodos
    public void agregarCobertura(Cobertura cobertura) {
        if(mapaCoberturas.containsKey(id)){
            throw new CoberturaYaRegistradaException("La cobertura con ID: " + id + " ya fue registrada.");
        }
        else{
            mapaCoberturas.put(cobertura.getCodigoRegion(), cobertura);
            listaCoberturas.add(cobertura);
            return true;
        }
    }

    public Cobertura eliminarCobertura(String id) {
        if(!mapaCoberturas.containsKey(id)){
            throw new CoberturaNoEncontradaException("La cobertura con ID: " + id + " no fue encontrada.");
        }
        Cobertura aux = mapaCoberturas.remove(id);
        
        for(int i = 0; i < listaCoberturas.size(); i++){

            Cobertura aux2 = listaCoberturas.get(i);
            if(id.equals(aux.getCodigoRegion())){
                    listaCoberturas.remove(i);
                    break;
            }

        }
        return aux;
    }
    
    public Cobertura eliminarCobertura(String codigo) {
        if(!mapaCoberturas.containsKey(codigo)){
            throw new CoberturaNoEncontradaException("El plan con ID: " + id + " no fue encontrado.");
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
            throw new CoberturaNoEncontradaException("La cobertura con ID: " + id + " no fue encontrada.");
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
    
    public Plan buscarPlan(Cobertura cobertura, String clave){
        Plan plan = cobertura.buscarPlan(clave);
        if(plan != null) return plan;
        throw new PlanNoEncontradoException("El plan con ID: " + id + " no fue encontrado.");
    }

}