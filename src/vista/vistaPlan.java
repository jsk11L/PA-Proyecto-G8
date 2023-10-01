package vista;
import javafx.scene.control.*;
import modelos.*;
import java.util.*;
import controladores.*;
import javafx.scene.control.Alert;

public class vistaPlan {
    private controladorPlan cp;
    private controladorCobertura cc;

    public vistaPlan(controladorPlan ctrlPlan, controladorCobertura cc) {
        this.cp = ctrlPlan;
        this.cc = cc;
    }
    
    public void mostrarVentanaAgregar() {
        TextInputDialog dialogCobertura = new TextInputDialog();
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

    public void ingresarDatosPlan(String cobertura) {
        TextInputDialog dialogPlan = new TextInputDialog();
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

    public void mostrarVentanaEliminar() {
        TextInputDialog dialogCobertura = new TextInputDialog();
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

    public void ingresarIdPlanEliminar(String cobertura) {
        TextInputDialog dialogPlan = new TextInputDialog();
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
    
    public void mostrarVentanaListar() {
        TextInputDialog dialogCobertura = new TextInputDialog();
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

    public void listarPlanes(String cobertura) {
        ArrayList<Plan> planes = cp.listarPlanesPorCobertura(cobertura);

        StringBuilder sb = new StringBuilder();
        for (Plan plan : planes) {
            sb.append("ID: ").append(plan.getId()).append(", Precio: ").append(plan.getPrecio()).append("\n");
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Planes de Cobertura");
        alert.setHeaderText(null);
        alert.setContentText(sb.toString());
        alert.showAndWait();
    }

    private boolean validarDatosPlan(String datos) {
        return datos.split(",").length == 2;
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
