package excepciones;

/**
 * Excepción que indica que un cliente no ha sido encontrado en el sistema.
 */
public class ClienteNoEncontradoException extends Exception {
    
    /**
     * Constructor para crear una nueva excepción de ClienteNoEncontrado con un mensaje específico.
     *
     * @param mensaje El mensaje detallado que describe el motivo de la excepción.
     */
    public ClienteNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
