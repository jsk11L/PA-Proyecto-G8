package excepciones;

/**
 * Excepción que indica que una cobertura no se encuentra en el sistema.
 */
public class CoberturaNoEncontradaException extends Exception {
    
    /**
     * Constructor para crear una nueva excepción de CoberturaNoEncontrada con un mensaje específico.
     *
     * @param mensaje El mensaje detallado que describe el motivo de la excepción.
     */
    public CoberturaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}
