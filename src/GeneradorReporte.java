import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import modelos.*;

/**
 * La clase GeneradorReporte se encarga de crear un archivo CSV que contiene
 * información detallada de las coberturas, planes y clientes de la empresa.
 * 
 * @author javier
 * @version 1.0
 */
public class GeneradorReporte {

    /** La empresa sobre la que se generará el reporte. */
    private Empresa empresa;

    /**
     * Constructor de la clase GeneradorReporte.
     * 
     * @param empresa La empresa sobre la que se generará el reporte.
     */
    public GeneradorReporte(Empresa empresa) {
        this.empresa = empresa;
    }

    /**
     * Genera un reporte en formato CSV y lo guarda en la ruta especificada.
     * 
     * @param rutaArchivo La ruta donde se guardará el archivo CSV generado.
     */
    public void generarReporteCSV(String rutaArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            
            writer.write("\"Cobertura\";\"Codigo de Region\";\"ID de Plan\";\"Precio de Plan\";\"Nombre Cliente\";\"RUT\";\"Telefono\"");
            writer.newLine();

            ArrayList<Cobertura> coberturas = empresa.getCoberturas();
            for (Cobertura cobertura : coberturas) {
                for (Plan plan : cobertura.getPlanes()) {
                    for (Cliente cliente : plan.getClientes()) {
                        writer.write(String.format("\"%s\";\"%s\";\"%s\";\"%.2f\";\"%s\";\"%s\";\"%s\"",
                                cobertura.getRegion(),
                                cobertura.getCodigoRegion(),
                                plan.getId(),
                                plan.getPrecio(),
                                cliente.getNombre(),
                                cliente.getRut(),
                                cliente.getTelefono()));
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
