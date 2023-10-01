package controladores;
import modelos.*;
import excepciones.*;
import vista.*;
import java.io.*;
import java.util.*;

public class controladorCobertura {
    private Empresa modelo;
    private vistaCobertura vista;

    public controladorCobertura(Empresa modelo) {
        this.modelo = modelo;
        this.vista = new vistaCobertura(this);
    }

    // Agregar Cobertura
    public boolean agregarCobertura(Cobertura cobertura) {
        try{
            modelo.agregarCobertura(cobertura);
            return true;
        } catch (CoberturaYaRegistradaException e){
            return false;
        }
    }

    // Eliminar Cobertura
    public boolean eliminarCobertura(String codigoCobertura) {
        try{
            Cobertura cobertura = modelo.eliminarCobertura(codigoCobertura);
            return true;
        } catch (CoberturaNoEncontradaException e){
            return false;
        }
    }

    // Listar todas las Coberturas
    public ArrayList<Cobertura> listarCoberturas() {
        ArrayList<Cobertura> copia = modelo.getCoberturas();
        return copia;
    }
    
    public ArrayList<Cobertura> listarCoberturasTres() {
        ArrayList<Cobertura> copia = modelo.getCoberturasTres();
        return copia;
    }

    // Añadir Plan a una Cobertura específica
    public boolean agregarPlanACobertura(String codigoCobertura, Plan plan) {
        try{
            Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
            cobertura.agregarPlan(plan);
            return true;
        } catch (CoberturaNoEncontradaException e){
            return false;
        } catch (PlanYaRegistradoException e){
            return false;
        }
    }

    // Eliminar Plan de una Cobertura específica
    public boolean eliminarPlanDeCobertura(String codigoCobertura, String idPlan) {
        try{
            Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
            cobertura.eliminarPlan(idPlan);
            return true;
        } catch (CoberturaNoEncontradaException e){
            return false;
        } catch (PlanNoEncontradoException e){
            return false;
        }
    }

    // Listar todos los Planes de una Cobertura específica y cuántos suscriptores tienen
    public ArrayList<Plan> listarPlanesDeCobertura(String codigoCobertura) {
        try{
            Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
            ArrayList<Plan> planes = cobertura.getPlanes();
            return planes;
        } catch (CoberturaNoEncontradaException e){
            return null;
        }
    }
 
    // Modificar el precio de un Plan en una Cobertura específica
    public boolean modificarPrecioPlan(String codigoCobertura, String idPlan, float nuevoPrecio) {
        try{
            Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
            Plan plan = cobertura.buscarPlan(idPlan);
            plan.setPrecio(nuevoPrecio); 
            return true;
        } catch(CoberturaNoEncontradaException e){
            return false;
        } catch (PlanNoEncontradoException e){
            return false;
        }
    }

    // Modificar el ID de un Plan en una Cobertura específica
    public boolean modificarIdPlan(String codigoCobertura, String idPlanActual, String nuevoId) {
        try{
            Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
            Plan plan = cobertura.buscarPlan(idPlanActual);
            plan.setId(nuevoId); 
            return true;
        } catch(CoberturaNoEncontradaException e){
            return false;
        } catch (PlanNoEncontradoException e){
            return false;
        }
    }
    
    public Cobertura buscarCobertura(String clave){
        try{
            Cobertura cobertura = modelo.buscarCobertura(clave);
            return cobertura;
        } catch (CoberturaNoEncontradaException e){
            return null;
        }
    }
    
    public Cliente buscarClientePorIndice(String coberturaId, String planId, int indice) {
        try{
            Cobertura cobertura = buscarCobertura(coberturaId);
            Plan plan = cobertura.buscarPlan(planId);
            return plan.getClientes().get(indice);
        }catch (PlanNoEncontradoException e){
                return null;
        }
    }
    
    public void iniciarAgregar(){
        vista.mostrarVentanaAgregar();
    }
    
    public void iniciarEliminar(){
        vista.mostrarVentanaEliminar();
    }
    
    public void iniciarListar(){
        vista.mostrarVentanaListar();
    }
    
    public void iniciarListarTres(){
        vista.mostrarVentanaListarTres();
    }
    

}
