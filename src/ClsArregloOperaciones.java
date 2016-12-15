
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author archrs
 */
public class ClsArregloOperaciones {

    String ruta = "c:/epis/operaciones.txt/";

    private ArrayList<ClsOperacion> operacion;
    public int ultimoId;

    public ClsArregloOperaciones() {
        operacion = new ArrayList<>();
        cargar();
    }

    public void adicionar(ClsOperacion x) {
        operacion.add(x);
    }

    public void eliminar(ClsOperacion x) {
        operacion.remove(x);
    }

    public ClsOperacion obtener(int pos) {
        return operacion.get(pos);
    }

    public ClsOperacion buscar(int codigo) {
        for (int i = 0; i < operacion.size(); i++) {
            if (codigo == operacion.get(i).getId()) {
                return operacion.get(i);
            }
        }
        return null;
    }

    public ClsOperacion buscarTipo(String factor) {
        for (int i = 0; i < operacion.size(); i++) {
            if (factor.equals(operacion.get(i).getFactor())) {
                return operacion.get(i);
            }
        }
        return null;
    }

    public ArrayList<ClsOperacion> buscarTodosTipos(String factor) {
        ArrayList<ClsOperacion> listado = new ArrayList<>();
        for (int i = 0; i < operacion.size(); i++) {
            if (factor.equals(operacion.get(i).getFactor())) {
                listado.add(operacion.get(i));
            }
        }
        return listado;
    }

    public int tamano() {
        return operacion.size();
    }

    public void cargar() {
        try {
            File archivo = new File(ruta);
            if (archivo.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(ruta));
                String linea;

                while ((linea = br.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(linea, "|");

                    int id = Integer.parseInt(st.nextToken().trim());
                    double a = Double.parseDouble(st.nextToken().trim());
                    double b = Double.parseDouble(st.nextToken().trim());
                    double resultado = Double.parseDouble(st.nextToken().trim());
                    String factor = st.nextToken().trim();

                    ClsOperacion x = new ClsOperacion(id, a, b, resultado, factor);
                    adicionar(x);
                    ultimoId = id;
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
                pw.println(obtener(i).getId() + "|"
                        + obtener(i).getA() + "|"
                        + obtener(i).getB() + "|"
                        + obtener(i).getResultado() + "|"
                        + obtener(i).getFactor()
                );
            }
            pw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se produjo un error = " + e);
        }
    }
}
