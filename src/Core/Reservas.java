package Core;

import GUI.VentanaPrincipal;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static GUI.PanelMisReservas.panellibroreservado;

public class Reservas {

    public static void misreservas(VentanaPrincipal ventanacontenedor, JPanel panel) {
        try (BufferedReader reader = new BufferedReader(new FileReader("prestamos.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length == 4) {
                    if (campos[0].equals(ventanacontenedor.getUsuarioActivo().getCorreo())) {
                        panel.add(panellibroreservado(campos));
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] leerUltimaLinea() {
        String[] camposultimalinea   = null;

        try (BufferedReader br = new BufferedReader(new FileReader("prestamos.txt"))) {
            String linea;
            String ultimaLinea = null; // Variable para almacenar la última línea válida
            while ((linea = br.readLine()) != null) {
                ultimaLinea = linea; // Actualiza la última línea leída
            }

            // Si hay una última línea, la dividimos en campos
            if (ultimaLinea != null) {
                camposultimalinea = ultimaLinea.split(","); // Divide la última línea en campos
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return camposultimalinea; // Retorna el arreglo de campos de la última línea
    }

}
