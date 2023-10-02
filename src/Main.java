import modelos.*;
import controladores.controladorCobertura;
import controladores.controladorPlan;
import controladores.controladorCliente;
import vista.*;
import excepciones.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * Clase Main, punto de entrada principal de la aplicación de gestión de redes de cable de TV.
 * Se encarga de inicializar la interfaz de usuario, cargar datos de prueba y gestionar la interacción del usuario.
 */
public class Main extends Application {
    
     /** La instancia principal de la empresa. */
    private Empresa empresa = new Empresa();
    
    /** Controlador para gestionar coberturas. */
    private controladorCobertura controladorCobertura = new controladorCobertura(empresa);
    
    /** Controlador para gestionar planes. */
    private controladorPlan controladorPlan = new controladorPlan(empresa, controladorCobertura);
    
    /** Controlador para gestionar clientes. */
    private controladorCliente controladorCliente = new controladorCliente(empresa, controladorCobertura, controladorPlan);

     /**
     * Método que se ejecuta al iniciar la aplicación. Configura y muestra la ventana principal.
     * @param primaryStage Escenario principal donde se mostrarán los elementos.
     */
    @Override
    public void start(Stage primaryStage) {
        //cargarDatosDePrueba();
        //cargarDatosDePrueba2();
        cargarDatosDesdeCSV();
        
        primaryStage.setTitle("Gestión de redes de cable de TV");
        Image appIcon = new Image("/images/icon.png");
        primaryStage.getIcons().add(appIcon);

        BorderPane root = new BorderPane();

        Image image = new Image("/images/2.png");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(600);

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20, 20, 20, 20));
        vbox.setAlignment(Pos.CENTER_LEFT);
    
        Button btnSuscribir = new Button("Suscribir Cliente");
        btnSuscribir.setPrefWidth(200);
        btnSuscribir.setPrefHeight(50);
        
        Button btnDesuscribir = new Button("Desuscribir Cliente");
        btnDesuscribir.setPrefWidth(200);
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
           GeneradorReporte reporte = new GeneradorReporte(empresa);
           reporte.generarReporteCSV("report.csv");
           
           GenerarRegistroExcel reporte2 = new GenerarRegistroExcel(empresa);
           reporte2.generarReporte("report2.xls");
           
           primaryStage.close();
        });
        
        vbox.getChildren().addAll(btnSuscribir, btnDesuscribir, btnBuscar, btnListar, btnModificar, btnSalir);

        root.setLeft(vbox);
        root.setRight(imageView);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * Método para cargar datos ficticios de prueba en la aplicación. 
     * Incluye coberturas, planes y clientes para zonas como Valparaiso, Viña del Mar, etc.
     */
    private void cargarDatosDePrueba() {
        String[] zonas = {"Valparaiso", "Viña del Mar", "Quilpue"};
        String[] codigosRegion = {"5", "6", "7"};
        String[] planesIds = {"A", "B", "C", "D", "E"};
        String[] nombresClientes = {"Juan", "Carlos", "Ana", "Luis", "María"};
        String[] rutsClientes = {"12345678-9", "23456789-0", "34567890-1", "45678901-2", "56789012-3"};
        String[] telefonosClientes = {"912345678", "922345678", "932345678", "942345678", "952345678"};

        try{
            for (int i = 0; i < zonas.length; i++) {
                Cobertura cobertura = new Cobertura(zonas[i], codigosRegion[i]);
                empresa.agregarCobertura(cobertura);
                
                for (String planId : planesIds) {
                    Plan plan = new Plan(planId, 500);
                    cobertura.agregarPlan(plan);

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
    
    /**
     * Método para cargar otro conjunto de datos ficticios de prueba en la aplicación.
     * Incluye coberturas, planes y clientes para zonas como Osorno, Villa Alemana, etc.
     */
    private void cargarDatosDePrueba2() {
        String[] zonas = {"Osorno", "Villa Alemana", "La Cruz"};
        String[] codigosRegion = {"8", "9", "10"};
        String[] planesIds = {"A", "B", "C", "D", "E", "F", "G"};
        String[] nombresClientes = {"Juan", "Carlos", "Ana"};
        String[] rutsClientes = {"12345678-9", "23456789-0", "34567890-1"};
        String[] telefonosClientes = {"912345678", "922345678", "932345678"};

        try{
            for (int i = 0; i < zonas.length; i++) {
                Cobertura cobertura = new Cobertura(zonas[i], codigosRegion[i]);
                empresa.agregarCobertura(cobertura);
                
                for (String planId : planesIds) {
                    Plan plan = new Plan(planId, 500);
                    cobertura.agregarPlan(plan);

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
    
    /**
    * Carga los datos desde un archivo CSV y popula las estructuras de datos de la aplicación.
    * El método asume un formato específico del CSV, basado en la estructura de datos de la empresa.
    * Las entidades (Cobertura, Plan, Cliente) se crean o se obtienen según corresponda, evitando duplicados.
    * 
    * @throws IOException Si ocurre un problema al leer el archivo.
    */
    private void cargarDatosDesdeCSV() {
        String rutaArchivo = "report.csv";
        
        if (new File(rutaArchivo).exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
                String line;
                boolean esPrimeraLinea = true;
                
                while ((line = reader.readLine()) != null) {
                    if (esPrimeraLinea) {
                        esPrimeraLinea = false;
                        continue;
                    }
                    
                    String[] campos = line.split(";");
                    if (campos.length != 7) {
                        System.out.println("Línea mal formada en CSV: " + line);
                        continue;
                    }
                    
                    String zonaCobertura = campos[0];
                    String codigoRegion = campos[1];
                    String idPlan = campos[2];
                    String precioString = campos[3].replace("\"", "").replace(",", ".");
                    float precioPlan = Float.parseFloat(precioString);                            
                    String nombreCliente = campos[4];
                    String rutCliente = campos[5];
                    String telefonoCliente = campos[6];
                    
                    Cobertura cobertura = new Cobertura(zonaCobertura, codigoRegion);
                    if (empresa.buscarCobertura(cobertura) == false) {
                        empresa.agregarCobertura(cobertura);
                    }
                    else{
                        cobertura = empresa.buscarCobertura(codigoRegion);
                    }

                    Plan plan = new Plan(idPlan, precioPlan);
                    if (cobertura.buscarPlan(plan) == false) {
                        cobertura.agregarPlan(plan);
                    }
                    else{
                        plan = cobertura.buscarPlan(idPlan);
                    }

                    Cliente cliente = new Cliente(nombreCliente, rutCliente, telefonoCliente);
                    if (plan.buscarCliente(cliente) == false) {
                        plan.agregarCliente(cliente);
                    }
                                      
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (ClienteYaRegistradoException | PlanYaRegistradoException | CoberturaYaRegistradaException e){
                e.printStackTrace();
            } catch (PlanNoEncontradoException | CoberturaNoEncontradaException e){
                e.printStackTrace();
            }
        }
    }


    /**
     * Método principal que lanza la aplicación.
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        launch(args);
    }

}
