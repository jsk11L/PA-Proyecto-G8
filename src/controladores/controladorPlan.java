package controladores;
import modelos.*;
import excepciones.*;
import vista.*;
import java.util.*;

/**
 * Clase controladora para gestionar los planes en el sistema.
 */
public class controladorPlan {
    private Empresa modelo;
    private vistaPlan vista;
    private controladorCobertura cc;

    /**
     * Constructor para la clase controladorPlan.
     * 
     * @param modelo Referencia al modelo de Empresa que contiene la información del sistema.
     * @param cc Referencia al controlador de coberturas.
     */
    public controladorPlan(Empresa modelo, controladorCobertura cc) {
        this.modelo = modelo;
        this.cc = cc;
        this.vista = new vistaPlan(this, cc);
    }

    /**
     * Busca un plan específico dentro de una cobertura.
     * 
     * @param cobertura El nombre o identificador de la cobertura donde buscar el plan.
     * @param clave El identificador del plan a buscar.
     * @return El plan encontrado o null si no se encuentra.
     */
    public Plan buscarPlan(String cobertura, String clave){
        try{
            Plan plan = modelo.buscarPlan(cobertura, clave);
            return plan;
        } catch (CoberturaNoEncontradaException e){
            
        } catch(PlanNoEncontradoException e){
            
        }
        return null;
    }

    /**
     * Elimina un plan específico de una cobertura.
     * 
     * @param cobertura El nombre o identificador de la cobertura donde está el plan a eliminar.
     * @param idPlan El identificador del plan a eliminar.
     * @return true si se eliminó con éxito, false de lo contrario.
     */
    public boolean eliminarPlan(String cobertura, String idPlan){
        return modelo.eliminarPlan(cobertura, idPlan);
    }

    /**
     * Agrega un nuevo plan a una cobertura específica.
     * 
     * @param cobertura El nombre o identificador de la cobertura donde se agregará el plan.
     * @param id El identificador del nuevo plan.
     * @param precio El precio del nuevo plan.
     * @return true si el plan fue agregado con éxito, false de lo contrario.
     */
    public boolean agregarPlan(String cobertura, String id, float precio){
        Plan plan = new Plan(id, precio);
        return cc.agregarPlanACobertura(cobertura, plan);
    }

    /**
     * Lista todos los planes de una cobertura específica.
     * 
     * @param cobertura El nombre o identificador de la cobertura de la cual se listaran los planes.
     * @return Una lista con los planes de la cobertura especificada.
     */
    public ArrayList<Plan> listarPlanesPorCobertura(String cobertura){
        return cc.listarPlanesDeCobertura(cobertura);
    }
    
    /**
     * Inicia el proceso de agregar un plan mostrando la ventana correspondiente.
     */
    public void iniciarAgregar(){
        vista.mostrarVentanaAgregar();
    }
    
    /**
     * Inicia el proceso de quitar un plan mostrando la ventana correspondiente.
     */
    public void iniciarEliminar(){
        vista.mostrarVentanaEliminar();
    }
    
    /**
     * Inicia el proceso de listado de planes mostrando la ventana correspondiente.
     */
    public void iniciarListar(){
        vista.mostrarVentanaListar();
    }

}

