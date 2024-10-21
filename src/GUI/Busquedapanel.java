package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static GUI.Catalogo.crearpanelinfolibro;

/**
 * La clase BusquedaPanel genera un panel con los libros que tengan las características que indicamos en la busqueda.
 */
public class Busquedapanel extends JPanel {

    /**
     * El panel BusquedaPanel  contendrá a un JscrollPane que a su vez contendrá unos
     * paneles que se iran ubicando de manera horizontal y vertical. Además en esto paneles
     * se podrá ver el titulo, la portada del libro y un boton para obtener mas informacion.
     *
     * @param autor            El autor del libro a buscar.
     * @param titulo           El título del libro a buscar.
     * @param genero           El género del libro a buscar.
     * @param ventanacontendor La ventana que contiene este panel.
     */
    public Busquedapanel(String autor, String titulo, String genero, Ventana ventanacontendor) {

        setLayout(new BorderLayout()); //Para que ocupe toda la pantalla

        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new GridLayout(3, 3));

        // Crear un JScrollPane que contenga el panelContenido
        JScrollPane scrollPane = new JScrollPane(panelContenido);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        busquedalibrofichero(panelContenido, autor, titulo, genero, ventanacontendor);

        // Añadir el JScrollPane al panel principal
        add(scrollPane);

    }

    /**
     * Este métatdo busca en el fichero libros.txt los libros que tienen
     * las características introducidas por el usuario. Si el usuario no ha
     * proporcionado todos los datos, se consideran como coincidencias.
     * Luego, se comparan los campos con los datos introducidos por el usuario.
     * Si se cumplen las tres características, se crea un panel para mostrar
     * la información del libro.
     *
     * @param panelcontindo    El panel donde se añadirán los resultados de la búsqueda.
     * @param autor            El autor del libro a buscar.
     * @param titulo           El título del libro a buscar.
     * @param genero           El género del libro a buscar.
     * @param ventanacontendor La ventana que contiene este panel.
     */

    public static void busquedalibrofichero(JPanel panelcontindo, String autor, String titulo, String genero, Ventana ventanacontendor) {
        int contador = 0; //Para ver si existe al menos una coincidencia.

        //Abrimos el fichero donde tenemos todos los datos del los libros
        try (BufferedReader reader = new BufferedReader(new FileReader("libros.txt"))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(","); //Seperamos el contenido
                boolean coincideTitulo = false;
                boolean coincideAutor = false;
                boolean coincideGenero = false;

                if (titulo.isEmpty()) {
                    coincideTitulo = true; //Si esta empty no tenemos en cuenta el titulo
                }
                if (autor.isEmpty()) {
                    coincideAutor = true; //Si esta empty no tenemos en cuenta el autor
                }
                if (genero.isEmpty()) {
                    coincideGenero = true;  //Si esta empty no tenemos en cuenta el genero
                }
                if (campos.length == 8) { //Comparamos los datos del libro con lo que ha introducido el usuario
                    if (titulo.equalsIgnoreCase(campos[1])) {
                        coincideTitulo = true;
                    }
                    if (campos[3].equalsIgnoreCase(autor)) {
                        coincideAutor = true;
                    }

                    if (campos[5].equals(genero)) {
                        coincideGenero = true;
                    }
                }

                //Si se cumplen las tres condicidones hemos encontrado un libro que cuadra con las caracteriticas de la busqueda
                if (coincideTitulo && coincideAutor && coincideGenero && campos.length == 8) {

                    int copias = Integer.parseInt(campos[6]);
                    int aniopublicacion = Integer.parseInt(campos[4]);
                    Libro libro = new Libro(campos[0], campos[1], campos[2], campos[3], aniopublicacion, campos[5], copias, campos[7]); //Creamos el libro
                    panelcontindo.add(crearpanelinfolibro(libro, ventanacontendor)); //LLamamos a un meteod de la Clase Catalogo para crear el panel
                    contador++;
                }
            }
            if (contador == 0) {
                JOptionPane.showMessageDialog(null, "No se ha encontrado ningún libro ");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

