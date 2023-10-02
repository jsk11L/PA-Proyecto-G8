package vista;

import javafx.scene.control.*;
import modelos.*;
import java.util.*;
import controladores.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.event.ActionEvent;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;
import javafx.scene.image.Image;

/**
 * Representa la vista del cliente en la interfaz de usuario, proporcionando métodos para mostrar ventanas
 * relacionadas con operaciones del cliente, tales como suscripción, desuscripción, búsqueda y listado.
 */
public class vistaCliente {
    private controladorCobertura cc;
    private controladorPlan cp;
    private controladorCliente cce;

    /**
     * Constructor de la clase vistaCliente.
     * 
     * @param ctrlCobertura Controlador relacionado con las operaciones de cobertura.
     * @param ctrlPlan Controlador relacionado con las operaciones de planes.
     * @param ctrlCliente Controlador relacionado con las operaciones de clientes.
     */
    public vistaCliente(controladorCobertura ctrlCobertura, controladorPlan ctrlPlan, controladorCliente ctrlCliente) {
        this.cc = ctrlCobertura;
        this.cp = ctrlPlan;
        this.cce = ctrlCliente;
    }
    
    /**
     * Muestra una ventana para suscribir a un cliente.
     */
    public void mostrarVentanaSuscripcion() {
        TextInputDialog dialogCobertura = new TextInputDialog();
        Stage stage = (Stage) dialogCobertura.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/images/iconSus.png"));
        dialogCobertura.setTitle("Suscripción");
        dialogCobertura.setHeaderText("Ingrese el código de región de la cobertura:");
        Optional<String> resultCobertura = dialogCobertura.showAndWait();

        if (!resultCobertura.isPresent()) {
            return;
        }

        if (resultCobertura.get().trim().isEmpty()) {
            mostrarMensajeError("Error", "Debe ingresar una cobertura válida.");
            return;
        }

        Cobertura cobertura = cc.buscarCobertura(resultCobertura.get().trim());
        if (cobertura != null) {            
            seleccionarPlan(resultCobertura.get().trim());
        } else {
            mostrarMensajeError("Error", "Cobertura no encontrada.");
        }
        
    }
    
    /**
     * Permite al usuario seleccionar un plan para una cobertura especificada.
     * 
     * @param cobertura El código de región de la cobertura.
     */
    public void seleccionarPlan(String cobertura) {
        TextInputDialog dialogPlan = new TextInputDialog();
        Stage stage = (Stage) dialogPlan.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/images/iconSus.png"));
        dialogPlan.setTitle("Suscripción");
        dialogPlan.setHeaderText("Ingrese el ID del Plan:");
        Optional<String> resultPlan = dialogPlan.showAndWait();
        
        if (!resultPlan.isPresent()) {
            return;
        }

        if (resultPlan.get().trim().isEmpty()) {
            mostrarMensajeError("Error", "Debe ingresar una cobertura válida.");
            return;
        }

        Plan plan = cp.buscarPlan(cobertura, resultPlan.get().trim());
        if (plan != null) {
                ingresarDatosCliente(cobertura, resultPlan.get().trim());
            } else {
                mostrarMensajeError("Error", "Plan no encontrado en la cobertura seleccionada.");
            }
    }
    
    /**
     * Permite al usuario seleccionar un plan para una cobertura especificada.
     * 
     * @param cobertura El código de región de la cobertura.
     * @param plan El id del plan al que se desea suscribir al cliente.
     */
    public void ingresarDatosCliente(String cobertura, String plan) {
        TextInputDialog dialogCliente = new TextInputDialog();
        Stage stage = (Stage) dialogCliente.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/images/iconSus.png"));
        dialogCliente.setTitle("Datos del Cliente");
        dialogCliente.setHeaderText("Ingrese los datos del cliente (ejemplo: nombre,rut,telefono):");
        Optional<String> resultCliente = dialogCliente.showAndWait();
        
        if (!resultCliente.isPresent()) {
            return;
        }

        if (resultCliente.get().trim().isEmpty()) {
            mostrarMensajeError("Error", "Debe ingresar una cobertura válida.");
            return;
        }

        String[] datos = resultCliente.get().split(",");
        try {
                cce.suscribirCliente(datos[0], datos[1], datos[2], cobertura, plan);
                mostrarMensajeInfo("Éxito", "Cliente suscrito exitosamente.");
            } catch(Exception e) {
                mostrarMensajeError("Error", e.getMessage());
            }
    }
    
    /**
     * Muestra una ventana para desuscribir a un cliente.
     */
    public void mostrarVentanaDesuscripcion() {
        TextInputDialog dialogCobertura = new TextInputDialog();
        Stage stage = (Stage) dialogCobertura.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/images/iconDes.png"));
        dialogCobertura.setTitle("Suscripción");
        dialogCobertura.setHeaderText("Ingrese el código de región de la cobertura:");
        Optional<String> resultCobertura = dialogCobertura.showAndWait();
        
        if (!resultCobertura.isPresent()) {
            return;
        }

        if (resultCobertura.get().trim().isEmpty()) {
            mostrarMensajeError("Error", "Debe ingresar una cobertura válida.");
            return;
        }

        Cobertura cobertura = cc.buscarCobertura(resultCobertura.get().trim());
        if (cobertura != null) {
                // Si encontramos la cobertura, continuamos al siguiente paso.
                seleccionarPlanDesuscribir(resultCobertura.get().trim());
            } else {
                mostrarMensajeError("Error", "Cobertura no encontrada.");
            }
        
    }
    
    /**
     * Permite al usuario seleccionar un plan para una desuscripción basada en una cobertura especificada.
     * 
     * @param cobertura El código de región de la cobertura.
     */
    public void seleccionarPlanDesuscribir(String cobertura) {
        TextInputDialog dialogPlan = new TextInputDialog();
        Stage stage = (Stage) dialogPlan.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/images/iconDes.png"));
        dialogPlan.setTitle("Suscripción");
        dialogPlan.setHeaderText("Ingrese el ID del Plan:");
        Optional<String> resultPlan = dialogPlan.showAndWait();
        
        if (!resultPlan.isPresent()) {
            return;
        }

        if (resultPlan.get().trim().isEmpty()) {
            mostrarMensajeError("Error", "Debe ingresar una cobertura válida.");
            return;
        }

        Plan plan = cp.buscarPlan(cobertura, resultPlan.get().trim());
        if (plan != null) {
                // Si encontramos el plan, continuamos al siguiente paso.
                ingresarDatosClienteDesuscribir(cobertura, resultPlan.get().trim());
            } else {
                mostrarMensajeError("Error", "Plan no encontrado en la cobertura seleccionada.");
            }
    }
    
    /**
     * Solicita al usuario ingresar datos del cliente para completar la desuscripción.
     *
     * @param cobertura El código de región de la cobertura.
     * @param plan El ID del plan seleccionado.
     */
    public void ingresarDatosClienteDesuscribir(String cobertura, String plan) {
        TextInputDialog dialogCliente = new TextInputDialog();
        Stage stage = (Stage) dialogCliente.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/images/iconDes.png"));
        dialogCliente.setTitle("Datos del Cliente");
        dialogCliente.setHeaderText("Ingrese los datos del cliente (ejemplo: nombre,rut,telefono):");
        Optional<String> resultCliente = dialogCliente.showAndWait();
        
        if (!resultCliente.isPresent()) {
            return;
        }

        if (resultCliente.get().trim().isEmpty()) {
            mostrarMensajeError("Error", "Debe ingresar una cobertura válida.");
            return;
        }

        String[] datos = resultCliente.get().split(",");
        try {
                cce.desuscribirCliente(datos[0], datos[1], datos[2], cobertura, plan);
                mostrarMensajeInfo("Éxito", "Cliente desuscrito exitosamente.");
            } catch(Exception e) {
                mostrarMensajeError("Error", e.getMessage());
            }
    }
    
    /**
     * Muestra una ventana para buscar a un cliente por su RUT.
     */
    public void mostrarVentanaBusqueda() {
        TextInputDialog dialogCliente = new TextInputDialog();
        Stage stage = (Stage) dialogCliente.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/images/iconSearch.png"));
        dialogCliente.setTitle("Búsqueda de Cliente");
        dialogCliente.setHeaderText("Ingrese el RUT del cliente:");
        Optional<String> resultCliente = dialogCliente.showAndWait();
        
        if (!resultCliente.isPresent()) {
            return;
        }

        if (resultCliente.get().trim().isEmpty()) {
            mostrarMensajeError("Error", "Debe ingresar una cobertura válida.");
            return;
        }

        String[] datos = cce.buscarCliente(resultCliente.get().trim());
        if (datos != null) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Información del Cliente");
                alert.setHeaderText(null);
                alert.setContentText("Nombre: " + datos[2] + "\nRUT: " + datos[3] + "\nTeléfono: " + datos[4] + "\nCobertura Registrada: " + datos[0] + "\nPlan registrado: " + datos[1]);
                alert.showAndWait();
            } else {
                mostrarMensajeError("Error", "Cliente no encontrado.");
            }
    }
    
    /**
     * Muestra una ventana para listar clientes en un plan y cobertura específicos.
     */
    public void mostrarVentanaListar() {
        TextInputDialog dialogCobertura = new TextInputDialog();
        Stage stage = (Stage) dialogCobertura.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/images/iconList.png"));
        dialogCobertura.setTitle("Suscripción");
        dialogCobertura.setHeaderText("Ingrese el código de región de la cobertura:");
        Optional<String> resultCobertura = dialogCobertura.showAndWait();
        
        if (!resultCobertura.isPresent()) {
            return;
        }

        if (resultCobertura.get().trim().isEmpty()) {
            mostrarMensajeError("Error", "Debe ingresar una cobertura válida.");
            return;
        }

        Cobertura cobertura = cc.buscarCobertura(resultCobertura.get().trim());
        if (cobertura != null) {
                // Si encontramos la cobertura, continuamos al siguiente paso.
                seleccionarPlanListar(resultCobertura.get().trim());
            } else {
                mostrarMensajeError("Error", "Cobertura no encontrada.");
            }
        
    }
    
    /**
     * Permite al usuario seleccionar un plan para listar clientes basado en una cobertura especificada.
     * 
     * @param cobertura El código de región de la cobertura.
     */
     public void seleccionarPlanListar(String cobertura) {
        TextInputDialog dialogPlan = new TextInputDialog();
        Stage stage = (Stage) dialogPlan.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/images/iconList.png"));
        dialogPlan.setTitle("Suscripción");
        dialogPlan.setHeaderText("Ingrese el ID del Plan:");
        Optional<String> resultPlan = dialogPlan.showAndWait();
        
        if (!resultPlan.isPresent()) {
            return;
        }

        if (resultPlan.get().trim().isEmpty()) {
            mostrarMensajeError("Error", "Debe ingresar una cobertura válida.");
            return;
        }

        Plan plan = cp.buscarPlan(cobertura, resultPlan.get().trim());
        if (plan != null) {
                listarClientes(cobertura, resultPlan.get().trim());
            } else {
                mostrarMensajeError("Error", "Plan no encontrado en la cobertura seleccionada.");
            }
    }  
    
     /**
     * Lista los clientes para una cobertura y plan especificados, con botones para navegar entre los clientes.
     * 
     * @param cobertura El código de región de la cobertura.
     * @param plan El ID del plan seleccionado.
     */
    public void listarClientes(String cobertura, String plan) {
        AtomicInteger clienteIndice = new AtomicInteger(0);
        int totalClientes = cce.obtenerTotalClientes(cobertura, plan);

        Dialog<Void> dialog = new Dialog<>();
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/images/iconList.png"));
        dialog.setTitle("Datos del Cliente");
        dialog.setHeaderText("Información de los Clientes:");

        ButtonType btnSiguienteType = new ButtonType("Siguiente", ButtonData.NEXT_FORWARD);
        ButtonType btnAnteriorType = new ButtonType("Anterior", ButtonData.BACK_PREVIOUS);
        ButtonType btnCerrarType = new ButtonType("Cerrar", ButtonData.CANCEL_CLOSE);

        dialog.getDialogPane().getButtonTypes().addAll(btnSiguienteType, btnAnteriorType, btnCerrarType);

        
        final Button btnSiguiente = (Button) dialog.getDialogPane().lookupButton(btnSiguienteType);
        btnSiguiente.addEventFilter(ActionEvent.ACTION, event -> {
            clienteIndice.set((clienteIndice.get() + 1) % totalClientes);
            event.consume();
            actualizarCliente(dialog, cobertura, plan, clienteIndice.get());
        });

       
        final Button btnAnterior = (Button) dialog.getDialogPane().lookupButton(btnAnteriorType);
        btnAnterior.addEventFilter(ActionEvent.ACTION, event -> {
            clienteIndice.set((clienteIndice.get() + totalClientes - 1) % totalClientes);
            event.consume();
            actualizarCliente(dialog, cobertura, plan, clienteIndice.get());
        });

        actualizarCliente(dialog, cobertura, plan, clienteIndice.get());

        dialog.showAndWait();
    }

    /**
     * Actualiza la información del cliente mostrada en un diálogo basada en un índice.
     * 
     * @param dialog El diálogo que muestra la información del cliente.
     * @param cobertura El código de región de la cobertura.
     * @param plan El ID del plan seleccionado.
     * @param indice El índice del cliente en la lista.
     */
    private void actualizarCliente(Dialog<Void> dialog, String cobertura, String plan, int indice) {
        Cliente cliente = cc.buscarClientePorIndice(cobertura, plan, indice);
        if (cliente != null) {
            dialog.setContentText("Nombre: " + cliente.getNombre() + "\nRUT: " + cliente.getRut() + "\nTeléfono: " + cliente.getTelefono() + "\nCobertura Registrada: " + cobertura + "\nPlan registrado: " + plan);
        } else {
            dialog.setContentText("Cliente no encontrado.");
        }
    }

    /**
     * Valida que los datos del cliente estén en el formato correcto.
     * 
     * @param datos Los datos del cliente en formato de cadena.
     * @return true si los datos son válidos, false en caso contrario.
     */
    private boolean validarDatosCliente(String datos) {
        return datos.split(",").length == 3;
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
