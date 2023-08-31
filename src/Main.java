
//clase cliente:

public class Cliente {
    private String nombre;
    private String rut;
    private String telefono;

    public Cliente(String nombre, String rut, String telefono) {
        this.nombre = nombre;
        this.rut = rut;
        this.telefono = telefono;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}

//clase cobertura

public class Cobertura {
    private String region;
    private int codigoRegion;
    private int numeroClientes;
    private List<Cliente> clientes;

    public Cobertura(String region, int codigoRegion) {
        this.region = region;
        this.codigoRegion = codigoRegion;
        this.numeroClientes = 0;
        this.clientes = new ArrayList<>();
    }

    // Getters y setters
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getCodigoRegion() {
        return codigoRegion;
    }

    public void setCodigoRegion(int codigoRegion) {
        this.codigoRegion = codigoRegion;
    }

    public int getNumeroClientes() {
        return numeroClientes;
    }

    public void setNumeroClientes(int numeroClientes) {
        this.numeroClientes = numeroClientes;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
        numeroClientes++;
    }
}

//clase empresa
public class Empresa {
    private String nombre;
    private List<Cobertura> coberturas;

    public Empresa(String nombre) {
        this.nombre = nombre;
        this.coberturas = new ArrayList<>();
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cobertura> getCoberturas() {
        return coberturas;
    }

    public void agregarCobertura(Cobertura cobertura) {
        coberturas.add(cobertura);
    }
}
//Clase "Main" (para probar el código):
public class Main {
    public static void main(String[] args) {
        // Crear clientes
        Cliente cliente1 = new Cliente("Juan Pérez", "123456789", "+123456789");
        Cliente cliente2 = new Cliente("María López", "987654321", "+987654321");

        // Crear coberturas y agregar clientes
        Cobertura cobertura1 = new Cobertura("Zona Norte", 1);
        cobertura1.agregarCliente(cliente1);

        Cobertura cobertura2 = new Cobertura("Zona Sur", 2);
        cobertura2.agregarCliente(cliente2);

        // Crear empresa y agregar coberturas
        Empresa empresa = new Empresa("Mi Empresa");
        empresa.agregarCobertura(cobertura1);
        empresa.agregarCobertura(cobertura2);

        // Acceder a los datos
        System.out.println("Empresa: " + empresa.getNombre());

        for (Cobertura cobertura : empresa.getCoberturas()) {
            System.out.println("Cobertura: " + cobertura.getRegion() + " (Código: " + cobertura.getCodigoRegion() + ")");
            System.out.println("Número de clientes: " + cobertura.getNumeroClientes());

            for (Cliente cliente : cobertura.getClientes()) {
                System.out.println("Cliente: " + cliente.getNombre());
                System.out.println("   Rut: " + cliente.getRut());
                System.out.println("   Teléfono: " + cliente.getTelefono());
            }

            System.out.println();
        }
    }
}

