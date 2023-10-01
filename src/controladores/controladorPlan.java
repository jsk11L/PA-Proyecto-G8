package controladores;
import modelos.*;
import excepciones.*;
import vista.*;
import java.io.*;
import java.util.*;

public class controladorPlan {
    private Empresa modelo;
    private vistaPlan vista;

    public controladorPlan(Empresa modelo) {
        this.modelo = modelo;
        this.vista = new vistaPlan();
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

}

