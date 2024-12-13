package GUI.DetallesLibro;

import Core.Libro;
import GUI.GeneroImagenes;

import javax.swing.*;
import java.awt.*;

public interface PanelLibroDetalleFactory {
    JPanel crearSubPanel(Libro libro);
}

// Fábrica para crear el panel de género
class PanelGeneroFactory implements PanelLibroDetalleFactory {
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
    private JLabel getGeneroImagen(Libro libro) {
        JLabel generoImagen = new JLabel();
        ImageIcon iconGenero = new ImageIcon(GeneroImagenes.getInstance().getImagePath(libro.getGenero()));
        Image imgGenero = iconGenero.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        generoImagen.setIcon(new ImageIcon(imgGenero));
        generoImagen.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar la imagenflecha en el subpanel
        return generoImagen;
    }
}

// Fábrica para crear el panel de año de publicación
class PanelAnioPublicacionFactory implements PanelLibroDetalleFactory {
    @Override
    public JPanel crearSubPanel(Libro libro) {
        JPanel publicadopanel = new JPanel();
        publicadopanel.setLayout(new BoxLayout(publicadopanel, BoxLayout.Y_AXIS));
        publicadopanel.setBackground(Color.white);
        publicadopanel.setBounds(610, 310, 90, 100);

        JLabel etiquetapublicado = new JLabel("PUBLICADO");
        etiquetapublicado.setForeground(Color.LIGHT_GRAY);
        etiquetapublicado.setAlignmentX(Component.CENTER_ALIGNMENT);
        publicadopanel.add(etiquetapublicado);

        publicadopanel.add(Box.createVerticalStrut(10));

        JLabel aniopublicado = new JLabel((libro.getAniopublicacion()));
        aniopublicado.setFont(new Font("Arial", Font.BOLD, 20));
        aniopublicado.setAlignmentX(Component.CENTER_ALIGNMENT);
        publicadopanel.add(aniopublicado);

        return publicadopanel;
    }
}

// Fábrica para crear el panel de idioma
class PanelIdiomaFactory implements PanelLibroDetalleFactory {
    @Override
    public JPanel crearSubPanel(Libro libro) {
        JPanel paneldioma = new JPanel();
        paneldioma.setLayout(new BoxLayout(paneldioma, BoxLayout.Y_AXIS));
        paneldioma.setBackground(Color.white);
        paneldioma.setBounds(740, 310, 130, 100);

        JLabel etiquetaidioma = new JLabel("IDIOMA");
        etiquetaidioma.setForeground(Color.LIGHT_GRAY);
        etiquetaidioma.setAlignmentX(Component.CENTER_ALIGNMENT);
        paneldioma.add(etiquetaidioma);

        paneldioma.add(Box.createVerticalStrut(10));

        JLabel idiomaabreviatura = new JLabel("Castellano");
        idiomaabreviatura.setFont(new Font("Arial", Font.BOLD, 20));
        idiomaabreviatura.setAlignmentX(Component.CENTER_ALIGNMENT);
        paneldioma.add(idiomaabreviatura);


        return paneldioma;
    }
}

class PanelCantidadPaginasFactory implements PanelLibroDetalleFactory {
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