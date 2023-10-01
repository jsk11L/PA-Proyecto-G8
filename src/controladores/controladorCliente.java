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

    public void desuscribirCliente(Cliente cliente, String codigoCobertura, String idPlan) {
        try{
            Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
            Plan plan = cobertura.buscarPlan(idPlan);
            plan.eliminarCliente(cliente);
        }catch (CoberturaNoEncontradaException e){
            
        }catch (PlanNoEncontradoException e){
            
        }catch(ClienteNoEncontradoException e){
            
        }
    }
    
    public void iniciarSuscripcion() {
        vista.mostrarVentanaSuscripcion();
    }

}

