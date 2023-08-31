// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
/*public class Main {
    public static void main(String[] args) {
        // Press Alt+Intro with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        // Press Mayús+F10 or click the green arrow button in the gutter to run the code.
        for (int i = 1; i <= 5; i++) {

            // Press Mayús+F9 to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Ctrl+F8.
            System.out.println("i = " + i);
        }
    }
}

 */

import java.util.ArrayList;

class Suscriptor {
    private String nombre;
    private String rut;
    private String telefono;
    private int edad;

    public Suscriptor(String nombre, String rut, String telefono, int edad) {
        this.nombre = nombre;
        this.rut = rut;
        this.telefono = telefono;
        this.edad = edad;
    }

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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}

class Paquete {
    private String nombre;
    private double precio;
    private int cantidadSuscriptores;
    private int suscriptoresActuales;
    private ArrayList<Suscriptor> suscriptores;

    public Paquete(String nombre, double precio, int cantidadSuscriptores) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadSuscriptores = cantidadSuscriptores;
        this.suscriptoresActuales = 0;
        this.suscriptores = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidadSuscriptores() {
        return cantidadSuscriptores;
    }

    public int getSuscriptoresActuales() {
        return suscriptoresActuales;
    }

    public void agregarSuscriptor(Suscriptor suscriptor) {
        if (suscriptoresActuales < cantidadSuscriptores) {
            suscriptores.add(suscriptor);
            suscriptoresActuales++;
        } else {
            System.out.println("El paquete está lleno, no se puede agregar más suscriptores.");
        }
    }
}

class Redes {
    private ArrayList<Paquete> paquetes;

    public Redes() {
        paquetes = new ArrayList<>();
    }

    public ArrayList<Paquete> getPaquetes() {
        return paquetes;
    }

    public void agregarPaquete(Paquete paquete) {
        paquetes.add(paquete);
    }
}

public class Main {
    public static void main(String[] args) {
        Suscriptor suscriptor1 = new Suscriptor("Juan Pérez", "123456789", "555-1234", 30);
        Suscriptor suscriptor2 = new Suscriptor("María Gómez", "987654321", "555-5678", 25);

        Paquete paquete1 = new Paquete("Paquete Básico", 29.99, 50);
        Paquete paquete2 = new Paquete("Paquete Premium", 49.99, 100);

        paquete1.agregarSuscriptor(suscriptor1);
        paquete1.agregarSuscriptor(suscriptor2);

        paquete2.agregarSuscriptor(suscriptor1);

        Redes redes = new Redes();
        redes.agregarPaquete(paquete1);
        redes.agregarPaquete(paquete2);

        System.out.println("Paquetes en la red:");
        for (Paquete paquete : redes.getPaquetes()) {
            System.out.println("Nombre: " + paquete.getNombre());
            System.out.println("Precio: " + paquete.getPrecio());
            System.out.println("Suscriptores actuales: " + paquete.getSuscriptoresActuales());
            System.out.println();
        }
    }
}

