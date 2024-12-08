package GUI;

import App.VentanaPrincipal;
import Core.Libro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Core.Libro.infoLibros;


/**
 * La clase {@code Catalogo} es un panel que contiene todos los libros de la biblioteca.
 * Los usuarios pueden seleccionar un libro mediante un botón para obtener información más detallada del libro.
 */
public class Catalogo extends JPanel {

    /**
     * Constructor de la clase {@code Catalogo}.
     * Este constructor crea un panel con una cuadrícula que contiene tarjetas con los libros disponibles.
     * Cada tarjeta tiene el título, la portada y un botón para obtener más detalles sobre el libro.
     *
     * @param ventanacontenedor La ventana principal que contiene este panel.
     */
    public Catalogo(VentanaPrincipal ventanacontenedor) {

        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());

        JPanel panelContenido = new JPanel();

        panelContenido.setLayout(new GridLayout(7, 9));

        // Crear un JScrollPane que contenga el panelContenido
        JScrollPane scrollPane = new JScrollPane(panelContenido);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Cargar los libros en el panel
        infoLibros(panelContenido, ventanacontenedor);

        // Añadir el JScrollPane al panel principal
        add(scrollPane);

    }

    /**
     * Crea un panel con la información de un libro, que incluye su título, portada y un botón para más detalles.
     *
     * @param libro             El objeto {@code Libro} que contiene la información del libro.
     * @param ventanacontenedor La ventana principal que contiene este panel.
     * @return Un panel con la información del libro, incluyendo un botón para más detalles.
     */
    public static JPanel crearpanelinfolibro(final Libro libro, VentanaPrincipal ventanacontenedor) {

        JPanel panelinfolibro = new JPanel();

        panelinfolibro.setSize(400, 400);
        panelinfolibro.setLayout(null);

        // Etiqueta para el título del libro
        JLabel titulo = new JLabel(libro.getTitulo());
        titulo.setBounds(100, 10, 300, 20);
        panelinfolibro.add(titulo);

        // Establecer el fondo del panel y su tamaño preferido

        panelinfolibro.setPreferredSize(new Dimension(250, 250));

        // Imagen del libro
        JLabel imagenLabel = new JLabel();
        ImageIcon imagenIcon = new ImageIcon(libro.getRutaimagen());
        Image imagen = imagenIcon.getImage();
        Image imagenEscalada = imagen.getScaledInstance(190, 190, Image.SCALE_SMOOTH);
        imagenLabel.setIcon(new ImageIcon(imagenEscalada));
        imagenLabel.setBounds(55, 50, 190, 190);
        imagenLabel.setIcon(new ImageIcon(imagenEscalada));
        panelinfolibro.add(imagenLabel);

        //Botón para obtener más información del libro
        JButton buttoninfodetallelibro = new JButton("Info");
        buttoninfodetallelibro.setEnabled(true);
        buttoninfodetallelibro.setBounds(350, 7, 50, 40);
        panelinfolibro.add(buttoninfodetallelibro);

        // Acción para mostrar los detalles del libro al hacer clic en el botón
        buttoninfodetallelibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear un panel con los detalles del libro y cambiar el panel visible
                DetallesLibro detallesLibroPanel = new DetallesLibro(ventanacontenedor, libro);
                ventanacontenedor.mostrarPanel(detallesLibroPanel, "paneldetalle");

            }
        });

        return panelinfolibro;
    }
}
