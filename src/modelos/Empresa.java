package modelos;
import java.util.*;
import excepciones.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Modelo que gestiona toda la aplicación.
 */
public class Empresa {
    private HashMap<String, Cobertura> mapaCoberturas;
    private ArrayList<Cobertura> listaCoberturas;

    public Empresa() {
        this.listaCoberturas = new ArrayList<>();
        this.mapaCoberturas = new HashMap<>();
    }

    /**
    * Agrega una nueva cobertura al sistema.
    *
    * @param cobertura La cobertura que se va a agregar.
    * @throws CoberturaYaRegistradaException Si la cobertura ya ha sido registrada anteriormente.
    */
    public void agregarCobertura(Cobertura cobertura)throws CoberturaYaRegistradaException {
        if(mapaCoberturas.containsKey(cobertura.getCodigoRegion())){
            throw new CoberturaYaRegistradaException("La cobertura con ID: " + cobertura.getCodigoRegion() + " ya fue registrada.");
        }
        else{
            mapaCoberturas.put(cobertura.getCodigoRegion(), cobertura);
            listaCoberturas.add(cobertura);
        }
    }
    
    /**
    * Elimina una cobertura especificada por su ID.
    *
    * @param id El ID de la cobertura a eliminar.
    * @return La cobertura que ha sido eliminada.
    * @throws CoberturaNoEncontradaException Si no se encuentra una cobertura con el ID proporcionado.
    */
    public Cobertura eliminarCobertura(String id) throws CoberturaNoEncontradaException {
        if(!mapaCoberturas.containsKey(id)){
            throw new CoberturaNoEncontradaException("La cobertura con ID: " + id + " no fue encontrada.");
        }
        Cobertura aux = mapaCoberturas.remove(id);
        
        for(int i = 0; i < listaCoberturas.size(); i++){

            Cobertura aux2 = listaCoberturas.get(i);
            if(id.equals(aux2.getCodigoRegion())){
                    listaCoberturas.remove(i);
                    break;
            }

        }
        return aux;
    }
    
    /**
    * Busca una cobertura específica por su código.
    *
    * @param codigo El código de la cobertura que se desea buscar.
    * @return La cobertura encontrada.
    * @throws CoberturaNoEncontradaException Si no se encuentra una cobertura con el código proporcionado.
    */
    public Cobertura buscarCobertura(String codigo) throws CoberturaNoEncontradaException{
        if(!mapaCoberturas.containsKey(codigo)){
            throw new CoberturaNoEncontradaException("La cobertura con ID: " + codigo + " no fue encontrada.");
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
    
    /**
    * Devuelve una lista de todas las coberturas registradas en el sistema.
    *
    * @return Una lista de coberturas.
    */
    public ArrayList<Cobertura> getCoberturas(){
        ArrayList<Cobertura> copia = new ArrayList<>();
        
        for(int i = 0; i < listaCoberturas.size(); i++){
            Cobertura aux = listaCoberturas.get(i);
            copia.add(aux);
        }
        
        return copia;
    }
    
    /**
    * Devuelve una lista de las tres coberturas con menos clientes registrados.
    *
    * @return Una lista de las tres coberturas con menos clientes.
    */
    public ArrayList<Cobertura> getCoberturasTres() {
        ArrayList<Cobertura> copia = new ArrayList<>();
        
        for(int i = 0; i < listaCoberturas.size(); i++){
            Cobertura aux = listaCoberturas.get(i);
            copia.add(aux);
        }
        
        return copia.stream().sorted(Comparator.comparingInt(Cobertura::numeroDeClientes)).limit(3).collect(Collectors.toCollection(ArrayList::new));
    }
    
    /**
    * Busca un plan específico dentro de una cobertura determinada.
    *
    * @param codigo El código de la cobertura donde se buscará el plan.
    * @param clave La clave del plan a buscar.
    * @return El plan encontrado.
    * @throws PlanNoEncontradoException Si no se encuentra un plan con la clave proporcionada.
    * @throws CoberturaNoEncontradaException Si no se encuentra una cobertura con el código proporcionado.
    */
    public Plan buscarPlan(String codigo, String clave) throws PlanNoEncontradoException, CoberturaNoEncontradaException{
        Cobertura cobertura = buscarCobertura(codigo);
        Plan plan = cobertura.buscarPlan(clave);
        if(plan != null) {
            return plan;
        }
        throw new PlanNoEncontradoException("El plan con ID: " + clave + " no fue encontrado.");
    }
    
    /**
    * Elimina un plan específico dentro de una cobertura determinada.
    *
    * @param codigoCobertura El código de la cobertura donde se eliminará el plan.
    * @param idPlan El ID del plan a eliminar.
    * @return true si el plan se eliminó con éxito, false en caso contrario.
    */
    public boolean eliminarPlan(String codigoCobertura, String idPlan){
        try{
            Cobertura cobertura = buscarCobertura(codigoCobertura);
            cobertura.eliminarPlan(idPlan);
            return true;
        }catch(PlanNoEncontradoException e){
            return false;
        }catch(CoberturaNoEncontradaException e){
            return false;
        }
    }
    
}