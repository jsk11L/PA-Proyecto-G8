package excepciones;

/**
 * Excepción que indica que una cobertura ya se encuentra en el sistema.
 */
public class CoberturaYaRegistradaException extends Exception {
    
    /**
     * Constructor para crear una nueva excepción de CoberturaYaRegistrada con un mensaje específico.
     *
     * @param mensaje El mensaje detallado que describe el motivo de la excepción.
     */
    public CoberturaYaRegistradaException(String mensaje) {
        super(mensaje);
    }
    
    /**
     * Sobrescribe el método toString() para proporcionar una representación personalizada de la excepción.
     *
     * @return Una representación en forma de cadena de esta excepción.
     */
    @Override
    public String toString() {
        return "CoberturaNoEncontradaException: " + getMessage();
    }
}
