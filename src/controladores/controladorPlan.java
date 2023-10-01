package controladores;
import modelos.*;
import vista.*;
import java.io.*;
import java.util.*;

public class controladorPlan {
    private Empresa modelo;
    private vistaPlan vista;

    public controladorPlan(Empresa modelo) {
        this.modelo = modelo;
        this.vista = new vistaPlan();
    }

    // Suscribir un cliente a un plan específico de una cobertura
    public void suscribirClienteAPlan(String codigoCobertura, String idPlan, Cliente cliente) {
        Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
        if (cobertura != null) {
            Plan plan = cobertura.buscarPlan(idPlan);
            if (plan != null && !plan.tieneCliente(cliente)) { // Asegurándote de que el cliente no esté ya suscrito
                plan.agregarCliente(cliente);
                //vista.mostrarMensaje("Cliente suscrito al plan exitosamente!");
            } else {
                //vista.mostrarMensaje("Cliente ya está suscrito o plan no encontrado!");
            }
        } else {
            //vista.mostrarMensaje("Cobertura no encontrada!");
        }
    }

    // Desuscribir un cliente de un plan específico de una cobertura
    public void desuscribirClienteDePlan(String codigoCobertura, String idPlan, Cliente cliente) {
        Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
        if (cobertura != null) {
            Plan plan = cobertura.buscarPlan(idPlan);
            if (plan != null && plan.tieneCliente(cliente)) {
                plan.eliminarCliente(cliente);
                //vista.mostrarMensaje("Cliente desuscripto del plan exitosamente!");
            } else {
                //vista.mostrarMensaje("Cliente no está suscrito o plan no encontrado!");
            }
        } else {
            //vista.mostrarMensaje("Cobertura no encontrada!");
        }
    }

}

