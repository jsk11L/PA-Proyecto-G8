import modelos.Empresa;
import controladores.controladorCobertura;
import controladores.controladorPlan;
import controladores.controladorCliente;
import vista.*;
import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

    private Empresa empresa = new Empresa(); // Suponiendo que tienes un constructor por defecto
    private controladorCobertura controladorCobertura = new controladorCobertura(empresa);
    private controladorPlan controladorPlan = new controladorPlan(empresa);
    private controladorCliente controladorCliente = new controladorCliente(empresa);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestión de redes de cable de TV");

        BorderPane root = new BorderPane();
        MenuBar menuBar = new MenuBar();
        
        Menu menuGestion = new Menu("Gestión");
        
        MenuItem menuItemSuscribir = new MenuItem("Suscribir Cliente");
        menuItemSuscribir.setOnAction(e -> {
            // Aquí abres la ventana de suscripción
        });
        
        MenuItem menuItemDesuscribir = new MenuItem("Desuscribir Cliente");
        menuItemDesuscribir.setOnAction(e -> {
            // Aquí abres la ventana de desuscripción
        });
        
        menuGestion.getItems().addAll(menuItemSuscribir, menuItemDesuscribir);
        menuBar.getMenus().add(menuGestion);

        // Puedes agregar más ítems al menú si es necesario

        root.setTop(menuBar);
        
        Scene scene = new Scene(root, 800, 600); 
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


