package excepciones;

public class ClienteYaRegistradoException extends Exception {
    public ClienteYaRegistradoException(String mensaje) {
        super(mensaje);
    }
}