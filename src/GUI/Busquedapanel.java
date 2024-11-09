package GUI;

import Core.Libro;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static Core.Busquedalibros.busquedalibrofichero;
import static GUI.Catalogo.crearpanelinfolibro;

/**
 * La clase BusquedaPanel genera un panel con los libros que tengan las características que indicamos en la busqueda.
 */
public class Busquedapanel extends JPanel {
    /**
     * El panel BusquedaPanel contendrá a un JscrollPane que a su vez contendrá unos
     * paneles que se iran ubicando de manera horizontal y vertical. Además en esto paneles
     * se podrá ver el título, la portada del libro y un botón para obtener mas información.
     *
     * @param autor            El autor del libro a buscar.
     * @param titulo           El título del libro a buscar.
     * @param genero           El género del libro a buscar.
     * @param ventanacontendor La ventana que contiene este panel.
     */
    public Busquedapanel(String autor, String titulo, String genero, VentanaPrincipal ventanacontendor) {
        ArrayList<Libro> librosencontrados;

        setLayout(new BorderLayout()); //Para que ocupe toda la pantalla

        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new GridLayout(6, 6));

        // Crear un JScrollPane que contenga el panelContenido
        JScrollPane scrollPane = new JScrollPane(panelContenido);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        librosencontrados = busquedalibrofichero(autor, titulo, genero, ventanacontendor);
        librosbuscadospanel(librosencontrados, panelContenido, ventanacontendor);

        // Añadir el JScrollPane al panel principal
        add(scrollPane);

    }

    public void librosbuscadospanel(final ArrayList<Libro> librosencontrados, JPanel panelcontido, VentanaPrincipal ventanaPrincipal) {
        for (Libro libro : librosencontrados) {
            panelcontido.add(crearpanelinfolibro(libro, ventanaPrincipal));
        }
    }
}

