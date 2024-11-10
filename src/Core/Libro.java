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

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public int getAniopublicacion() {
        return aniopublicacion;
    }

    public void setAniopublicacion(int aniopublicacion) {
        this.aniopublicacion = aniopublicacion;
    }

    private int aniopublicacion;

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

    public Libro(String ISBN, String titulo, String resumen, String autor, Integer aniopublicacion, String genero, Integer cantidad, String rutaimagen) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.resumen = resumen;
        this.autor = autor;
        this.aniopublicacion = aniopublicacion;
        this.genero = genero;
        this.cantidad = cantidad;
        this.rutaimagen = rutaimagen;
    }

    @Override
    public String toString() {
        return "Titulo: " + this.titulo +
                ", Autor: " + this.autor +
                ", Genero: " + this.genero +
                ", Resumen: " + this.resumen;
    }

   /* public void mostrarInfo() {
        out.println(this);
    }*/

    /**
     * Metodo que lee el fichero y llama al metedo para crear un panel con los datos del libro
     * y los añade al PanelContendenor.
     */
    public static void infolibros(JPanel panel, VentanaPrincipal ventanacontenedor) {
        try (BufferedReader reader = new BufferedReader(new FileReader("libros.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");

                if (partes.length == 8) {
                    int copias = Integer.parseInt(partes[6]);
                    int aniopublicacion = Integer.parseInt(partes[4]);
                    Libro libro = new Libro(partes[0], partes[1], partes[2], partes[3], aniopublicacion, partes[5], copias, partes[7]);
                    panel.add(crearpanelinfolibro(libro, ventanacontenedor));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
