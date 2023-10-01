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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Main extends Application {

    private Empresa empresa = new Empresa(); // Suponiendo que tienes un constructor por defecto
    private controladorCobertura controladorCobertura = new controladorCobertura(empresa);
    private controladorPlan controladorPlan = new controladorPlan(empresa);
    private controladorCliente controladorCliente = new controladorCliente(empresa);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestión de redes de cable de TV");

        BorderPane root = new BorderPane();

        // Configurar la imagen
        Image image = new Image("/images/2.png"); // Asegúrate de poner la ruta correcta de tu imagen
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(600); // Ajustar al tamaño de tu ventana

        // Configurar botones en la izquierda
        VBox vbox = new VBox();
        vbox.setSpacing(10); // Espacio entre botones
        vbox.setPadding(new Insets(20, 20, 20, 20)); // Espaciado en la parte superior, derecha, inferior y izquierda
        vbox.setAlignment(Pos.CENTER_LEFT); // Alineación de los botones
    
        Button btnSuscribir = new Button("Suscribir Cliente");
        btnSuscribir.setPrefWidth(200);   // Cambia 200 por el ancho que prefieras.
        btnSuscribir.setPrefHeight(50);
        
        Button btnDesuscribir = new Button("Desuscribir Cliente");
        btnDesuscribir.setPrefWidth(200);   // Cambia 200 por el ancho que prefieras.
        btnDesuscribir.setPrefHeight(50);
        
        Button btnModificar = new Button("Entrar al sistema");
        btnModificar.setPrefWidth(200);
        btnModificar.setPrefHeight(50);
        
        btnSuscribir.setOnAction(event -> {
            controladorCliente.iniciarSuscripcion();
        });
        
        btnDesuscribir.setOnAction(event -> {
            //controladorCliente.iniciarDesuscripcion();
        });
        
        btnModificar.setOnAction(event -> {
           System.out.println("xd"); 
        });
        
        vbox.getChildren().addAll(btnSuscribir, btnDesuscribir, btnModificar);

        // Establecer imagen y botones en el BorderPane
        root.setLeft(vbox);
        root.setRight(imageView);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}


