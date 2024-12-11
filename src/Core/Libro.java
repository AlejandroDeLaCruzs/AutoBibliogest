package Core;

import App.VentanaPrincipal;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Core.Prestamos.haydisponibilidad;
import static GUI.Catalogo.crearpanelinfolibro;
import static GUI.DetallesLibro.panelLibroRecomendado;

/**
 * Clase que representa un libro con atributos como ISBN, título, autor, etc.
 * Proporciona métodos para gestionar la información de los libros, como buscar y crear libros,
 * así como generar recomendaciones basadas en el género.
 */
public class Libro {
    private String ISBN;
    private String titulo;
    private String resumen;
    private String rutaimagen;
    private String autor;
    private String genero;
    private Integer cantidad;
    private String aniopublicacion;
    private Integer cantidadpaginas;

    // Métodos getters y setters

    /**
     * Obtiene la cantidad de páginas del libro.
     *
     * @return La cantidad de páginas.
     */
    public Integer getCantidadpaginas() {
        return cantidadpaginas;
    }

    /**
     * Establece la cantidad de páginas del libro.
     *
     * @param cantidadpaginas La cantidad de páginas a establecer.
     */
    public void setCantidadpaginas(Integer cantidadpaginas) {
        this.cantidadpaginas = cantidadpaginas;
    }

    /**
     * Obtiene el resumen del libro.
     *
     * @return El resumen del libro.
     */
    public String getResumen() {
        return resumen;
    }

    /**
     * Establece el resumen del libro.
     *
     * @param resumen El resumen a establecer.
     */
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    /**
     * Obtiene el año de publicación del libro.
     *
     * @return El año de publicación.
     */
    public String getAniopublicacion() {
        return aniopublicacion;
    }

    /**
     * Establece el año de publicación del libro.
     *
     * @param aniopublicacion El año de publicación a establecer.
     */
    public void setAniopublicacion(String aniopublicacion) {
        this.aniopublicacion = aniopublicacion;
    }

    /**
     * Obtiene la ruta de la imagen del libro.
     *
     * @return La ruta de la imagen.
     */
    public String getRutaimagen() {
        return rutaimagen;
    }

    /**
     * Establece la ruta de la imagen del libro.
     *
     * @param rutaimagen La ruta de la imagen a establecer.
     */
    public void setRutaimagen(String rutaimagen) {
        this.rutaimagen = rutaimagen;
    }

    /**
     * Obtiene el género del libro.
     *
     * @return El género del libro.
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Establece el género del libro.
     *
     * @param genero El género a establecer.
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Obtiene el autor del libro.
     *
     * @return El autor del libro.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Establece el autor del libro.
     *
     * @param autor El autor a establecer.
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Obtiene el título del libro.
     *
     * @return El título del libro.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título del libro.
     *
     * @param titulo El título a establecer.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene la cantidad de copias del libro.
     *
     * @return La cantidad de copias del libro.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de copias del libro.
     *
     * @param cantidad La cantidad a establecer.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Constructor para inicializar un libro con los atributos proporcionados.
     *
     * @param ISBN            El ISBN del libro.
     * @param titulo          El título del libro.
     * @param resumen         El resumen del libro.
     * @param autor           El autor del libro.
     * @param aniopublicacion El año de publicación del libro.
     * @param genero          El género del libro.
     * @param cantidadpaginas La cantidad de páginas del libro.
     * @param cantidad        La cantidad de copias disponibles del libro.
     * @param rutaimagen      La ruta de la imagen del libro.
     */
    public Libro(String ISBN, String titulo, String resumen, String autor, String aniopublicacion, String genero, Integer cantidadpaginas, Integer cantidad, String rutaimagen) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.resumen = resumen;
        this.autor = autor;
        this.aniopublicacion = aniopublicacion;
        this.genero = genero;
        this.cantidad = cantidad;
        this.rutaimagen = rutaimagen;
        this.cantidadpaginas = cantidadpaginas;

    }

    /**
     * Representa el libro como una cadena de texto, mostrando su título, autor, género y resumen.
     *
     * @return Una cadena con la información del libro.
     */
    @Override
    public String toString() {
        // Concatenar los atributos de la clase separados por comas
        return ISBN + "," + titulo + "," + resumen + "," + autor + ","
                + aniopublicacion + "," + genero + "," + cantidadpaginas + ","
                + cantidad + "," + rutaimagen;
    }

    /**
     * Lee un archivo de texto con los libros y añade un panel con los datos de cada libro
     * al panel proporcionado.
     *
     * @param panel             El panel donde se añadirán los libros.
     * @param ventanacontenedor La ventana principal que contiene el panel.
     */
    public static void infoLibros(JPanel panel, VentanaPrincipal ventanacontenedor, Biblioteca biblioteca) {
        for (Libro libro : biblioteca.getLibros()) {
            panel.add(crearpanelinfolibro(libro, ventanacontenedor, biblioteca));
        }
    }


    /**
     * Busca un libro en el archivo, y devuelve el siguiente o anterior libro según el parámetro.
     *
     * @param libroActual     El libro actual para buscar.
     * @param buscarSiguiente Si es true, devuelve el siguiente libro, de lo contrario el anterior.
     * @return El libro siguiente o anterior, o null si no se encuentra.
     */
    public static Libro buscarLibro(Libro libroActual, boolean buscarSiguiente, Biblioteca biblioteca) {
        List<Libro> libros = biblioteca.getLibros();

        for (int i = 0; i < biblioteca.getLibros().size(); i++) {
                // Si encontramos el libro actual
            if (libros.get(i).equals(libroActual)) {
                // Verificar límites para evitar IndexOutOfBoundsException
                if (buscarSiguiente && i < libros.size() - 1) {
                    return libros.get(i + 1);
                } else if (!buscarSiguiente && i > 0) {
                    return libros.get(i - 1);
                } else {
                    // No hay libro siguiente o anterior según el caso
                    return null;
                }
            }
        }
        return null;
    }




    /**
     * Crea un objeto Libro a partir de un array de partes extraído de una línea de archivo.
     *
     * @param partes Los datos del libro en forma de un array de cadenas.
     * @return Un objeto Libro creado con los datos proporcionados.
     */
    public static Libro crearLibro(String[] partes) {
        if (partes.length == 9) {
            int copias = Integer.parseInt(partes[7]);
            int cantidadpaginas = Integer.parseInt(partes[6]);
            return new Libro(partes[0], partes[1], partes[2], partes[3], partes[4], partes[5], cantidadpaginas, copias, partes[8]);
        }
        return null;
    }


    public static List<Libro> librosRecomendados(Libro libroActual, List<Libro> libros) {
        List<Libro> librosRecomendados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getGenero().equals(libroActual.getGenero())
                    && !libro.getTitulo().equals(libroActual.getTitulo())
                    && haydisponibilidad(libro)) { // Asumiendo que haydisponibilidad(Libro) verifica la disponibilidad
                librosRecomendados.add(libro);
            }
        }
        return librosRecomendados;
    }

}
