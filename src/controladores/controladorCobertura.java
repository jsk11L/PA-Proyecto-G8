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
        this.vista = new vistaCobertura();
    }

    // Agregar Cobertura
    public void agregarCobertura(Cobertura cobertura) {
        try{
            modelo.agregarCobertura(cobertura);
        } catch (CoberturaYaRegistradaException e){
            
        }
    }

    // Eliminar Cobertura
    public void eliminarCobertura(String codigoCobertura) {
        try{
            Cobertura cobertura = modelo.eliminarCobertura(codigoCobertura);
        } catch (CoberturaNoEncontradaException e){
            
        }
    }

    // Listar todas las Coberturas
    public void listarCoberturas() {
        ArrayList<Cobertura> coberturas = modelo.getCoberturas();
        //vista.mostrarListaCoberturas(coberturas);
    }

    // Mostrar las 3 Coberturas con menos clientes
    public void mostrarTresCoberturasConMenosClientes() {
        ArrayList<Cobertura> coberturas = modelo.getCoberturas();
        //coberturas.sort(Comparator.comparingInt(Cobertura::numeroDeClientes)); // Asumiendo que tienes un método numeroDeClientes() en la clase Cobertura
        //ArrayList<Cobertura> top3 = coberturas.subList(0, Math.min(coberturas.size(), 3)); // Tomar los primeros 3 o menos si no hay 3
        //vista.mostrarListaCoberturas(top3);
    }

    // Añadir Plan a una Cobertura específica
    public void agregarPlanACobertura(String codigoCobertura, Plan plan) {
        try{
            Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
            cobertura.agregarPlan(plan);
        } catch (CoberturaNoEncontradaException e){
            //vista.mostrarError();
        } catch (PlanYaRegistradoException e){
            
        }
    }

    // Eliminar Plan de una Cobertura específica
    public void eliminarPlanDeCobertura(String codigoCobertura, String idPlan) {
        try{
            Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
            cobertura.eliminarPlan(idPlan);
        } catch (CoberturaNoEncontradaException e){
            
        } catch (PlanNoEncontradoException e){
            
        }
    }

    // Listar todos los Planes de una Cobertura específica y cuántos suscriptores tienen
    public void listarPlanesDeCobertura(String codigoCobertura) {
        try{
            Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
            ArrayList<Plan> planes = cobertura.getPlanes();
        } catch (CoberturaNoEncontradaException e){
            
        }
    }
 
    // Modificar el precio de un Plan en una Cobertura específica
    public void modificarPrecioPlan(String codigoCobertura, String idPlan, float nuevoPrecio) {
        try{
            Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
            Plan plan = cobertura.buscarPlan(idPlan);
            plan.setPrecio(nuevoPrecio); 
        } catch(CoberturaNoEncontradaException e){
            
        } catch (PlanNoEncontradoException e){
            
        }
    }

    // Modificar el ID de un Plan en una Cobertura específica
    public void modificarIdPlan(String codigoCobertura, String idPlanActual, String nuevoId) {
        try{
            Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
            Plan plan = cobertura.buscarPlan(idPlanActual);
            plan.setId(nuevoId); 
        } catch(CoberturaNoEncontradaException e){
            
        } catch (PlanNoEncontradoException e){
            
        }
    }
    
    public Cobertura buscarCobertura(String clave){
        try{
            Cobertura cobertura = modelo.buscarCobertura(clave);
            return cobertura;
        } catch (CoberturaNoEncontradaException e){
            
        }
        return null;
    }

}
