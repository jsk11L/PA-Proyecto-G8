package vista;
import javafx.scene.control.*;
import modelos.*;
import java.util.*;
import controladores.*;
import javafx.scene.control.Alert;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.event.ActionEvent;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Clase que representa la vista de las operaciones relacionadas con Coberturas.
 */
public class vistaCobertura {
    /** Controlador de Cobertura asociado con esta vista. */
    private controladorCobertura cc;
    
    /**
     * Constructor de la clase vistaCobertura.
     * @param cc Controlador de Cobertura a ser asociado con esta vista.
     */
    public vistaCobertura(controladorCobertura cc) {
        this.cc = cc;
    }
    
    /**
     * Método para mostrar una ventana para agregar una nueva cobertura.
     */
    public void mostrarVentanaAgregar() {
    TextInputDialog dialogCodigo = new TextInputDialog();
    Stage stage = (Stage) dialogCodigo.getDialogPane().getScene().getWindow();
    stage.getIcons().add(new Image("/images/iconSus.png"));
    dialogCodigo.setTitle("Agregar Cobertura");
    dialogCodigo.setHeaderText("Ingrese la región:");
    Optional<String> resultCodigo = dialogCodigo.showAndWait();

    if (resultCodigo.isPresent() && !resultCodigo.get().trim().isEmpty()) {
        String codigoRegion = resultCodigo.get().trim();

        TextInputDialog dialogRegion = new TextInputDialog();
        dialogRegion.setTitle("Agregar Cobertura");
        dialogRegion.setHeaderText("Ingrese el código de la región:");
        Optional<String> resultRegion = dialogRegion.showAndWait();

        if (resultRegion.isPresent() && !resultRegion.get().trim().isEmpty()) {
            String region = resultRegion.get().trim();
            
            Cobertura nuevaCobertura = new Cobertura(codigoRegion, region);
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

    /**
     * Método para mostrar una ventana que permite eliminar una cobertura existente.
     */
    public void mostrarVentanaEliminar() {
        TextInputDialog dialogCobertura = new TextInputDialog();
        Stage stage = (Stage) dialogCobertura.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/images/iconDes.png"));
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
    
    /**
     * Método para mostrar una ventana que lista todas las coberturas existentes.
     */
    public void mostrarVentanaListar() {
        AtomicInteger coberturaIndice = new AtomicInteger(0);
        ArrayList<Cobertura> todasLasCoberturas = cc.listarCoberturas();
        int totalCoberturas = todasLasCoberturas.size();

        if (todasLasCoberturas.isEmpty()) {
            mostrarMensajeError("Error", "No se encontraron coberturas.");
            return;
        }

        Dialog<Void> dialog = new Dialog<>();
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/images/iconList.png"));
        dialog.setTitle("Datos de la Cobertura");
        dialog.setHeaderText("Información de las Coberturas:");

        ButtonType btnSiguienteType = new ButtonType("Siguiente", ButtonData.NEXT_FORWARD);
        ButtonType btnAnteriorType = new ButtonType("Anterior", ButtonData.BACK_PREVIOUS);
        ButtonType btnCerrarType = new ButtonType("Cerrar", ButtonData.CANCEL_CLOSE);

        dialog.getDialogPane().getButtonTypes().addAll(btnSiguienteType, btnAnteriorType, btnCerrarType);

        final Button btnSiguiente = (Button) dialog.getDialogPane().lookupButton(btnSiguienteType);
        btnSiguiente.addEventFilter(ActionEvent.ACTION, event -> {
            coberturaIndice.set((coberturaIndice.get() + totalCoberturas - 1) % totalCoberturas);
            event.consume();
            mostrarCoberturaEnTextArea(dialog, coberturaIndice.get(), todasLasCoberturas);
        });
        
        final Button btnAnterior = (Button) dialog.getDialogPane().lookupButton(btnAnteriorType);
        btnAnterior.addEventFilter(ActionEvent.ACTION, event -> {
            coberturaIndice.set((coberturaIndice.get() + 1) % totalCoberturas);
            event.consume();
            mostrarCoberturaEnTextArea(dialog, coberturaIndice.get(), todasLasCoberturas);
        });
        
        mostrarCoberturaEnTextArea(dialog, coberturaIndice.get(), todasLasCoberturas);
        
        dialog.showAndWait();
    }
    
    /**
     * Método para mostrar una ventana que lista las tres coberturas con menos clientes.
     */
    public void mostrarVentanaListarTres() {
        AtomicInteger coberturaIndice = new AtomicInteger(0);
        ArrayList<Cobertura> todasLasCoberturas = cc.listarCoberturasTres();
        int totalCoberturas = todasLasCoberturas.size();

        if (todasLasCoberturas.isEmpty()) {
            mostrarMensajeError("Error", "No se encontraron coberturas.");
            return;
        }

        Dialog<Void> dialog = new Dialog<>();
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/images/iconList.png"));
        dialog.setTitle("Datos de las Coberturas");
        dialog.setHeaderText("Información de las Tres Coberturas con Menos Clientes:");

        ButtonType btnSiguienteType = new ButtonType("Siguiente", ButtonData.NEXT_FORWARD);
        ButtonType btnAnteriorType = new ButtonType("Anterior", ButtonData.BACK_PREVIOUS);
        ButtonType btnCerrarType = new ButtonType("Cerrar", ButtonData.CANCEL_CLOSE);

        dialog.getDialogPane().getButtonTypes().addAll(btnSiguienteType, btnAnteriorType, btnCerrarType);

        final Button btnSiguiente = (Button) dialog.getDialogPane().lookupButton(btnSiguienteType);
        btnSiguiente.addEventFilter(ActionEvent.ACTION, event -> {
            coberturaIndice.set((coberturaIndice.get() + totalCoberturas - 1) % totalCoberturas);
            event.consume();
            mostrarCoberturaEnTextArea(dialog, coberturaIndice.get(), todasLasCoberturas);
        });
        
        final Button btnAnterior = (Button) dialog.getDialogPane().lookupButton(btnAnteriorType);
        btnAnterior.addEventFilter(ActionEvent.ACTION, event -> {
            coberturaIndice.set((coberturaIndice.get() + 1) % totalCoberturas);
            event.consume();
            mostrarCoberturaEnTextArea(dialog, coberturaIndice.get(), todasLasCoberturas);
        });
        
        mostrarCoberturaEnTextArea(dialog, coberturaIndice.get(), todasLasCoberturas);
        
        dialog.showAndWait();
    }

     /**
     * Muestra la cobertura en el área de texto del diálogo proporcionado.
     * @param dialog Diálogo donde se mostrará la cobertura.
     * @param indice Índice de la cobertura a mostrar.
     * @param coberturas Lista de coberturas a mostrar.
     */
    private void mostrarCoberturaEnTextArea(Dialog<Void> dialog, int indice, List<Cobertura> coberturas) {
        Cobertura cobertura = coberturas.get(indice);
        if (cobertura != null) {
            dialog.setContentText("Region: " + cobertura.getRegion() + "\nCódigo de la Región: " + cobertura.getCodigoRegion() + "\nCantida de Planes: " + cobertura.getCantPlanes() + "\nCantidad de Clientes: " + cobertura.numeroDeClientes());
        } else {
            dialog.setContentText("Cliente no encontrado.");
        }
    }

    /**
     * Muestra un mensaje de error al usuario.
     *
     * @param titulo El título del mensaje de error.
     * @param mensaje El contenido del mensaje de error.
     */
    public void mostrarMensajeError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/images/error.png"));
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    /**
     * Muestra un mensaje informativo al usuario.
     *
     * @param titulo El título del mensaje informativo.
     * @param mensaje El contenido del mensaje informativo.
     */
    public void mostrarMensajeInfo(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/images/error.png"));
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
