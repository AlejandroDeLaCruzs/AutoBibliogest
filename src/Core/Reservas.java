package Core;

import GUI.VentanaPrincipal;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static GUI.PanelMisReservas.panellibroreservado;

/**
 * Clase que gestiona las reservas de libros realizadas por los usuarios.
 * Proporciona métodos para mostrar las reservas de un usuario y leer la última línea del archivo de préstamos.
 */
abstract public class Reservas {

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

    /**
     * Lee y retorna la última línea del archivo `prestamos.txt` como un arreglo de campos.
     *
     * @return Un arreglo de cadenas con los campos de la última línea, o {@code null} si no hay líneas.
     */
    public static String[] leerUltimaLinea() {
        String[] camposultimalinea   = null;

        try (BufferedReader br = new BufferedReader(new FileReader("prestamos.txt"))) {
            String linea;
            String ultimaLinea = null;
            while ((linea = br.readLine()) != null) {
                ultimaLinea = linea;
            }

            // Divide la última línea en campos si existe
            if (ultimaLinea != null) {
                camposultimalinea = ultimaLinea.split(",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return camposultimalinea;
    }

}
