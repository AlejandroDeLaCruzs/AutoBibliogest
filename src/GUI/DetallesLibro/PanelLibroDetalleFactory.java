package GUI.DetallesLibro;

import Core.Libro;
import GUI.GeneroImagenes;

import javax.swing.*;
import java.awt.*;

/**
 * Interfaz que define la fábrica para crear subpaneles de detalles de un libro.
 */
@FunctionalInterface
public interface PanelLibroDetalleFactory {
    /**
     * Crea un subpanel con información específica de un libro.
     *
     * @param libro El libro cuyos detalles se mostrarán.
     * @return Un JPanel que contiene la información correspondiente.
     */

    JPanel crearSubPanel(Libro libro);
}

/**
 * Fábrica para crear un subpanel que muestra el género de un libro.
 */
class PanelGeneroFactory implements PanelLibroDetalleFactory {

    /**
     * {@inheritDoc}
     * Este subpanel incluye el nombre y una imagen representativa del género del libro.
     */
    @Override
    public JPanel crearSubPanel(Libro libro) {
        JPanel generoPanel = new JPanel();
        generoPanel.setLayout(new BoxLayout(generoPanel, BoxLayout.Y_AXIS)); // Configurar BoxLayout (vertical)
        generoPanel.setBounds(390, 310, 180, 100); // Posición y tamaño del subpanel
        generoPanel.setBackground(Color.WHITE);

        JLabel genero = new JLabel("GÉNERO");
        genero.setForeground(Color.LIGHT_GRAY);
        genero.setAlignmentX(Component.CENTER_ALIGNMENT);
        generoPanel.add(genero);
        generoPanel.add(Box.createVerticalStrut(3));

        // Imagen del género
        JLabel generoImagen = getGeneroImagen(libro);
        generoPanel.add(generoImagen); // Añadir la imagenflechaderechs al subpanel

        // Espaciador entre la imagenflechaderechs y el texto
        generoPanel.add(Box.createVerticalStrut(7));

        // Texto del género
        JLabel generoTexto = new JLabel(libro.getGenero());
        generoTexto.setFont(new Font("Arial", Font.BOLD, 14));
        generoTexto.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el texto en el subpanel
        generoTexto.setPreferredSize(new Dimension(200, 40));
        generoPanel.add(generoTexto); // Añadir el texto al subpanel

        return generoPanel;
    }

    /**
     * Obtiene una etiqueta con la imagen correspondiente al género del libro.
     *
     * @param libro El libro cuyos detalles se están mostrando.
     * @return Un JLabel que contiene la imagen escalada del género.
     */
    private JLabel getGeneroImagen(Libro libro) {
        JLabel generoImagen = new JLabel();
        ImageIcon iconGenero = new ImageIcon(GeneroImagenes.getInstance().getImagePath(libro.getGenero()));
        Image imgGenero = iconGenero.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        generoImagen.setIcon(new ImageIcon(imgGenero));
        generoImagen.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar la imagenflecha en el subpanel
        return generoImagen;
    }
}

/**
 * Fábrica para crear un subpanel que muestra el año de publicación de un libro.
 */
class PanelAnioPublicacionFactory implements PanelLibroDetalleFactory {

    /**
     * {@inheritDoc}
     * Este subpanel incluye una etiqueta con el año de publicación del libro.
     */
    @Override
    public JPanel crearSubPanel(Libro libro) {
        JPanel panelPublicado = new JPanel();
        panelPublicado.setLayout(new BoxLayout(panelPublicado, BoxLayout.Y_AXIS));
        panelPublicado.setBackground(Color.white);
        panelPublicado.setBounds(610, 310, 90, 100);

        JLabel etiquetapublicado = new JLabel("PUBLICADO");
        etiquetapublicado.setForeground(Color.LIGHT_GRAY);
        etiquetapublicado.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPublicado.add(etiquetapublicado);

        panelPublicado.add(Box.createVerticalStrut(10));

        JLabel anioPublicado = new JLabel((libro.getAniopublicacion()));
        anioPublicado.setFont(new Font("Arial", Font.BOLD, 20));
        anioPublicado.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPublicado.add(anioPublicado);

        return panelPublicado;
    }
}

/**
 * Fábrica para crear un subpanel que muestra el idioma del libro.
 */
class PanelIdiomaFactory implements PanelLibroDetalleFactory {

    /**
     * {@inheritDoc}
     * Este subpanel incluye una etiqueta con el idioma principal del libro.
     */
    @Override
    public JPanel crearSubPanel(Libro libro) {
        JPanel panelIdioma = new JPanel();
        panelIdioma.setLayout(new BoxLayout(panelIdioma, BoxLayout.Y_AXIS));
        panelIdioma.setBackground(Color.white);
        panelIdioma.setBounds(740, 310, 130, 100);

        JLabel etiquetaidioma = new JLabel("IDIOMA");
        etiquetaidioma.setForeground(Color.LIGHT_GRAY);
        etiquetaidioma.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelIdioma.add(etiquetaidioma);

        panelIdioma.add(Box.createVerticalStrut(10));

        JLabel idioma = new JLabel("Castellano");
        idioma.setFont(new Font("Arial", Font.BOLD, 20));
        idioma.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelIdioma.add(idioma);


        return panelIdioma;
    }
}

/**
 * Fábrica para crear un subpanel que muestra la cantidad de páginas de un libro.
 */
class PanelCantidadPaginasFactory implements PanelLibroDetalleFactory {

    /**
     * {@inheritDoc}
     * Este subpanel incluye una etiqueta con la cantidad de páginas y una descripción.
     */
    @Override
    public JPanel crearSubPanel(Libro libro) {
        JPanel panelCantidadPags = new JPanel();
        panelCantidadPags.setLayout(new BoxLayout(panelCantidadPags, BoxLayout.Y_AXIS));
        panelCantidadPags.setBackground(Color.white);
        panelCantidadPags.setBounds(920, 310, 90, 100);

        JLabel etiquetaCantidadPags = new JLabel("EXTENSIÓN");
        etiquetaCantidadPags.setForeground(Color.LIGHT_GRAY);
        etiquetaCantidadPags.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCantidadPags.add(etiquetaCantidadPags);

        panelCantidadPags.add(Box.createVerticalStrut(6));

        JLabel cantidadPags = new JLabel(libro.getCantidadpaginas().toString());
        cantidadPags.setFont(new Font("Arial", Font.BOLD, 20));
        cantidadPags.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCantidadPags.add(cantidadPags);

        JLabel etiquetaPaginas = new JLabel("páginas");
        etiquetaPaginas.setForeground(Color.LIGHT_GRAY);
        etiquetaPaginas.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCantidadPags.add(etiquetaPaginas);

        return panelCantidadPags;
    }
}