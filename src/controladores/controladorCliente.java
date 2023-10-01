package controladores;
import modelos.*;
import vista.*;
import java.io.*;
import java.util.*;

public class controladorCliente {
    private Empresa modelo;
    private vistaCliente vista; // Asumiendo que tienes una clase VistaCliente

    public controladorCliente(Empresa modelo) {
        this.modelo = modelo;
        this.vista = new vistaCliente();
    }

    public void suscribirCliente(Cliente cliente, String codigoCobertura, String idPlan) {
        Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
        if (cobertura != null) {
            Plan plan = cobertura.buscarPlan(idPlan);
            if (plan != null && !plan.tieneCliente(cliente)) { // Aquí debes asegurarte de tener un método que verifique si un cliente ya está suscrito al plan
                plan.agregarCliente(cliente); // Esto es un método hipotético que debes implementar en tu clase Plan
                //vista.mostrarMensaje("Cliente suscrito exitosamente!"); // Esta es una función hipotética en tu VistaCliente
            }
        }
    }

    public void desuscribirCliente(Cliente cliente, String codigoCobertura, String idPlan) {
        Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
        if (cobertura != null) {
            Plan plan = cobertura.buscarPlan(idPlan);
            if (plan != null && plan.tieneCliente(cliente)) {
                plan.eliminarCliente(cliente);
                //vista.mostrarMensaje("Cliente desuscripto exitosamente!");
            }
        }
    }
}

