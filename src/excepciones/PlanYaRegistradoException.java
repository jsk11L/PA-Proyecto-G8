package excepciones;

/**
 * Excepción que indica que un plan ya se encuentra en el sistema.
 */
public class PlanYaRegistradoException extends Exception {
    
    /**
     * Constructor para crear una nueva excepción de PlanYaRegistrado con un mensaje específico.
     *
     * @param mensaje El mensaje detallado que describe el motivo de la excepción.
     */
    public PlanYaRegistradoException(String mensaje) {
        super(mensaje);
    }
}
