package controladores;

import modelos.*;
import excepciones.*;
import vista.*;
import java.io.*;
import java.util.*;

public class controladorCliente {
    private Empresa modelo;
    private vistaCliente vista; // Asumiendo que tienes una clase VistaCliente
    private controladorCobertura cc;
    private controladorPlan cp;

   public controladorCliente(Empresa modelo) {
        this.modelo = modelo;
        this.cc = new controladorCobertura(modelo);
        this.cp = new controladorPlan(modelo);
        this.vista = new vistaCliente(cc, cp, this);
    }


    public void suscribirCliente(String nombre, String rut, String telefono, String codigoCobertura, String idPlan) {
        try{
            Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
            Plan plan = cobertura.buscarPlan(idPlan);
            Cliente cliente = new Cliente(nombre,rut,telefono);
            plan.agregarCliente(cliente);
        }catch (CoberturaNoEncontradaException e){
            
        }catch (PlanNoEncontradoException e){
            
        }catch(ClienteYaRegistradoException e){
            
        }
    }

    public void desuscribirCliente(String nombre, String rut, String telefono, String codigoCobertura, String idPlan) {
        try{
            Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
            Plan plan = cobertura.buscarPlan(idPlan);
            Cliente cliente = new Cliente(nombre,rut,telefono);
            plan.eliminarCliente(cliente);
        }catch (CoberturaNoEncontradaException e){
            
        }catch (PlanNoEncontradoException e){
            
        }catch(ClienteNoEncontradoException e){
            
        }
    }
    
    public String[] buscarCliente(String rut) {
    for (Cobertura cobertura : modelo.getCoberturas()) {
        for (Plan plan : cobertura.getPlanes()) {
            for (Cliente cliente : plan.getClientes()) {
                if (cliente.getRut().equals(rut)) {
                    String[] resultado = new String[5];
                    resultado[0] = cobertura.getRegion();
                    resultado[1] = plan.getId();
                    resultado[2] = cliente.getNombre();
                    resultado[3] = cliente.getRut();
                    resultado[4] = cliente.getTelefono();
                    return resultado;
                }
            }
        }
    }
    return null; // Retorna null si no se encuentra el cliente
    
}
    
    public int obtenerTotalClientes(String codigoCobertura, String idPlan){
        try{
            Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
            Plan plan = cobertura.buscarPlan(idPlan);
            return plan.getClientes().size();
        }catch (CoberturaNoEncontradaException e){
            
        }catch (PlanNoEncontradoException e){
            
        }
        return 0;
    }
    
    public void iniciarSuscripcion() {
        vista.mostrarVentanaSuscripcion();
    }
    
    public void iniciarDesuscripcion() {
        vista.mostrarVentanaDesuscripcion();
    }
    
    public void iniciarBusqueda() {
        vista.mostrarVentanaBusqueda();
    }
    
    public void iniciarListar() {
        vista.mostrarVentanaListar();
    }
    

}

