import java.io.*;
import java.util.*;

//Clase "Main" (para probar el código):
public class Main {
    public static void main(String[] args) {
        // Crear clientes
        Cliente cliente1 = new Cliente("Juan Pérez", "123456789", "+123456789");
        Cliente cliente2 = new Cliente("María López", "987654321", "+987654321");

        // Agregar y eliminar planes en los clientes
        cliente1.agregarPlan("Plan A");
        cliente1.agregarPlan("Plan B", "Plan A");
        cliente1.agregarPlan("Plan C");
        cliente1.eliminarPlan("Plan A");

        cliente2.agregarPlan("Plan C");
        cliente2.agregarPlan("Plan D", "Plan C");
        cliente2.agregarPlan("Plan A");
        cliente2.eliminarPlan(0); // Eliminar el primer plan

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
                System.out.println("   Planes contratados:");
                for (String plan : cliente.getPlanesContratados()) {
                    System.out.println("      " + plan);
            }   }

            System.out.println();
        }
    }
}

