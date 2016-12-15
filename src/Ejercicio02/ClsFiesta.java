/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author ChrstphrSands
 */
public class ClsFiesta {

    public int totalPersonas;
    int totalMujeres;
    int totalHombres;
    double promedio;
    
    String ruta = "C:/epis/fiesta.txt";
    private ArrayList<ClsPersona> persona;

    public ClsFiesta() {
        persona = new ArrayList<>();
        cargar();
    }
    
    public void adicionar(ClsPersona x) {
        persona.add(x);
    }

    public void eliminar(ClsPersona x) {
        persona.remove(x);
    }

    public ClsPersona obtener(int pos) {
        return persona.get(pos);
    }
    
    public int tamano() {
        return persona.size();
    }
    
    public void cargar() {
        try {
            File archivo = new File(ruta);
            if (archivo.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(ruta));
                String linea;

                while ((linea = br.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(linea, "|");
                    int llegada = Integer.parseInt(st.nextToken().trim());
                    String nombre = st.nextToken().trim();
                    String sexo = st.nextToken().trim();
                    int edad = Integer.parseInt(st.nextToken().trim());

                    ClsPersona x = new ClsPersona(llegada, nombre, sexo, edad);
                    adicionar(x);
                    totalPersonas = llegada;
                }
                br.close();
            } else {
                JOptionPane.showMessageDialog(null, "El archivo no existe...");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se produjo un error = " + e);
        }
    }

    public void grabar() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(ruta));
            for (int i = 0; i < tamano(); i++) {
                pw.println(obtener(i).getLlegada()+ "|"
                        + obtener(i).getNombre()+ "|"
                        + obtener(i).getSexo()+ "|"
                        + obtener(i).getEdad()
                );
            }
            pw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se produjo un error = " + e);
        }
    }
    
    public ArrayList<ClsPersona> buscarHM(String sexo) {
        ArrayList<ClsPersona> listado = new ArrayList<>();
        for (int i = 0; i < persona.size(); i++) {
            if (sexo.equals(persona.get(i).getSexo())) {
                listado.add(persona.get(i));
            }
        }
        return listado;
    }
    
//    public int buscarHM(String sexo) {
//        ArrayList<ClsPersona> listado = new ArrayList<>();
//        for (int i = 0; i < persona.size(); i++) {
//            if (sexo.equals(persona.get(i).getSexo())) {
//                listado.add(persona.get(i));
//            }
//        }
//        return listado.size();
//    }
    
    int totalAsistentes(){
        return totalPersonas;
    }

    
    
//
//    double promedioPorSexo(){
//        
//    }
//
//    int masJoven(){
//        
//    }
}
