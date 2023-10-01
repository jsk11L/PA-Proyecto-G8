package vista;

import controladores.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class vistaMenu {

    private Stage ventanaMenu;
    private controladorCliente cce;
    private controladorCobertura cc;
    private controladorPlan cp;

    public vistaMenu(controladorCliente cce, controladorCobertura cc, controladorPlan cp) {
        this.cp = cp;
        this.cce = cce;
        this.cc = cc;
        
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
            this.cc.iniciarAgregar();
        });
        
        btnEliminarCobertura.setOnAction(event -> {
            this.cc.iniciarEliminar();
        });
        
        btnListarCoberturas.setOnAction(event -> {
            this.cc.iniciarListar();
        });
        
        btnListarTres.setOnAction(event -> {
            //this.cc.iniciarListarTres();
        });
        
        btnAgregarPlan.setOnAction(event -> {
            //this.cp.iniciarAgregar();
        });
        
        btnEliminarPlan.setOnAction(event -> {
            //this.cp.iniciarEliminar();
        });
        
        btnListarPlanes.setOnAction(event -> {
            //this.cp.iniciarListar();
        });


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