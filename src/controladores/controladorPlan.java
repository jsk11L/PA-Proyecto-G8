package controladores;
import modelos.*;
import excepciones.*;
import vista.*;
import java.util.*;

public class controladorPlan {
    private Empresa modelo;
    private vistaPlan vista;
    private controladorCobertura cc;

    public controladorPlan(Empresa modelo, controladorCobertura cc) {
        this.modelo = modelo;
        this.cc = cc;
        this.vista = new vistaPlan(this, cc);
    }
    
    public Plan buscarPlan(String cobertura, String clave){
        try{
            Plan plan = modelo.buscarPlan(cobertura, clave);
            return plan;
        } catch (CoberturaNoEncontradaException e){
            
        } catch(PlanNoEncontradoException e){
            
        }
        return null;
    }
    
    public boolean eliminarPlan(String cobertura, String idPlan){
        return modelo.eliminarPlan(cobertura, idPlan);
    }
    
    public boolean agregarPlan(String cobertura, String id, float precio){
        Plan plan = new Plan(id, precio);
        return cc.agregarPlanACobertura(cobertura, plan);

    }
    
    public ArrayList<Plan> listarPlanesPorCobertura(String cobertura){
        return cc.listarPlanesDeCobertura(cobertura);
    }
    
    public void iniciarAgregar(){
        vista.mostrarVentanaAgregar();
    }
    
    public void iniciarEliminar(){
        vista.mostrarVentanaEliminar();
    }
    
    public void iniciarListar(){
        vista.mostrarVentanaListar();
    }

}

