/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Christopher Cuyo
 *
 */
public class ClsOperacion {

    private int id;
    private double a;
    private double b;
    private double resultado;
    private String factor = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String operacion) {
        this.factor = operacion;
    }

    public ClsOperacion() {
    }

    public ClsOperacion(int id, double a, double b, double resultado, String factor) {
        this.id = id;
        this.a = a;
        this.b = b;
        this.resultado = resultado;
        this.factor = factor;
    }
}
