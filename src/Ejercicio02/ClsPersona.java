/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio02;

/**
 *
 * @author ChrstphrSands
 */
public class ClsPersona {
    private int llegada;
    private int edad;
    private String nombre;
    private String sexo;

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    public int getLlegada() {
        return llegada;
    }

    public void setLlegada(int llegada) {
        this.llegada = llegada;
    }

    public ClsPersona(int llegada, String nombre, String sexo, int edad) {
        this.llegada = llegada;
        this.edad = edad;
        this.nombre = nombre;
        this.sexo = sexo;
    }
}
