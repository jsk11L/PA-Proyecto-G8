package vista;

import javafx.scene.control.*;
import modelos.*;
import java.util.*;
import controladores.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.event.ActionEvent;
import javafx.scene.control.ButtonBar.ButtonData;

public class vistaCliente {
    private controladorCobertura cc;
    private controladorPlan cp;
    private controladorCliente cce;

    public vistaCliente(controladorCobertura ctrlCobertura, controladorPlan ctrlPlan, controladorCliente ctrlCliente) {
        this.cc = ctrlCobertura;
        this.cp = ctrlPlan;
        this.cce = ctrlCliente;
    }
    
    public void mostrarVentanaSuscripcion() {
        TextInputDialog dialogCobertura = new TextInputDialog();
        dialogCobertura.setTitle("Suscripción");
        dialogCobertura.setHeaderText("Ingrese el código de región de la cobertura:");
        Optional<String> resultCobertura = dialogCobertura.showAndWait();

        if (resultCobertura.isPresent() && !resultCobertura.get().trim().isEmpty()) {
            Cobertura cobertura = cc.buscarCobertura(resultCobertura.get().trim());
            if (cobertura != null) {
                // Si encontramos la cobertura, continuamos al siguiente paso.
                seleccionarPlan(resultCobertura.get().trim());
            } else {
                mostrarMensajeError("Error", "Cobertura no encontrada.");
            }
        } else {
            mostrarMensajeError("Error", "Debe ingresar una cobertura válida.");
        }
        
    }
    
    public void seleccionarPlan(String cobertura) {
        TextInputDialog dialogPlan = new TextInputDialog();
        dialogPlan.setTitle("Suscripción");
        dialogPlan.setHeaderText("Ingrese el ID del Plan:");
        Optional<String> resultPlan = dialogPlan.showAndWait();

        if (resultPlan.isPresent() && !resultPlan.get().trim().isEmpty()) {
            Plan plan = cp.buscarPlan(cobertura, resultPlan.get().trim());
            if (plan != null) {
                ingresarDatosCliente(cobertura, resultPlan.get().trim());
            } else {
                mostrarMensajeError("Error", "Plan no encontrado en la cobertura seleccionada.");
            }
        } else {
            mostrarMensajeError("Error", "Debe ingresar un plan válido.");
        }
    }
    
    public void ingresarDatosCliente(String cobertura, String plan) {
        TextInputDialog dialogCliente = new TextInputDialog();
        dialogCliente.setTitle("Datos del Cliente");
        dialogCliente.setHeaderText("Ingrese los datos del cliente (ejemplo: nombre,rut,telefono):");
        Optional<String> resultCliente = dialogCliente.showAndWait();

        if (resultCliente.isPresent() && validarDatosCliente(resultCliente.get().trim())) {
            String[] datos = resultCliente.get().split(",");
            try {
                cce.suscribirCliente(datos[0], datos[1], datos[2], cobertura, plan);
                mostrarMensajeInfo("Éxito", "Cliente suscrito exitosamente.");
            } catch(Exception e) {
                mostrarMensajeError("Error", e.getMessage());
            }
        } else {
            mostrarMensajeError("Error", "Debe ingresar datos válidos del cliente.");
        }
    }
    
    public void mostrarVentanaDesuscripcion() {
        TextInputDialog dialogCobertura = new TextInputDialog();
        dialogCobertura.setTitle("Suscripción");
        dialogCobertura.setHeaderText("Ingrese el código de región de la cobertura:");
        Optional<String> resultCobertura = dialogCobertura.showAndWait();

        if (resultCobertura.isPresent() && !resultCobertura.get().trim().isEmpty()) {
            Cobertura cobertura = cc.buscarCobertura(resultCobertura.get().trim());
            if (cobertura != null) {
                // Si encontramos la cobertura, continuamos al siguiente paso.
                seleccionarPlanDesuscribir(resultCobertura.get().trim());
            } else {
                mostrarMensajeError("Error", "Cobertura no encontrada.");
            }
        } else {
            mostrarMensajeError("Error", "Debe ingresar una cobertura válida.");
        }
        
    }
    
    public void seleccionarPlanDesuscribir(String cobertura) {
        TextInputDialog dialogPlan = new TextInputDialog();
        dialogPlan.setTitle("Suscripción");
        dialogPlan.setHeaderText("Ingrese el ID del Plan:");
        Optional<String> resultPlan = dialogPlan.showAndWait();

        if (resultPlan.isPresent() && !resultPlan.get().trim().isEmpty()) {
            Plan plan = cp.buscarPlan(cobertura, resultPlan.get().trim());
            if (plan != null) {
                // Si encontramos el plan, continuamos al siguiente paso.
                ingresarDatosClienteDesuscribir(cobertura, resultPlan.get().trim());
            } else {
                mostrarMensajeError("Error", "Plan no encontrado en la cobertura seleccionada.");
            }
        } else {
            mostrarMensajeError("Error", "Debe ingresar un plan válido.");
        }
    }
    
    public void ingresarDatosClienteDesuscribir(String cobertura, String plan) {
        TextInputDialog dialogCliente = new TextInputDialog();
        dialogCliente.setTitle("Datos del Cliente");
        dialogCliente.setHeaderText("Ingrese los datos del cliente (ejemplo: nombre,rut,telefono):");
        Optional<String> resultCliente = dialogCliente.showAndWait();

        if (resultCliente.isPresent() && validarDatosCliente(resultCliente.get().trim())) {
            String[] datos = resultCliente.get().split(",");
            try {
                cce.desuscribirCliente(datos[0], datos[1], datos[2], cobertura, plan);
                mostrarMensajeInfo("Éxito", "Cliente desuscrito exitosamente.");
            } catch(Exception e) {
                mostrarMensajeError("Error", e.getMessage());
            }
        } else {
            mostrarMensajeError("Error", "Debe ingresar datos válidos del cliente.");
        }
    }
    
    public void mostrarVentanaBusqueda() {
        TextInputDialog dialogCliente = new TextInputDialog();
        dialogCliente.setTitle("Búsqueda de Cliente");
        dialogCliente.setHeaderText("Ingrese el RUT del cliente:");
        Optional<String> resultCliente = dialogCliente.showAndWait();

        if (resultCliente.isPresent() && !resultCliente.get().trim().isEmpty()) {
            String[] datos = cce.buscarCliente(resultCliente.get().trim());
            if (datos != null) {
                // Aquí puedes mostrar los datos del cliente
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Información del Cliente");
                alert.setHeaderText(null);
                alert.setContentText("Nombre: " + datos[2] + "\nRUT: " + datos[3] + "\nTeléfono: " + datos[4] + "\nCobertura Registrada: " + datos[0] + "\nPlan registrado: " + datos[1]);
                alert.showAndWait();
            } else {
                mostrarMensajeError("Error", "Cliente no encontrado.");
            }
        } else {
            mostrarMensajeError("Error", "Debe ingresar un RUT válido.");
        }
    }
    
    public void mostrarVentanaListar() {
        TextInputDialog dialogCobertura = new TextInputDialog();
        dialogCobertura.setTitle("Suscripción");
        dialogCobertura.setHeaderText("Ingrese el código de región de la cobertura:");
        Optional<String> resultCobertura = dialogCobertura.showAndWait();

        if (resultCobertura.isPresent() && !resultCobertura.get().trim().isEmpty()) {
            Cobertura cobertura = cc.buscarCobertura(resultCobertura.get().trim());
            if (cobertura != null) {
                // Si encontramos la cobertura, continuamos al siguiente paso.
                seleccionarPlanListar(resultCobertura.get().trim());
            } else {
                mostrarMensajeError("Error", "Cobertura no encontrada.");
            }
        } else {
            mostrarMensajeError("Error", "Debe ingresar una cobertura válida.");
        }
        
    }
    
     public void seleccionarPlanListar(String cobertura) {
        TextInputDialog dialogPlan = new TextInputDialog();
        dialogPlan.setTitle("Suscripción");
        dialogPlan.setHeaderText("Ingrese el ID del Plan:");
        Optional<String> resultPlan = dialogPlan.showAndWait();

        if (resultPlan.isPresent() && !resultPlan.get().trim().isEmpty()) {
            Plan plan = cp.buscarPlan(cobertura, resultPlan.get().trim());
            if (plan != null) {
                // Si encontramos el plan, continuamos al siguiente paso.
                listarClientes(cobertura, resultPlan.get().trim());
            } else {
                mostrarMensajeError("Error", "Plan no encontrado en la cobertura seleccionada.");
            }
        } else {
            mostrarMensajeError("Error", "Debe ingresar un plan válido.");
        }
    }  
    
    public void listarClientes(String cobertura, String plan) {
        AtomicInteger clienteIndice = new AtomicInteger(0);
        int totalClientes = cce.obtenerTotalClientes(cobertura, plan);

        // Creamos un dialogo personalizado para mostrar los clientes y los botones
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Datos del Cliente");
        dialog.setHeaderText("Información de los Clientes:");

        ButtonType btnSiguienteType = new ButtonType("Siguiente", ButtonData.NEXT_FORWARD);
        ButtonType btnAnteriorType = new ButtonType("Anterior", ButtonData.BACK_PREVIOUS);
        ButtonType btnCerrarType = new ButtonType("Cerrar", ButtonData.CANCEL_CLOSE);

        dialog.getDialogPane().getButtonTypes().addAll(btnSiguienteType, btnAnteriorType, btnCerrarType);

        // Evento para el botón Siguiente
        final Button btnSiguiente = (Button) dialog.getDialogPane().lookupButton(btnSiguienteType);
        btnSiguiente.addEventFilter(ActionEvent.ACTION, event -> {
            clienteIndice.set((clienteIndice.get() + 1) % totalClientes);
            event.consume(); // Consume el evento para que no cierre el diálogo
            actualizarCliente(dialog, cobertura, plan, clienteIndice.get());
        });

        // Evento para el botón Anterior
        final Button btnAnterior = (Button) dialog.getDialogPane().lookupButton(btnAnteriorType);
        btnAnterior.addEventFilter(ActionEvent.ACTION, event -> {
            clienteIndice.set((clienteIndice.get() + totalClientes - 1) % totalClientes);
            event.consume(); // Consume el evento para que no cierre el diálogo
            actualizarCliente(dialog, cobertura, plan, clienteIndice.get());
        });

        // Inicializamos mostrando el primer cliente
        actualizarCliente(dialog, cobertura, plan, clienteIndice.get());

        // Mostrar el diálogo
        dialog.showAndWait();
    }

    private void actualizarCliente(Dialog<Void> dialog, String cobertura, String plan, int indice) {
        Cliente cliente = cc.buscarClientePorIndice(cobertura, plan, indice);
        if (cliente != null) {
            dialog.setContentText("Nombre: " + cliente.getNombre() + "\nRUT: " + cliente.getRut() + "\nTeléfono: " + cliente.getTelefono() + "\nCobertura Registrada: " + cobertura + "\nPlan registrado: " + plan);
        } else {
            dialog.setContentText("Cliente no encontrado.");
        }
    }

    private boolean validarDatosCliente(String datos) {
        return datos.split(",").length == 3;
    }
    
    public void mostrarMensajeError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void mostrarMensajeInfo(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
