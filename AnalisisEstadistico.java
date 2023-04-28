
package analisis.estadistico;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class AnalisisEstadistico {
    public static void main(String[] args) {
        // Ubicación del archivo de datos
        String ubicacionArchivo = "C:\\Users\\Estudiante\\Desktop\\LaB\\Evaluaciones_Agropecuarias_Municipales_EVA.csv";
        
        // Lista para almacenar los datos
        List<Integer> datos = new ArrayList<Integer>();
        
        // Lectura de datos del archivo
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(ubicacionArchivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                // Separar los valores por coma y convertir a entero
                String[] valores = linea.split(",");
                if (valores[0].matches("\\d+")) { // Verificar si el valor es un número
                    int valor = Integer.parseInt(valores[0]);
                    datos.add(valor);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        // Cálculo de estadísticas básicas
        double media = calcularMedia(datos);
        double mediana = calcularMediana(datos);
        double desviacionEstandar = calcularDesviacionEstandar(datos);
        
        // Creación del dataset para el gráfico
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < datos.size(); i++) {
            dataset.addValue(datos.get(i), "Valor", Integer.toString(i));
        }
        
        // Creación del gráfico de barras
        JFreeChart grafico = ChartFactory.createBarChart(
                "Ejemplo de gráfico de barras", // Título del gráfico
                "Índice", // Etiqueta del eje X
                "Valor", // Etiqueta del eje Y
                dataset); // Datos del gráfico
        
        // Creación del panel para el gráfico
        ChartPanel panelGrafico = new ChartPanel(grafico);
        panelGrafico.setPreferredSize(new Dimension(800, 600));
        
        // Creación de la ventana y adición del panel del gráfico
        JFrame ventana = new JFrame();
        ventana.setContentPane(panelGrafico);
        ventana.pack();
        ventana.setVisible(true);
        
        // Impresión de estadísticas básicas
        System.out.println("Media: " + media);
        System.out.println("Mediana: " + mediana);
        System.out.println("Desviación estándar: " + desviacionEstandar);
    }
    
    // Funciones para cálculo de estadísticas básicas
    public static double calcularMedia(List<Integer> datos) {
        int suma = 0;
        for (int i = 0; i < datos.size(); i++) {
            suma += datos.get(i);
        }
        return (double)suma / datos.size();
    }
    
    public static double calcularMediana(List<Integer> datos) {
    int mitad = datos.size() / 2;
    if (datos.size() % 2 == 0) {
        return (double)(datos.get(mitad - 1) + datos.get(mitad)) / 2;
    } else {
        return (double)datos.get(mitad);
    }
    }
    public static double calcularDesviacionEstandar(List<Integer> datos) {
    double media = calcularMedia(datos);
    double sumatoria = 0;
    for (int i = 0; i < datos.size(); i++) {
        sumatoria += Math.pow(datos.get(i) - media, 2);
    }
    double varianza = sumatoria / datos.size();
    return Math.sqrt(varianza);}
}


