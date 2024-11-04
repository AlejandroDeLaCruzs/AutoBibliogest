package Core;

import GUI.VentanaPrincipal;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static GUI.Catalogo.crearpanelinfolibro;

public class Busquedalibros {

    /**
     * Este método busca en el fichero libros.txt los libros que tienen
     * las características introducidas por el usuario. Si el usuario no ha
     * proporcionado todos los datos, se consideran como coincidencias.
     * Luego, se comparan los campos con los datos introducidos por el usuario.
     * Si se cumplen las tres características, se crea un panel para mostrar
     * la información del libro.
     *
    // * @param panelcontindo    El panel donde se añadirán los resultados de la búsqueda.
     * @param autor            El autor del libro a buscar.
     * @param titulo           El título del libro a buscar.
     * @param genero           El género del libro a buscar.
     * @param ventanacontendor La ventana que contiene este panel.
     */

    public static ArrayList<Libro> busquedalibrofichero(String autor, String titulo, String genero, VentanaPrincipal ventanacontendor) {
        int contador = 0; //Para ver si existe al menos una coincidencia.
        ArrayList<Libro> librosEncontrados = new ArrayList<>();
        //Abrimos el fichero donde tenemos todos los datos del los libros
        try (BufferedReader reader = new BufferedReader(new FileReader("libros.txt"))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(","); //Seperamos el contenido

                Libro libro = coincideConBusqueda(campos, autor, titulo, genero);
                if (libro != null) {
                    librosEncontrados.add(libro);
                }
            }
            if (librosEncontrados.isEmpty()) {
                JOptionPane.showMessageDialog(ventanacontendor, "No se ha encontrado ningún libro");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return librosEncontrados;
    }


    // Método auxiliar que verifica coincidencias y crea un libro si cumple con los criterios
    private static Libro coincideConBusqueda(String[] campos, String autor, String titulo, String genero) {
        boolean coincideTitulo = titulo.isEmpty() || campos[1].toLowerCase().contains(titulo.toLowerCase());
        boolean coincideAutor = autor.isEmpty() || campos[3].toLowerCase().contains(autor.toLowerCase());
        boolean coincideGenero = genero.isEmpty() || campos[5].toLowerCase().contains(genero.toLowerCase());

        if (coincideTitulo && coincideAutor && coincideGenero) {
            try {
                int copias = Integer.parseInt(campos[6]);
                int anioPublicacion = Integer.parseInt(campos[4]);
                return new Libro(campos[0], campos[1], campos[2], campos[3], anioPublicacion, campos[5], copias, campos[7]);
            } catch (NumberFormatException e) {
                System.out.println("Error en el formato de números para el libro: " + String.join(",", campos));
            }
        }
        return null; // No coincide con los criterios de búsqueda
    }
}
