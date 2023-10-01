package vista;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import modelos.*;
import java.util.*;
import controladores.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.function.Consumer;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.event.ActionEvent;
import javafx.scene.control.ButtonBar.ButtonData;

public class vistaCobertura {
    private controladorCobertura cc;
    
    public vistaCobertura(controladorCobertura cc) {
        this.cc = cc;
    }
    
    public void mostrarVentanaAgregar() {
    // Solicitar el código de región
    TextInputDialog dialogCodigo = new TextInputDialog();
    dialogCodigo.setTitle("Agregar Cobertura");
    dialogCodigo.setHeaderText("Ingrese la región:");
    Optional<String> resultCodigo = dialogCodigo.showAndWait();

    // Si el código es proporcionado, solicita la región
    if (resultCodigo.isPresent() && !resultCodigo.get().trim().isEmpty()) {
        String codigoRegion = resultCodigo.get().trim();

        // Solicitar la región
        TextInputDialog dialogRegion = new TextInputDialog();
        dialogRegion.setTitle("Agregar Cobertura");
        dialogRegion.setHeaderText("Ingrese el código de la región:");
        Optional<String> resultRegion = dialogRegion.showAndWait();

        // Si la región es proporcionada, crea la Cobertura y agrega
        if (resultRegion.isPresent() && !resultRegion.get().trim().isEmpty()) {
            String region = resultRegion.get().trim();
            
            Cobertura nuevaCobertura = new Cobertura(codigoRegion, region); // Suponiendo que el constructor de Cobertura toma ambos valores
            boolean agregado = cc.agregarCobertura(nuevaCobertura);

            if (agregado) {
                mostrarMensajeInfo("Éxito", "Cobertura agregada exitosamente.");
            } else {
                mostrarMensajeError("Error", "Ya existe una cobertura con ese código de región.");
            }
        } else {
            mostrarMensajeError("Error", "Debe ingresar una región válida.");
        }
    } else {
        mostrarMensajeError("Error", "Debe ingresar un código de región válido.");
    }
}

    
    public void mostrarVentanaEliminar() {
        TextInputDialog dialogCobertura = new TextInputDialog();
        dialogCobertura.setTitle("Eliminar Cobertura");
        dialogCobertura.setHeaderText("Ingrese el código de región de la cobertura a eliminar:");
        Optional<String> resultCobertura = dialogCobertura.showAndWait();

        if (resultCobertura.isPresent() && !resultCobertura.get().trim().isEmpty()) {
            boolean eliminado = cc.eliminarCobertura(resultCobertura.get().trim());
            if (eliminado) {
                mostrarMensajeInfo("Éxito", "Cobertura eliminada exitosamente.");
            } else {
                mostrarMensajeError("Error", "Error al eliminar la cobertura o no encontrada.");
            }
        } else {
            mostrarMensajeError("Error", "Debe ingresar una cobertura válida.");
        }
    }
    
    public void mostrarVentanaListar() {
        AtomicInteger coberturaIndice = new AtomicInteger(0);
        ArrayList<Cobertura> todasLasCoberturas = cc.listarCoberturas();
        int totalCoberturas = todasLasCoberturas.size();

        if (todasLasCoberturas == null || todasLasCoberturas.isEmpty()) {
            mostrarMensajeError("Error", "No se encontraron coberturas.");
            return;
        }

        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Datos de la Cobertura");
        dialog.setHeaderText("Información de las Coberturas:");

        ButtonType btnSiguienteType = new ButtonType("Siguiente", ButtonData.NEXT_FORWARD);
        ButtonType btnAnteriorType = new ButtonType("Anterior", ButtonData.BACK_PREVIOUS);
        ButtonType btnCerrarType = new ButtonType("Cerrar", ButtonData.CANCEL_CLOSE);

        dialog.getDialogPane().getButtonTypes().addAll(btnSiguienteType, btnAnteriorType, btnCerrarType);

        // Manejo de botón anterior
        
        final Button btnSiguiente = (Button) dialog.getDialogPane().lookupButton(btnSiguienteType);
        btnSiguiente.addEventFilter(ActionEvent.ACTION, event -> {
            coberturaIndice.set((coberturaIndice.get() + totalCoberturas - 1) % totalCoberturas);
            event.consume(); // Consume el evento para que no cierre el diálogo
            mostrarCoberturaEnTextArea(dialog, coberturaIndice.get(), todasLasCoberturas);
        });
        
        final Button btnAnterior = (Button) dialog.getDialogPane().lookupButton(btnAnteriorType);
        btnAnterior.addEventFilter(ActionEvent.ACTION, event -> {
            coberturaIndice.set((coberturaIndice.get() + 1) % totalCoberturas);
            event.consume(); // Consume el evento para que no cierre el diálogo
            mostrarCoberturaEnTextArea(dialog, coberturaIndice.get(), todasLasCoberturas);
        });
        
        mostrarCoberturaEnTextArea(dialog, coberturaIndice.get(), todasLasCoberturas);
        
        // Mostrar el diálogo
        dialog.showAndWait();
    }

    private void mostrarCoberturaEnTextArea(Dialog<Void> dialog, int indice, List<Cobertura> coberturas) {
        Cobertura cobertura = coberturas.get(indice);
        if (cobertura != null) {
            dialog.setContentText("Region: " + cobertura.getRegion() + "\nCódigo de la Región: " + cobertura.getCodigoRegion());
        } else {
            dialog.setContentText("Cliente no encontrado.");
        }
    }


    // Métodos para mostrar mensajes, que ya estaban definidos en vistaCliente:
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
