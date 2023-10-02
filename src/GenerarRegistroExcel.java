import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import modelos.*;

/**
 * La clase GeneradorReporteExcel se encarga de crear un archivo de hoja de cálculo
 * que contiene información detallada de las coberturas, planes y clientes de la empresa.
 * Utiliza la librería JExcelAPI para generar el archivo Excel.
 * 
 * @author javier
 * @version 1.0
 */
public class GenerarRegistroExcel {

    /** La empresa sobre la que se generará el reporte. */
    private Empresa empresa;

    /**
     * Constructor de la clase GeneradorReporteExcel.
     * 
     * @param empresa La empresa sobre la que se generará el reporte.
     */
    public GenerarRegistroExcel(Empresa empresa) {
        this.empresa = empresa;
    }

    /**
     * Genera un reporte en formato de hoja de cálculo y lo guarda en la ruta especificada.
     * 
     * @param rutaArchivo La ruta donde se guardará el archivo generado.
     */
    public void generarReporte(String rutaArchivo) {
        try {
            WritableWorkbook workbook = Workbook.createWorkbook(new File(rutaArchivo));
            WritableSheet sheet = workbook.createSheet("Reporte Empresa", 0);

            String[] headers = {"Cobertura", "Codigo de Region", "ID de Plan", "Precio de Plan", "Nombre Cliente", "RUT", "Telefono"};
            for (int i = 0; i < headers.length; i++) {
                Label label = new Label(i, 0, headers[i]);
                sheet.addCell(label);
            }

            int rowNum = 1;
            ArrayList<Cobertura> coberturas = empresa.getCoberturas();
            for (Cobertura cobertura : coberturas) {
                ArrayList<Plan> planes = cobertura.getPlanes();
                for (Plan plan : planes) {
                    ArrayList<Cliente> clientes = plan.getClientes();
                    for (Cliente cliente : clientes) {
                        sheet.addCell(new Label(0, rowNum, cobertura.getRegion()));
                        sheet.addCell(new Label(1, rowNum, cobertura.getCodigoRegion()));
                        sheet.addCell(new Label(2, rowNum, plan.getId()));
                        sheet.addCell(new Label(3, rowNum, String.valueOf(plan.getPrecio())));
                        sheet.addCell(new Label(4, rowNum, cliente.getNombre()));
                        sheet.addCell(new Label(5, rowNum, cliente.getRut()));
                        sheet.addCell(new Label(6, rowNum, cliente.getTelefono()));
                        rowNum++;
                    }
                }
            }
            
            workbook.write();
            workbook.close();
            
        } catch (IOException | WriteException e) {
            e.printStackTrace();
        }
    }
}
