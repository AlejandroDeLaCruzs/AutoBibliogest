package Core;

import App.VentanaPrincipal;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static GUI.PanelMisReservas.panellibroreservado;

/**
 * Clase que gestiona las reservas de libros realizadas por los usuarios.
 * Proporciona métodos para mostrar las reservas de un usuario y leer la última línea del archivo de préstamos.
 */
abstract public class Reservas {

    public static ArrayList<Prestamos> misPrestamos(ArrayList<Prestamos> prestamos, Usuario usuarioActivo) {
        ArrayList<Prestamos> misPrestamos = new ArrayList<>();
        for (Prestamos prestamo : prestamos) {
            if (prestamo.getCorreo().equals(usuarioActivo.getCorreo())) {
                misPrestamos.add(prestamo);
            }
        }
        return misPrestamos;
    }

    /**
     * Lee y retorna la última línea del archivo `prestamos.txt` como un arreglo de campos.
     *
     * @return Un arreglo de cadenas con los campos de la última línea, o {@code null} si no hay líneas.
     */
    public static Prestamos ultimoPrestamo(ArrayList<Prestamos> prestamos) {
        return prestamos.getLast();
    }
}