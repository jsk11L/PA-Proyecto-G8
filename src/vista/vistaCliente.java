package vista;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import modelos.*;
import java.util.*;
import controladores.*;

public class vistaCliente {
    private controladorCobertura cc;
    private controladorPlan cp;
    private controladorCliente cce;

    public vistaCliente(controladorCobertura ctrlCobertura, controladorPlan ctrlPlan, controladorCliente ctrlCliente) {
        this.cc = ctrlCobertura;
        this.cp = ctrlPlan;
        this.cce = ctrlCliente;
    }
    
    // En tu clase vistaCliente.java
    public void mostrarVentanaSuscripcion() {
        TextInputDialog dialogCobertura = new TextInputDialog();
        dialogCobertura.setTitle("Suscripción");
        dialogCobertura.setHeaderText("Ingrese la cobertura:");
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
        dialogPlan.setHeaderText("Ingrese el Plan:");
        Optional<String> resultPlan = dialogPlan.showAndWait();

        if (resultPlan.isPresent() && !resultPlan.get().trim().isEmpty()) {
            Plan plan = cp.buscarPlan(cobertura, resultPlan.get().trim());
            if (plan != null) {
                // Si encontramos el plan, continuamos al siguiente paso.
                ingresarDatosCliente(cobertura, resultPlan.get().trim());
            } else {
                mostrarMensajeError("Error", "Plan no encontrado en la cobertura seleccionada.");
            }
        } else {
            mostrarMensajeError("Error", "Debe ingresar un plan válido.");
        }
    }
    
    public void ingresarDatosCliente(String cobertura, String plan) {
        // Aquí puedes usar un Dialog personalizado o una ventana separada 
        // para ingresar todos los datos requeridos (nombre, rut, teléfono).
        // Por simplicidad, uso un TextInputDialog como ejemplo:

        TextInputDialog dialogCliente = new TextInputDialog();
        dialogCliente.setTitle("Datos del Cliente");
        dialogCliente.setHeaderText("Ingrese los datos del cliente (ejemplo: nombre,rut,telefono):");
        Optional<String> resultCliente = dialogCliente.showAndWait();

        if (resultCliente.isPresent() && validarDatosCliente(resultCliente.get().trim())) {
            String[] datos = resultCliente.get().split(",");
            try {
                cce.suscribirCliente(datos[0], datos[1], datos[2], cobertura, plan);
                mostrarMensajeInfo("Éxito", "Cliente registrado exitosamente.");
            } catch(Exception e) {
                mostrarMensajeError("Error", e.getMessage());
            }
        } else {
            mostrarMensajeError("Error", "Debe ingresar datos válidos del cliente.");
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
