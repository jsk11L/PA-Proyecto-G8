package vista;
import javafx.scene.control.*;
import modelos.*;
import java.util.*;
import controladores.*;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Representa la interfaz gráfica para la administración de planes.
 */
public class vistaPlan {
    
    private controladorPlan cp;
    private controladorCobertura cc;

    /**
     * Constructor para la vista de plan.
     *
     * @param ctrlPlan Controlador asociado con las operaciones de plan.
     * @param cc Controlador asociado con las operaciones de cobertura.
     */
    public vistaPlan(controladorPlan ctrlPlan, controladorCobertura cc) {
        this.cp = ctrlPlan;
        this.cc = cc;
    }
    
    /**
     * Muestra una ventana para agregar un nuevo plan.
     */
    public void mostrarVentanaAgregar() {
        TextInputDialog dialogCobertura = new TextInputDialog();
        Stage stage = (Stage) dialogCobertura.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/images/iconSus.png"));
        dialogCobertura.setTitle("Agregar Plan");
        dialogCobertura.setHeaderText("Ingrese el código de región de la cobertura:");
        Optional<String> resultCobertura = dialogCobertura.showAndWait();

        if (resultCobertura.isPresent() && !resultCobertura.get().trim().isEmpty()) {
            Cobertura cobertura = cc.buscarCobertura(resultCobertura.get().trim());
            if (cobertura != null) {
                // Si encontramos la cobertura, solicitamos ID del Plan y Precio.
                ingresarDatosPlan(resultCobertura.get().trim());
            } else {
                mostrarMensajeError("Error", "Cobertura no encontrada.");
            }
        } else {
            mostrarMensajeError("Error", "Debe ingresar una cobertura válida.");
        }
    }

    /**
     * Permite ingresar datos de un plan.
     *
     * @param cobertura Código de región de la cobertura.
     */
    public void ingresarDatosPlan(String cobertura) {
        TextInputDialog dialogPlan = new TextInputDialog();
        Stage stage = (Stage) dialogPlan.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/images/iconSus.png"));
        dialogPlan.setTitle("Agregar Plan");
        dialogPlan.setHeaderText("Ingrese el ID del Plan y el precio (ejemplo: ID,precio):");
        Optional<String> resultPlan = dialogPlan.showAndWait();

        if (resultPlan.isPresent() && validarDatosPlan(resultPlan.get().trim())) {
            String[] datos = resultPlan.get().split(",");
            try {
                cp.agregarPlan(cobertura, datos[0], Float.parseFloat(datos[1]));
                mostrarMensajeInfo("Éxito", "Plan agregado exitosamente.");
            } catch(Exception e) {
                mostrarMensajeError("Error", e.getMessage());
            }
        } else {
            mostrarMensajeError("Error", "Debe ingresar datos válidos del plan.");
        }
    }

    /**
     * Muestra una ventana para eliminar un plan existente.
     */
    public void mostrarVentanaEliminar() {
        TextInputDialog dialogCobertura = new TextInputDialog();
        Stage stage = (Stage) dialogCobertura.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/images/iconDes.png"));
        dialogCobertura.setTitle("Eliminar Plan");
        dialogCobertura.setHeaderText("Ingrese el código de región de la cobertura:");
        Optional<String> resultCobertura = dialogCobertura.showAndWait();

        if (resultCobertura.isPresent() && !resultCobertura.get().trim().isEmpty()) {
            Cobertura cobertura = cc.buscarCobertura(resultCobertura.get().trim());
            if (cobertura != null) {
                // Si encontramos la cobertura, solicitamos ID del Plan.
                ingresarIdPlanEliminar(resultCobertura.get().trim());
            } else {
                mostrarMensajeError("Error", "Cobertura no encontrada.");
            }
        } else {
            mostrarMensajeError("Error", "Debe ingresar una cobertura válida.");
        }
    }

    /**
     * Permite ingresar el ID de un plan para eliminarlo.
     *
     * @param cobertura Código de región de la cobertura.
     */
    public void ingresarIdPlanEliminar(String cobertura) {
        TextInputDialog dialogPlan = new TextInputDialog();
        Stage stage = (Stage) dialogPlan.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/images/iconDes.png"));
        dialogPlan.setTitle("Eliminar Plan");
        dialogPlan.setHeaderText("Ingrese el ID del Plan:");
        Optional<String> resultPlan = dialogPlan.showAndWait();

        if (resultPlan.isPresent() && !resultPlan.get().trim().isEmpty()) {
            try {
                cp.eliminarPlan(cobertura, resultPlan.get().trim());
                mostrarMensajeInfo("Éxito", "Plan eliminado exitosamente.");
            } catch(Exception e) {
                mostrarMensajeError("Error", e.getMessage());
            }
        } else {
            mostrarMensajeError("Error", "Debe ingresar un ID de plan válido.");
        }
    }
    
    /**
     * Muestra una ventana para listar los planes disponibles.
     */
    public void mostrarVentanaListar() {
        TextInputDialog dialogCobertura = new TextInputDialog();
        Stage stage = (Stage) dialogCobertura.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/images/iconList.png"));
        dialogCobertura.setTitle("Listar Planes");
        dialogCobertura.setHeaderText("Ingrese el código de región de la cobertura:");
        Optional<String> resultCobertura = dialogCobertura.showAndWait();

        if (resultCobertura.isPresent() && !resultCobertura.get().trim().isEmpty()) {
            Cobertura cobertura = cc.buscarCobertura(resultCobertura.get().trim());
            if (cobertura != null) {
                listarPlanes(resultCobertura.get().trim());
            } else {
                mostrarMensajeError("Error", "Cobertura no encontrada.");
            }
        } else {
            mostrarMensajeError("Error", "Debe ingresar una cobertura válida.");
        }
    }

    /**
     * Lista los planes asociados a una cobertura específica.
     *
     * @param cobertura Código de región de la cobertura.
     */
    public void listarPlanes(String cobertura) {
        ArrayList<Plan> planes = cp.listarPlanesPorCobertura(cobertura);

        StringBuilder sb = new StringBuilder();
        for (Plan plan : planes) {
            sb.append("ID: ").append(plan.getId()).append(", Precio: ").append(plan.getPrecio()).append("\n");
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/images/iconList.png"));
        alert.setTitle("Planes de Cobertura");
        alert.setHeaderText(null);
        alert.setContentText(sb.toString());
        alert.showAndWait();
    }
    
    /**
     * Valida que los datos del plan estén en el formato correcto.
     * 
     * @param datos Los datos del plan en formato de cadena.
     * @return true si los datos son válidos, false en caso contrario.
     */
    private boolean validarDatosPlan(String datos) {
        return datos.split(",").length == 2;
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
