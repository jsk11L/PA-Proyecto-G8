package controladores;
import modelos.*;
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
        modelo.agregarCobertura(cobertura);
        //vista.mostrarMensaje("Cobertura agregada exitosamente!");
    }

    // Eliminar Cobertura
    public void eliminarCobertura(String codigoCobertura) {
        Cobertura cobertura = modelo.eliminarCobertura(codigoCobertura);
        if (cobertura != null) {
            //vista.mostrarMensaje("Cobertura eliminada exitosamente!");
        } else {
            //vista.mostrarMensaje("Cobertura no encontrada!");
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
        Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
        if (cobertura != null) {
            cobertura.agregarPlan(plan); // Asumiendo que tienes este método en tu clase Cobertura
            //vista.mostrarMensaje("Plan agregado a la cobertura exitosamente!");
        } else {
            //vista.mostrarMensaje("Cobertura no encontrada!");
        }
    }

    // Eliminar Plan de una Cobertura específica
    public void eliminarPlanDeCobertura(String codigoCobertura, String idPlan) {
        Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
        if (cobertura != null) {
            boolean result = cobertura.eliminarPlan(idPlan); // Asumiendo que tienes este método en tu clase Cobertura
            if (result) {
                //vista.mostrarMensaje("Plan eliminado de la cobertura exitosamente!");
            } else {
                //vista.mostrarMensaje("Plan no encontrado en la cobertura!");
            }
        } else {
            //vista.mostrarMensaje("Cobertura no encontrada!");
        }
    }

    // Listar todos los Planes de una Cobertura específica y cuántos suscriptores tienen
    public void listarPlanesDeCobertura(String codigoCobertura) {
        Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
        if (cobertura != null) {
            ArrayList<Plan> planes = cobertura.getPlanes(); // Asumiendo que tienes este método en tu clase Cobertura
            //vista.mostrarListaPlanes(planes); // Suponiendo que en VistaCobertura tienes un método que muestra una lista de planes y sus suscriptores
        } else {
            //vista.mostrarMensaje("Cobertura no encontrada!");
        }
    }
 
    // Modificar el precio de un Plan en una Cobertura específica
    public void modificarPrecioPlan(String codigoCobertura, String idPlan, float nuevoPrecio) {
        Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
        if (cobertura != null) {
            Plan plan = cobertura.buscarPlan(idPlan); // Asumiendo que tienes este método en tu clase Cobertura
            if (plan != null) {
                plan.setPrecio(nuevoPrecio); // Utilizando el setter en tu clase Plan
                //vista.mostrarMensaje("Precio del plan actualizado exitosamente!");
            } else {
                //vista.mostrarMensaje("Plan no encontrado!");
            }
        } else {
            //vista.mostrarMensaje("Cobertura no encontrada!");
        }
    }

    // Modificar el ID de un Plan en una Cobertura específica
    public void modificarIdPlan(String codigoCobertura, String idPlanActual, String nuevoId) {
        Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
        if (cobertura != null) {
            Plan plan = cobertura.buscarPlan(idPlanActual);
            if (plan != null) {
                plan.setId(nuevoId); // Utilizando el setter en tu clase Plan
                //vista.mostrarMensaje("ID del plan actualizado exitosamente!");
            } else {
                //vista.mostrarMensaje("Plan no encontrado!");
            }
        } else {
            //vista.mostrarMensaje("Cobertura no encontrada!");
        }
    }

}
