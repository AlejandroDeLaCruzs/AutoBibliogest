package GUI;

import App.VentanaPrincipal;
import Core.Libro;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static GUI.Catalogo.crearpanelinfolibro;

/**
 * La clase {@code Busquedapanel} genera un panel que muestra los libros que cumplen con los criterios de búsqueda.
 * El panel contiene una lista de libros presentados en forma de tarjetas con su título, portada y un botón
 * para obtener más información.
 */
public class Busquedapanel extends JPanel {

    /**
     * Constructor de la clase {@code Busquedapanel}.
     * Este constructor crea un panel con una cuadrícula que contiene los libros encontrados según los criterios de búsqueda.
     *
     * @param librosencontrados Lista de libros encontrados que cumplen con los criterios de búsqueda.
     * @param ventanacontendor  Ventana principal que contiene este panel y proporciona contexto para las interacciones.
     */
    public Busquedapanel(ArrayList<Libro> librosencontrados, VentanaPrincipal ventanacontendor) {


        setLayout(new BorderLayout()); //Para que ocupe toda la pantalla

        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new GridLayout(6, 6));

        // Crear un JScrollPane que contenga el panelContenido
        JScrollPane scrollPane = new JScrollPane(panelContenido);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        agregarLibrosALaVista(librosencontrados, panelContenido, ventanacontendor);

        // Añadir el JScrollPane al panel principal
        add(scrollPane);

    }

    /**
     * Este método agrega los paneles de información de cada libro al panel de contenido.
     *
     * @param librosencontrados Lista de libros encontrados que se mostrarán en el panel.
     * @param panelContenido    El panel donde se agregan los libros.
     * @param ventanaPrincipal  La ventana principal que contiene este panel.
     */
    //LA VENTANA PRINCIPAL !!
    public void agregarLibrosALaVista(final ArrayList<Libro> librosencontrados, JPanel panelContenido, VentanaPrincipal ventanaPrincipal) {
        for (Libro libro : librosencontrados) {
            // Para cada libro, se crea un panel con la información y se agrega al panelContenido
            panelContenido.add(crearpanelinfolibro(libro, ventanaPrincipal));
        }
    }

}

