package vista;

import controladores.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Representa la interfaz gráfica del menú principal del sistema.
 */
public class vistaMenu {

    private Stage ventanaMenu;
    private controladorCliente cce;
    private controladorCobertura cc;
    private controladorPlan cp;

    /**
     * Constructor para inicializar el menú principal.
     *
     * @param cce Controlador para las operaciones relacionadas con el cliente.
     * @param cc Controlador para las operaciones relacionadas con la cobertura.
     * @param cp Controlador para las operaciones relacionadas con el plan.
     */
    public vistaMenu(controladorCliente cce, controladorCobertura cc, controladorPlan cp) {
        this.cp = cp;
        this.cce = cce;
        this.cc = cc;
     
        ventanaMenu = new Stage();
        ventanaMenu.setTitle("Menú de administración");
        Image appIcon = new Image("/images/iconMenu.png");
        ventanaMenu.getIcons().add(appIcon);

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
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
            this.cc.iniciarListarTres();
        });
        
        btnAgregarPlan.setOnAction(event -> {
            this.cp.iniciarAgregar();
        });
        
        btnEliminarPlan.setOnAction(event -> {
            this.cp.iniciarEliminar();
        });
        
        btnListarPlanes.setOnAction(event -> {
            this.cp.iniciarListar();
        });

        layout.getChildren().addAll(btnAgregarCobertura, btnEliminarCobertura, btnListarCoberturas);
        layout.getChildren().addAll(btnListarTres, btnAgregarPlan, btnEliminarPlan);
        layout.getChildren().addAll(btnListarPlanes);
        
        Scene scene = new Scene(layout, 300, 400);
        ventanaMenu.setScene(scene);
    }

    /**
     * Muestra el menú principal al usuario.
     */
    public void mostrar() {
        ventanaMenu.show();
    }
}