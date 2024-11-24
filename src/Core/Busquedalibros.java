package Core;

import GUI.VentanaPrincipal;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static Core.Libro.crearLibro;
import static GUI.Catalogo.crearpanelinfolibro;

/**
 * Esta clase contiene métodos para realizar la búsqueda de libros en un archivo de texto.
 * Los libros se filtran según las características proporcionadas por el usuario (autor, título y género).
 * Si se encuentra al menos un libro que cumpla con los criterios de búsqueda, se devuelve una lista de libros encontrados.
 */
public class Busquedalibros {

    /**
     * Este método busca en el fichero 'libros.txt' los libros que tienen
     * las características introducidas por el usuario. Si el usuario no ha
     * proporcionado todos los datos, se consideran como coincidencias.
     * Luego, se comparan los campos con los datos introducidos por el usuario.
     * Si se cumplen las tres características, se crea un panel para mostrar
     * la información del libro.
     *
     * @param autor            El autor del libro a buscar. Si el autor está vacío, no se filtra por autor.
     * @param titulo           El título del libro a buscar. Si el título está vacío, no se filtra por título.
     * @param genero           Un arreglo de géneros por los cuales filtrar los libros. Si está vacío o es nulo, no se filtra por género.
     * @return                 Una lista de libros que coinciden con los criterios de búsqueda. Si no se encuentra ningún libro, se retorna `null`.
     */

    public static ArrayList<Libro> busquedalibrofichero(String autor, String titulo, String[] genero) {
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

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (librosEncontrados.isEmpty()){
            return null;
        }
        return librosEncontrados;
    }


    /**
     * Método auxiliar que verifica si un libro coincide con los criterios de búsqueda.
     * Si el libro cumple con los tres criterios (autor, título y género), crea un objeto `Libro`.
     *
     * @param campos Los campos del libro extraídos del archivo (por ejemplo: título, autor, etc.).
     * @param autor El autor del libro a buscar.
     * @param titulo El título del libro a buscar.
     * @param generos Un arreglo de géneros para comparar con el libro.
     * @return Un objeto `Libro` si el libro coincide con los criterios de búsqueda, `null` si no hay coincidencia.
     */
    private static Libro coincideConBusqueda(String[] campos, String autor, String titulo, String[] generos) {
        boolean coincideTitulo = titulo.isEmpty() || campos[1].toLowerCase().contains(titulo.toLowerCase());
        boolean coincideAutor = autor.isEmpty() || campos[3].toLowerCase().contains(autor.toLowerCase());
        boolean coincideGenero = generos == null ||  generos.length == 0 || generoCoincide(campos[5], generos);

        if (coincideTitulo && coincideAutor && coincideGenero) {
            try {
                return crearLibro(campos);
            } catch (NumberFormatException e) {
                System.out.println("Error en el formato de números para el libro: " + String.join(",", campos));
            }
        }
        return null; // No coincide con los criterios de búsqueda
    }


    /**
     * Verifica si un género dado coincide con alguno de los géneros proporcionados.
     *
     * @param generoCampo El género del libro.
     * @param generos Un arreglo de géneros que se están buscando.
     * @return `true` si el género coincide con alguno de los géneros proporcionados, `false` en caso contrario.
     */
    private static boolean generoCoincide(String generoCampo, String[] generos) {
        for (String genero : generos) {
            if (generoCampo.equalsIgnoreCase(genero)) {
                return true;
            }
        }
        return false;
    }
}


