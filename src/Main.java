import modelos.*;
import controladores.controladorCobertura;
import controladores.controladorPlan;
import controladores.controladorCliente;
import vista.*;
import java.io.*;
import java.util.*;
import excepciones.*;
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
    private controladorPlan controladorPlan = new controladorPlan(empresa, controladorCobertura);
    private controladorCliente controladorCliente = new controladorCliente(empresa, controladorCobertura, controladorPlan);

    @Override
    public void start(Stage primaryStage) {
        cargarDatosDePrueba();
        cargarDatosDePrueba2();
        
        primaryStage.setTitle("Gestión de redes de cable de TV");
        Image appIcon = new Image("/images/icon.png");
        primaryStage.getIcons().add(appIcon);

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
        
        Button btnBuscar = new Button("Buscar un Cliente");
        btnBuscar.setPrefWidth(200);
        btnBuscar.setPrefHeight(50);
        
        Button btnListar = new Button("Listar Clientes de un Plan");
        btnListar.setPrefWidth(200);
        btnListar.setPrefHeight(50);
        
        Button btnModificar = new Button("Entrar al sistema");
        btnModificar.setPrefWidth(200);
        btnModificar.setPrefHeight(50);
        
        Button btnSalir = new Button("Cerrar la aplicación");
        btnSalir.setPrefWidth(200);
        btnSalir.setPrefHeight(50);
        
        btnSuscribir.setOnAction(event -> {
            controladorCliente.iniciarSuscripcion();
        });
        
        btnDesuscribir.setOnAction(event -> {
            controladorCliente.iniciarDesuscripcion();
        });
        
        btnBuscar.setOnAction(event -> {
            controladorCliente.iniciarBusqueda();
        });
        
        btnListar.setOnAction(event -> {
            controladorCliente.iniciarListar();
        });
        
        btnModificar.setOnAction(event -> {
           vistaMenu vm = new vistaMenu(controladorCliente, controladorCobertura, controladorPlan);
           vm.mostrar();
           
        });
        
        btnSalir.setOnAction(event -> {
           primaryStage.close();
        });
        
        vbox.getChildren().addAll(btnSuscribir, btnDesuscribir, btnBuscar, btnListar, btnModificar, btnSalir);

        // Establecer imagen y botones en el BorderPane
        root.setLeft(vbox);
        root.setRight(imageView);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void cargarDatosDePrueba() {
        String[] zonas = {"Valparaiso", "Viña del Mar", "Quilpue"};
        String[] codigosRegion = {"5", "6", "7"};  // Codigos ficticios
        String[] planesIds = {"A", "B", "C", "D", "E"};
        String[] nombresClientes = {"Juan", "Carlos", "Ana", "Luis", "María"};
        String[] rutsClientes = {"12345678-9", "23456789-0", "34567890-1", "45678901-2", "56789012-3"};
        String[] telefonosClientes = {"912345678", "922345678", "932345678", "942345678", "952345678"};

        // Crear coberturas
        try{
            for (int i = 0; i < zonas.length; i++) {
                Cobertura cobertura = new Cobertura(zonas[i], codigosRegion[i]);
                empresa.agregarCobertura(cobertura);
                
                // Crear planes para cada cobertura
                for (String planId : planesIds) {
                    Plan plan = new Plan(planId, 500);
                    cobertura.agregarPlan(plan);

                    // Crear clientes para cada plan
                    for (int j = 0; j < nombresClientes.length; j++) {
                        Cliente cliente = new Cliente(nombresClientes[j], rutsClientes[j], telefonosClientes[j]);
                        plan.agregarCliente(cliente);
                    }
                }
            }
        }catch(CoberturaYaRegistradaException e){
            return;
        }catch(PlanYaRegistradoException e){
            return;
        }catch(ClienteYaRegistradoException e){
            return;
        }
    }
    
    private void cargarDatosDePrueba2() {
        String[] zonas = {"Osorno", "Villa Alemana", "La Cruz"};
        String[] codigosRegion = {"8", "9", "10"};  // Codigos ficticios
        String[] planesIds = {"A", "B", "C", "D", "E", "F", "G"};
        String[] nombresClientes = {"Juan", "Carlos", "Ana"};
        String[] rutsClientes = {"12345678-9", "23456789-0", "34567890-1"};
        String[] telefonosClientes = {"912345678", "922345678", "932345678"};

        // Crear coberturas
        try{
            for (int i = 0; i < zonas.length; i++) {
                Cobertura cobertura = new Cobertura(zonas[i], codigosRegion[i]);
                empresa.agregarCobertura(cobertura);
                
                // Crear planes para cada cobertura
                for (String planId : planesIds) {
                    Plan plan = new Plan(planId, 500);
                    cobertura.agregarPlan(plan);

                    // Crear clientes para cada plan
                    for (int j = 0; j < nombresClientes.length; j++) {
                        Cliente cliente = new Cliente(nombresClientes[j], rutsClientes[j], telefonosClientes[j]);
                        plan.agregarCliente(cliente);
                    }
                }
            }
        }catch(CoberturaYaRegistradaException e){
            return;
        }catch(PlanYaRegistradoException e){
            return;
        }catch(ClienteYaRegistradoException e){
            return;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
