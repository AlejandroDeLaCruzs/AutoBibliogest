package Ficheros;

import java.io.*;
import java.util.ArrayList;
import java.util.function.Function;

/**
 * Clase genérica para la gestión de lectura y escritura de objetos en ficheros.
 * Permite cargar y guardar objetos de tipo T en un archivo, utilizando una función para convertir las líneas del archivo en objetos.
 *
 * @param <T> El tipo de objeto que se manejará en los métodos de carga y escritura.
 */
public class FicheroGenerico<T> {
    private final Function<String[], T> creador;

    /**
     * Constructor de la clase FicheroGenerico.
     * Inicializa el creador que será utilizado para convertir las líneas del archivo en objetos.
     *
     * @param creador Función que convierte un array de cadenas en un objeto de tipo T.
     */
    public FicheroGenerico(Function<String[], T> creador) {
        this.creador = creador;
    }

    /**
     * Carga objetos de un archivo de texto en una lista.
     * Cada línea del archivo se convierte en un objeto utilizando el creador proporcionado.
     *
     * @param nombreArchivo El nombre del archivo desde el que se cargan los objetos.
     * @param objetos Lista donde se almacenarán los objetos cargados.
     */
    public void cargarFichero(String nombreArchivo, ArrayList<T> objetos) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = parchearCampos(linea);
                T objeto = creador.apply(partes);
                if (objeto != null) {
                    objetos.add(objeto);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Escribe una lista de objetos en un archivo de texto.
     * Cada objeto se convierte en su representación en texto utilizando el método toString().
     *
     * @param nombreArchivo El nombre del archivo donde se escribirán los objetos.
     * @param objetos Lista de objetos que se guardarán en el archivo.
     */
    public void escribirFichero(String nombreArchivo, ArrayList<T> objetos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (T objeto : objetos) {
                writer.write(objeto.toString());
                writer.newLine(); // Agrega una nueva línea después de cada objeto
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] parchearCampos(String linea){
        String[] partes = linea.split(",");

        return partes;
    }

}