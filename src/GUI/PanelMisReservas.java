package GUI;

import App.VentanaPrincipal;
import Core.Prestamos;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static Core.Reservas.*;

public class PanelMisReservas extends JPanel {
    private JScrollPane scrollPane;
    private JPanel panelContenedor;

    public PanelMisReservas(VentanaPrincipal ventanacontenedor, ArrayList<Prestamos> prestamos) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        panelContenedor = new JPanel();
        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));

        misReservas(ventanacontenedor, panelContenedor);

        scrollPane = new JScrollPane(panelContenedor);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane);  // Directamente agregar el scrollPane
        setPreferredSize(new Dimension(400, 400));


    }

    public static JPanel panellibroreservado(String[] datosreserva) {

        JPanel panelReserva = new JPanel();
        panelReserva.setLayout(new BoxLayout(panelReserva, BoxLayout.X_AXIS));


        panelReserva.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel para la imagen del libro y el título
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));

        // Etiqueta de título del libro
        JLabel titulo = new JLabel(datosreserva[1]);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        panelIzquierdo.add(titulo);

        // Espacio entre título y la imagen
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0, 10)));

        // Imagen del libro
        JLabel imagenLabel = new JLabel();
        ImageIcon imagenIcon = new ImageIcon(rutaImagen(datosreserva[1]));
        Image imagen = imagenIcon.getImage();
        Image imagenEscalada = imagen.getScaledInstance(200, 200, Image.SCALE_SMOOTH);  // Tamaño ajustado de imagen
        imagenLabel.setIcon(new ImageIcon(imagenEscalada));
        imagenLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelIzquierdo.add(imagenLabel);

        // Añadir panel izquierdo al panel principal
        panelReserva.add(panelIzquierdo);

        // Espacio entre la imagen y el panel de fechas
        panelReserva.add(Box.createRigidArea(new Dimension(20, 0)));

        // Crear el Box para las fechas en el lado derecho
        Box boxFechas = Box.createVerticalBox();

        JLabel fechaInicioLabel = new JLabel("Inicio: " + datosreserva[2]);
        JLabel fechaVencimientoLabel = new JLabel("Vencimiento: " + datosreserva[3]);

        fechaInicioLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        fechaVencimientoLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        // Agregar etiquetas de fechas al Box
        boxFechas.add(fechaInicioLabel);
        boxFechas.add(Box.createRigidArea(new Dimension(0, 5))); // Espacio entre las fechas
        boxFechas.add(fechaVencimientoLabel);

        // Añadir boxFechas al lado derecho del panelReserva
        panelReserva.add(boxFechas);

        return panelReserva;
    }

    public void agregarReserva(VentanaPrincipal ventanaPrincipal) {
        String[] datosreserva = leerUltimaLinea();
        if (datosreserva == null || datosreserva.length < 4) {
            System.err.println("No se pudo leer datos válidos de la última reserva.");
            return; // No continuar si los datos no son válidos
        }

        JPanel panelReserva = panellibroreservado(datosreserva);
        panelContenedor.add(panelReserva);

        // Refrescar el contenedor y desplazar el JScrollPane al final
        panelContenedor.revalidate();
        panelContenedor.repaint();
        SwingUtilities.invokeLater(() -> {
            JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
            verticalScrollBar.setValue(verticalScrollBar.getMaximum()); // Desplazar al final
        });

    }

    private static String rutaImagen(String tituloLibro) {
        return "./res/" + tituloLibro + ".jpg";
    }
}

