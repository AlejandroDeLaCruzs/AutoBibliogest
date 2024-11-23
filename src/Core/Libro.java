package Core;

import GUI.VentanaPrincipal;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static GUI.Catalogo.crearpanelinfolibro;

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

    public Integer getCantidadpaginas() {
        return cantidadpaginas;
    }

    public void setCantidadpaginas(Integer cantidadpaginas) {
        this.cantidadpaginas = cantidadpaginas;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getAniopublicacion() {
        return aniopublicacion;
    }

    public void setAniopublicacion(String aniopublicacion) {
        this.aniopublicacion = aniopublicacion;
    }

    public String getRutaimagen() {
        return rutaimagen;
    }

    public void setRutaimagen(String rutaimagen) {
        this.rutaimagen = rutaimagen;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Libro(String ISBN, String titulo, String resumen, String autor, String aniopublicacion, String genero,Integer cantidadpaginas, Integer cantidad,  String rutaimagen) {
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

    @Override
    public String toString() {
        return "Titulo: " + this.titulo +
                ", Autor: " + this.autor +
                ", Genero: " + this.genero +
                ", Resumen: " + this.resumen;
    }


    /**
     * Metodo que lee el fichero y llama al metedo para crear un panel con los datos del libro
     * y los añade al PanelContendenor.
     */
    public static void infolibros(JPanel panel, VentanaPrincipal ventanacontenedor) {
        try (BufferedReader reader = new BufferedReader(new FileReader("libros.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");

                if (partes.length == 9) {
                    panel.add(crearpanelinfolibro(crearLibro(partes), ventanacontenedor));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Libro buscarLibro(Libro libroActual, boolean buscarSiguiente) {
        try (BufferedReader reader = new BufferedReader(new FileReader("libros.txt"))) {
            String linea;
            String[] partesAnterior = null; // Guardar datos del libro previo

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");

                if (partes.length == 9) {
                    // Si encontramos el libro actual
                    if (partes[1].equals(libroActual.titulo)) {
                        if (buscarSiguiente) {
                            return obtenerSiguienteLibro(reader);
                        } else {
                            return obtenerLibroAnterior(partesAnterior);
                        }

                    }

                    // Actualizar el libro previo antes de avanzar a la siguiente línea
                    partesAnterior = partes;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Libro obtenerSiguienteLibro(BufferedReader reader) throws IOException {
        String linea = reader.readLine();  // Leer la siguiente línea
        if (linea != null) {
            String[] partesSiguiente = linea.split(",");
            return crearLibro(partesSiguiente);
        }
        return null;  // No hay siguiente libro
    }

    private static Libro obtenerLibroAnterior(String[] partesAnterior) {
        if (partesAnterior != null) {
            return crearLibro(partesAnterior);
        }
        return null;  // No hay libro anterior
    }

    public static Libro crearLibro(String[] partes) {
        int copias = Integer.parseInt(partes[7]);
        int cantidadpaginas = Integer.parseInt(partes[6]);
        return new Libro(partes[0], partes[1], partes[2], partes[3], partes[4], partes[5], cantidadpaginas, copias, partes[8]);
    }


}
