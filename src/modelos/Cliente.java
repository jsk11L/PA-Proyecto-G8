package modelos;

/**
 * Representa un cliente con su información básica.
 */
public class Cliente {
    private String nombre;
    private String rut;
    private String telefono;

    /**
     * Construye un nuevo objeto Cliente con el nombre, RUT y teléfono especificados.
     *
     * @param nombre El nombre del cliente.
     * @param rut El RUT (Rol Único Tributario) del cliente.
     * @param telefono El número de teléfono del cliente.
     */
    public Cliente(String nombre, String rut, String telefono) {
        this.nombre = nombre;
        this.rut = rut;
        this.telefono = telefono;
    }

    /**
     * Devuelve el nombre del cliente.
     *
     * @return El nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre del cliente.
     *
     * @param nombre El nuevo nombre del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el RUT del cliente.
     *
     * @return El RUT del cliente.
     */
    public String getRut() {
        return rut;
    }

    /**
     * Establece el RUT del cliente.
     *
     * @param rut El nuevo RUT del cliente.
     */
    public void setRut(String rut) {
        this.rut = rut;
    }

    /**
     * Devuelve el número de teléfono del cliente.
     *
     * @return El número de teléfono del cliente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono del cliente.
     *
     * @param telefono El nuevo número de teléfono del cliente.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}