package modelos;

import excepciones.*;
import java.util.*;

/**
 * Representa una cobertura regional de servicios con múltiples planes.
 */
public class Cobertura {
    private String region;
    private String codigoRegion;
    private int cantPlanes;
    private ArrayList<Plan> listaPlanes;
    private HashMap<String, Plan> mapaPlanes;

    /**
     * Construye un nuevo objeto Cobertura con la región y código de región especificados.
     *
     * @param region El nombre de la región.
     * @param codigoRegion El código único que identifica la región.
     */
    public Cobertura(String region, String codigoRegion) {
        this.region = region;
        this.codigoRegion = codigoRegion;
        this.cantPlanes = 0;
        this.listaPlanes = new ArrayList<>();
        this.mapaPlanes = new HashMap<>();
    }

    /**
     * Devuelve el nombre de la región de la cobertura.
     *
     * @return El nombre de la región.
     */
    public String getRegion() {
        return region;
    }
        
    /**
     * Establece el nombre de la región de la cobertura.
     *
     * @param region El nuevo nombre de la región.
     */
    public void setRegion(String region) {
        this.region = region;
    }
    
    /**
     * Devuelve el código de región de la cobertura.
     *
     * @return El código de la región.
     */
    public String getCodigoRegion() {
        return codigoRegion;
    }
    
    /**
     * Establece el código de región de la cobertura.
     *
     * @param codigoRegion El nuevo código de la región.
     */
    public void setCodigoRegion(String codigoRegion) {
        this.codigoRegion = codigoRegion;
    }

    /**
     * Devuelve la cantidad de planes disponibles en la cobertura.
     *
     * @return El número de planes.
     */
    public int getCantPlanes() {
        return cantPlanes;
    }

    /**
     * Establece la cantidad de planes de la cobertura.
     *
     * @param cantPlanes La nueva cantidad de planes en la cobertura.
     */
    public void setCantPlanes(int cantPlanes) {
        this.cantPlanes = cantPlanes;
    }
    
    /**
     * Agrega un nuevo plan a la cobertura.
     *
     * @param plan El plan a agregar.
     * @throws PlanYaRegistradoException si el plan ya fue registrado anteriormente.
     */
    public void agregarPlan(Plan plan) throws PlanYaRegistradoException{
        if(mapaPlanes.containsKey(plan.getId() ) == false) { // Verifica que el plan no esté ya registrado
            listaPlanes.add(plan);
            mapaPlanes.put(plan.getId(), plan);
            this.cantPlanes++;
        }else{
            throw new PlanYaRegistradoException("El plan con ID: " + plan.getId() + " ya fue registrado.");        
        }
    }

    /**
     * Elimina un plan especificado por su ID.
     *
     * @param idPlan El ID del plan a eliminar.
     * @throws PlanNoEncontradoException si no se encuentra un plan con el ID especificado.
     */
    public void eliminarPlan(String idPlan) throws PlanNoEncontradoException{
        if(mapaPlanes.containsKey(idPlan) == true) { // Verifica que el plan no esté ya registrado
            Plan aux = mapaPlanes.remove(idPlan); 
            listaPlanes.remove(aux);
        }else{
            throw new PlanNoEncontradoException("El plan con ID: " + idPlan + " no fue encontrado.");       
        }
    }

    /**
     * Devuelve una lista con todos los planes disponibles en la cobertura.
     *
     * @return Una lista de objetos Plan.
     */
    public ArrayList<Plan> getPlanes() {
        ArrayList<Plan> copia = new ArrayList<>();
        
        for(int i = 0; i < listaPlanes.size(); i++){
            Plan aux = listaPlanes.get(i);
            copia.add(aux);
        }
        
        return copia;
    }

    /**
     * Calcula y devuelve el número total de clientes en todos los planes de la cobertura.
     *
     * @return El total de clientes en todos los planes.
     */
    public int numeroDeClientes() {
        int totalClientes = 0;
        for(int i = 0; i < listaPlanes.size(); i++){
            Plan aux = listaPlanes.get(i);
            totalClientes += aux.numeroDeClientes();
        }
        return totalClientes;
    }

     /**
     * Busca y devuelve un plan especificado por su ID.
     *
     * @param idPlan El ID del plan a buscar.
     * @return El objeto Plan si se encuentra.
     * @throws PlanNoEncontradoException si no se encuentra un plan con el ID especificado.
     */
    public Plan buscarPlan(String idPlan) throws PlanNoEncontradoException{
        if(mapaPlanes.containsKey(idPlan) == true) {
            return mapaPlanes.get(idPlan);
        }else{
            throw new PlanNoEncontradoException("El plan con ID: " + idPlan + " no fue encontrado.");       
        }
    }
    
}