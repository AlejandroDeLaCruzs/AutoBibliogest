package GUI;

import App.VentanaPrincipal;
import Core.Biblioteca;
import Core.Libro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/**
 * Clase que representa el catálogo de libros en la biblioteca dentro de la interfaz gráfica.
 * Los usuarios pueden seleccionar un libro mediante un botón para obtener más información detallada.
 */
public class PanelCatalogoLibros extends JPanel {

    /**
     * Constructor de la clase {@code PanelCatalogoLibros}.
     * Este constructor configura un panel con una cuadrícula que contiene tarjetas con los libros disponibles.
     * Cada tarjeta incluye el título, la portada y un botón para obtener más detalles sobre el libro.
     *
     * @param ventanaContenedor La ventana principal que contiene este panel.
     * @param biblioteca        La biblioteca que contiene los libros a mostrar.
     */
    public PanelCatalogoLibros(VentanaPrincipal ventanaContenedor, Biblioteca biblioteca) {
        inicializarPanel();

        JPanel panelContenidoLibros = new JPanel();

        panelContenidoLibros.setLayout(new GridLayout(7, 9));

        // Crear un JScrollPane que contenga el panelContenido
        JScrollPane scrollPaneLibros = new JScrollPane(panelContenidoLibros);
        scrollPaneLibros.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Cargar los libros en el panel
        infoLibros(panelContenidoLibros, ventanaContenedor, biblioteca);

        // Añadir el JScrollPane al panel principal
        add(scrollPaneLibros);

    }

    /**
     * Configura las propiedades iniciales del panel, como el diseño y el color de fondo.
     */
    private void inicializarPanel() {
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());
    }



    /**
     * Crea un panel con la información de un libro, que incluye su título, portada y un botón para más detalles.
     *
     * @param libro             El objeto {@code Libro} que contiene la información del libro.
     * @param ventanaContenedor La ventana principal que contiene este panel.
     * @param biblioteca        La biblioteca que contiene los datos adicionales del libro.
     * @return Un panel con la información del libro, incluyendo un botón para más detalles.
     */
    public static JPanel crearPanelInfoLibro(final Libro libro, VentanaPrincipal ventanaContenedor, Biblioteca biblioteca) {
        JPanel panelInfoLibro = new JPanel();

        // Configuración del diseño y dimensiones del panel
        panelInfoLibro.setSize(400, 400);
        panelInfoLibro.setLayout(null);
        panelInfoLibro.setPreferredSize(new Dimension(250, 250));

        // Etiqueta para el título del libro
        JLabel titulo = new JLabel(libro.getTitulo());
        titulo.setBounds(100, 10, 300, 20);
        panelInfoLibro.add(titulo);

        // Imagen del libro
        JLabel imagenLabel = new JLabel();
        ImageIcon imagenIcon = new ImageIcon(libro.getRutaimagen());
        Image imagen = imagenIcon.getImage();
        Image imagenEscalada = imagen.getScaledInstance(190, 190, Image.SCALE_SMOOTH);
        imagenLabel.setIcon(new ImageIcon(imagenEscalada));
        imagenLabel.setBounds(55, 50, 190, 190);
        imagenLabel.setIcon(new ImageIcon(imagenEscalada));
        panelInfoLibro.add(imagenLabel);

        //Botón para obtener más información del libro
        JButton buttonInfoDetalleLibro = new JButton("Info");
        buttonInfoDetalleLibro.setEnabled(true);
        buttonInfoDetalleLibro.setBounds(350, 7, 50, 40);
        panelInfoLibro.add(buttonInfoDetalleLibro);

        // Acción para mostrar los detalles del libro al hacer clic en el botón
        buttonInfoDetalleLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear un panel con los detalles del libro y cambiar el panel visible
                DetallesLibro detallesLibroPanel = new DetallesLibro(ventanaContenedor, libro, biblioteca);
                ventanaContenedor.mostrarPanel(detallesLibroPanel, "paneldetalle");

            }
        });

        return panelInfoLibro;
    }

    /**
     * Lee un archivo de texto con los libros y añade un panel con los datos de cada libro
     * al panel proporcionado.
     *
     * @param panel             El panel donde se añadirán los libros.
     * @param ventanacontenedor La ventana principal que contiene el panel.
     */
    private static void infoLibros(JPanel panel, VentanaPrincipal ventanacontenedor, Biblioteca biblioteca) {
        for (Libro libro : biblioteca.getLibros()) {
            panel.add(crearPanelInfoLibro(libro, ventanacontenedor, biblioteca));
        }
    }


}
