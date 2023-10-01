package controladores;

import modelos.*;
import excepciones.*;
import vista.*;
import java.util.*;

/**
 * Controlador para la gestión de coberturas y sus operaciones asociadas.
 */
public class controladorCobertura {
    private Empresa modelo;
    private vistaCobertura vista;

    /**
     * Constructor del controlador de coberturas.
     *
     * @param modelo El modelo de la empresa.
     */
    public controladorCobertura(Empresa modelo) {
        this.modelo = modelo;
        this.vista = new vistaCobertura(this);
    }

    /**
     * Agrega una nueva cobertura.
     *
     * @param cobertura La cobertura a agregar.
     * @return true si la cobertura fue agregada con éxito, false de lo contrario.
     */
    public boolean agregarCobertura(Cobertura cobertura) {
        try{
            modelo.agregarCobertura(cobertura);
            return true;
        } catch (CoberturaYaRegistradaException e){
            return false;
        }
    }

    /**
     * Elimina una cobertura.
     *
     * @param codigoCobertura El código de la cobertura a eliminar.
     * @return true si la cobertura fue eliminada con éxito, false de lo contrario.
     */
    public boolean eliminarCobertura(String codigoCobertura) {
        try{
            modelo.eliminarCobertura(codigoCobertura);
            return true;
        } catch (CoberturaNoEncontradaException e){
            return false;
        }
    }

    /**
     * Lista todas las coberturas.
     *
     * @return Una lista con todas las coberturas.
     */
    public ArrayList<Cobertura> listarCoberturas() {
        ArrayList<Cobertura> copia = modelo.getCoberturas();
        return copia;
    }
    
    /**
     * Lista las primeras tres coberturas.
     *
     * @return Una lista con las tres primeras coberturas.
     */
    public ArrayList<Cobertura> listarCoberturasTres() {
        ArrayList<Cobertura> copia = modelo.getCoberturasTres();
        return copia;
    }

    /**
     * Añade un plan a una cobertura específica.
     *
     * @param codigoCobertura El código de la cobertura a la que se agregará el plan.
     * @param plan El plan a agregar.
     * @return true si el plan fue añadido con éxito, false de lo contrario.
     */
    public boolean agregarPlanACobertura(String codigoCobertura, Plan plan) {
        try{
            Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
            cobertura.agregarPlan(plan);
            return true;
        } catch (CoberturaNoEncontradaException | PlanYaRegistradoException e){
            return false;
        }
    }

    /**
     * Elimina un plan de una cobertura específica.
     *
     * @param codigoCobertura El código de la cobertura del plan a eliminar.
     * @param idPlan El ID del plan a eliminar.
     * @return true si el plan fue eliminado con éxito, false de lo contrario.
     */
    public boolean eliminarPlanDeCobertura(String codigoCobertura, String idPlan) {
        try{
            Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
            cobertura.eliminarPlan(idPlan);
            return true;
        } catch (CoberturaNoEncontradaException | PlanNoEncontradoException e){
            return false;
        }
    }

    /**
     * Lista todos los planes de una cobertura específica.
     *
     * @param codigoCobertura El código de la cobertura.
     * @return Una lista de planes de la cobertura especificada, null si no se encuentra la cobertura.
     */
    public ArrayList<Plan> listarPlanesDeCobertura(String codigoCobertura) {
        try{
            Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
            ArrayList<Plan> planes = cobertura.getPlanes();
            return planes;
        } catch (CoberturaNoEncontradaException e){
            return null;
        }
    }
    
    /**
     * Busca y devuelve una cobertura basándose en una clave.
     *
     * @param clave La clave o código de la cobertura a buscar.
     * @return La cobertura encontrada. Retorna null si no se encuentra la cobertura.
     */
    public Cobertura buscarCobertura(String clave){
        try{
            Cobertura cobertura = modelo.buscarCobertura(clave);
            return cobertura;
        } catch (CoberturaNoEncontradaException e){
            return null;
        }
    }

    /**
     * Busca y devuelve un cliente por su índice dentro de una cobertura y plan específicos.
     *
     * @param coberturaId El ID de la cobertura.
     * @param planId El ID del plan.
     * @param indice El índice del cliente dentro del plan.
     * @return El cliente encontrado. Retorna null si no se encuentra.
     */
    public Cliente buscarClientePorIndice(String coberturaId, String planId, int indice) {
        try{
            Cobertura cobertura = buscarCobertura(coberturaId);
            Plan plan = cobertura.buscarPlan(planId);
            return plan.getClientes().get(indice);
        }catch (PlanNoEncontradoException e){
            return null;
        }
    }

    /**
     * Inicia el proceso de agregación mostrando la ventana correspondiente.
     */
    public void iniciarAgregar(){
        vista.mostrarVentanaAgregar();
    }

    /**
     * Inicia el proceso de eliminación mostrando la ventana correspondiente.
     */
    public void iniciarEliminar(){
        vista.mostrarVentanaEliminar();
    }

    /**
     * Inicia el proceso de listado mostrando la ventana correspondiente.
     */
    public void iniciarListar(){
        vista.mostrarVentanaListar();
    }
    
    /**
     * Inicia el proceso de listado de las tres coberturas con menos clientes mostrando la ventana correspondiente.
     */
    public void iniciarListarTres(){
        vista.mostrarVentanaListarTres();
    }

}
