package controladores;

import modelos.*;
import excepciones.*;
import vista.*;

/**
 * Controlador para gestionar las operaciones relacionadas con los clientes.
 * Esta clase se encarga de coordinar las acciones entre el modelo y la vista relacionadas con el cliente.
 */
public class controladorCliente {
    private Empresa modelo;
    private vistaCliente vista;
    private controladorCobertura cc;
    private controladorPlan cp;

   /**
    * Constructor del controlador del cliente.
    *
    * @param modelo Modelo principal de la empresa.
    * @param cc Controlador de cobertura.
    * @param cp Controlador del plan.
    */
    public controladorCliente(Empresa modelo, controladorCobertura cc, controladorPlan cp) {
        this.modelo = modelo;
        this.cc = cc;
        this.cp = cp;
        this.vista = new vistaCliente(cc, cp, this);
    }

    /**
     * Suscribe un cliente a un plan específico dentro de una cobertura.
     *
     * @param nombre Nombre del cliente.
     * @param rut RUT del cliente.
     * @param telefono Teléfono del cliente.
     * @param codigoCobertura Código de la cobertura.
     * @param idPlan ID del plan.
     */
    public void suscribirCliente(String nombre, String rut, String telefono, String codigoCobertura, String idPlan) {
        try {
            Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
            Plan plan = cobertura.buscarPlan(idPlan);
            Cliente cliente = new Cliente(nombre,rut,telefono);
            plan.agregarCliente(cliente);
        } catch (CoberturaNoEncontradaException | PlanNoEncontradoException | ClienteYaRegistradoException e) {
            return;
        }
    }

    /**
     * Desuscribe un cliente de un plan específico dentro de una cobertura.
     *
     * @param nombre Nombre del cliente.
     * @param rut RUT del cliente.
     * @param telefono Teléfono del cliente.
     * @param codigoCobertura Código de la cobertura.
     * @param idPlan ID del plan.
     */
    public void desuscribirCliente(String nombre, String rut, String telefono, String codigoCobertura, String idPlan) {
        try {
            Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
            Plan plan = cobertura.buscarPlan(idPlan);
            Cliente cliente = new Cliente(nombre,rut,telefono);
            plan.eliminarCliente(cliente);
        } catch (CoberturaNoEncontradaException | PlanNoEncontradoException | ClienteNoEncontradoException e) {
            return;
        }
    }

    /**
     * Busca un cliente basándose en su RUT y devuelve detalles relevantes si se encuentra.
     *
     * @param rut RUT del cliente a buscar.
     * @return Array de strings con detalles del cliente o null si no se encuentra.
     */
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
        return null;
    }

    /**
     * Obtiene el total de clientes suscritos a un plan dentro de una cobertura.
     *
     * @param codigoCobertura Código de la cobertura.
     * @param idPlan ID del plan.
     * @return Número total de clientes o 0 en caso de error.
     */
    public int obtenerTotalClientes(String codigoCobertura, String idPlan){
        try{
            Cobertura cobertura = modelo.buscarCobertura(codigoCobertura);
            Plan plan = cobertura.buscarPlan(idPlan);
            return plan.getClientes().size();
        } catch (CoberturaNoEncontradaException | PlanNoEncontradoException e) {
            return 0;
        }
    }
    
    /** Inicia la ventana de suscripción de clientes. */
    public void iniciarSuscripcion() {
        vista.mostrarVentanaSuscripcion();
    }
    
    /** Inicia la ventana de desuscripción de clientes. */
    public void iniciarDesuscripcion() {
        vista.mostrarVentanaDesuscripcion();
    }
    
    /** Inicia la ventana de búsqueda de clientes. */
    public void iniciarBusqueda() {
        vista.mostrarVentanaBusqueda();
    }
    
    /** Inicia la ventana para listar clientes. */
    public void iniciarListar() {
        vista.mostrarVentanaListar();
    }
}