package vista;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import modelos.*;
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
        Stage suscripcionStage = new Stage();
        suscripcionStage.setTitle("Suscribir Cliente");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Etiquetas y campos de texto
        Label coberturaLabel = new Label("Cobertura:");
        TextField coberturaField = new TextField();
        Label planLabel = new Label("Plan:");
        TextField planField = new TextField();
        Label nombreLabel = new Label("Nombre:");
        TextField nombreField = new TextField();
        Label rutLabel = new Label("Rut:");
        TextField rutField = new TextField();
        Label telefonoLabel = new Label("Teléfono:");
        TextField telefonoField = new TextField();

        Button btnSuscribir = new Button("Suscribir Cliente");
        btnSuscribir.setOnAction(e -> {
            String coberturaID = coberturaField.getText();
            String planID = planField.getText();
            Cobertura cobertura = cc.buscarCobertura(coberturaID);
            Plan plan = cp.buscarPlan(cobertura, planID);

            if (cobertura != null && plan != null) {
                String nombre = nombreField.getText();
                String rut = rutField.getText();
                String telefono = telefonoField.getText();

                Cliente cliente = new Cliente(nombre, rut, telefono);
                cce.suscribirCliente(cliente, coberturaID, planID);

                // Puedes añadir una alerta de confirmación si quieres
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("Cliente suscrito con éxito!");
                alert.showAndWait();

                suscripcionStage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Cobertura o Plan no encontrado!");
                alert.showAndWait();
            }
        });

        grid.add(coberturaLabel, 0, 0);
        grid.add(coberturaField, 1, 0);
        grid.add(planLabel, 0, 1);
        grid.add(planField, 1, 1);
        grid.add(nombreLabel, 0, 2);
        grid.add(nombreField, 1, 2);
        grid.add(rutLabel, 0, 3);
        grid.add(rutField, 1, 3);
        grid.add(telefonoLabel, 0, 4);
        grid.add(telefonoField, 1, 4);
        grid.add(btnSuscribir, 1, 5);

        Scene scene = new Scene(grid, 400, 300);
        suscripcionStage.setScene(scene);
        suscripcionStage.show();
    }


}
