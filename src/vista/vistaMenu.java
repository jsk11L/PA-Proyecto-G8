package vista;

import controladores.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class vistaMenu {

    private Stage ventanaMenu;

    public vistaMenu() {
        ventanaMenu = new Stage();
        ventanaMenu.setTitle("Menú de administración");

        VBox layout = new VBox(10); // 10 es el espaciado entre los elementos
        layout.setPadding(new Insets(20, 20, 20, 20));

        Button btnAgregarCobertura = new Button("Agregar Cobertura");
        Button btnEliminarCobertura = new Button("Eliminar Cobertura");
        Button btnListarCoberturas = new Button("Listar Coberturas");
        Button btnListarTres = new Button("Listar 3 Coberturas con menos clientes");
        Button btnAgregarPlan = new Button("Agregar Plan");
        Button btnEliminarPlan = new Button("Eliminar Plan");
        Button btnListarPlanes = new Button("Listar Planes de una Cobertura");

        btnAgregarCobertura.setOnAction(event -> {
            // Aquí puedes llamar directamente a los métodos de la Vista correspondiente 
            // o pasar por el Controlador. Personalmente, recomiendo pasar por el Controlador.
        });

        // ... Configurar las acciones para los otros botones

        layout.getChildren().addAll(btnAgregarCobertura, btnEliminarCobertura, btnListarCoberturas);
        layout.getChildren().addAll(btnListarTres, btnAgregarPlan, btnEliminarPlan);
        layout.getChildren().addAll(btnListarPlanes);
        // ... Agregar los otros botones al layout

        Scene scene = new Scene(layout, 300, 400);  // Ajusta el tamaño como desees
        ventanaMenu.setScene(scene);
    }

    public void mostrar() {
        ventanaMenu.show();
    }
}