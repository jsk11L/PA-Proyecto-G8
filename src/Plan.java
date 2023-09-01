/*import java.util.*;
import java.io.*;*/
public class Plan {
    private boolean contratado;
    private boolean planA;
    private boolean planB;
    private boolean planC;
    private boolean planD;

    public Plan() {
        contratado = false;
        planA = false;
        planB = false;
        planC = false;
        planD = false;
    }

    public boolean estaContratado() {
        return contratado;
    }

    public void setContratado(boolean contratado) {
        this.contratado = contratado;
    }

    public boolean tienePlanA() {
        return planA;
    }

    public void setPlanA(boolean planA) {
        this.planA = planA;
    }

    public boolean tienePlanB() {
        return planB;
    }

    public void setPlanB(boolean planB) {
        this.planB = planB;
    }

    public boolean tienePlanC() {
        return planC;
    }

    public void setPlanC(boolean planC) {
        this.planC = planC;
    }

    public boolean tienePlanD() {
        return planD;
    }

    public void setPlanD(boolean planD) {
        this.planD = planD;
    }
}

