package Ficheros;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

public class FicheroGenerico<T> {
    private final Function<String[], T> creador;

    public FicheroGenerico(Function<String[], T> creador) {
        this.creador = creador;
    }

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


    public void escribirFichero(String nombreArchivo, ArrayList<T> objetos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
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