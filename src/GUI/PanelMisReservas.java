package GUI;

import App.VentanaPrincipal;
import Core.Prestamos;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static Core.Reservas.*;

/**
 * Clase que representa el panel de visualización de las reservas activas de un usuario.
 * Permite listar y añadir reservas, así como mostrar información relevante como títulos, fechas e imágenes de libros reservados.
 */
public class PanelMisReservas extends JPanel {
    private JScrollPane scrollPane;
    private JPanel panelContenedor;

    /**
     * Constructor de la clase PanelMisReservas.
     * Inicializa el panel con las reservas activas del usuario en sesión.
     *
     * @param ventanaContenedor La ventana principal que contiene este panel.
     * @param prestamos         Lista de préstamos disponibles.
     */
    public PanelMisReservas(VentanaPrincipal ventanaContenedor, ArrayList<Prestamos> prestamos) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        panelContenedor = new JPanel();
        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));

        ArrayList<Prestamos> misPrestamosHistorico;
        misPrestamosHistorico = misPrestamos(prestamos, ventanaContenedor.getUsuarioActivo());

        anaidirPrestamosAlPanel(misPrestamosHistorico, panelContenedor);
        scrollPane = new JScrollPane(panelContenedor);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane);  // Directamente agregar el scrollPane
        setPreferredSize(new Dimension(400, 400));


    }

    /**
     * Añade una lista de préstamos al panel de contenido.
     *
     * @param misPrestamos   Lista de préstamos a mostrar.
     * @param panelContenedor Panel en el que se agregan los préstamos.
     */
    public void anaidirPrestamosAlPanel(ArrayList<Prestamos> misPrestamos, JPanel panelContenedor) {
        for(Prestamos prestamo: misPrestamos) {
            panelContenedor.add(panelLibroReservado(prestamo));
        }
    }

    /**
     * Crea un panel para mostrar la información de un libro reservado.
     *
     * @param prestamo Información del préstamo asociado al libro.
     * @return Un panel que contiene los datos del libro y las fechas de préstamo.
     */
    public static JPanel panelLibroReservado(Prestamos prestamo) {
        JPanel panelReserva = new JPanel();
        panelReserva.setLayout(new BoxLayout(panelReserva, BoxLayout.X_AXIS));
        panelReserva.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel para la imagen del libro y el título
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));

        // Etiqueta de título del libro
        JLabel titulo = new JLabel(prestamo.getTitulo());
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        panelIzquierdo.add(titulo);

        // Espacio entre título y la imagen
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0, 10)));

        // Imagen del libro
        JLabel imagenLabel = new JLabel();
        ImageIcon imagenIcon = new ImageIcon(rutaImagen(prestamo.getTitulo()));
        Image imagen = imagenIcon.getImage();
        Image imagenEscalada = imagen.getScaledInstance(200, 200, Image.SCALE_SMOOTH);  // Tamaño ajustado de imagen
        imagenLabel.setIcon(new ImageIcon(imagenEscalada));
        imagenLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelIzquierdo.add(imagenLabel);

        panelReserva.add(panelIzquierdo);
        panelReserva.add(Box.createRigidArea(new Dimension(20, 0)));

        // Panel de fechas
        Box boxFechas = Box.createVerticalBox();
        JLabel fechaInicioLabel = new JLabel("Inicio: " + prestamo.getFechaInicioPrestamo());
        JLabel fechaVencimientoLabel = new JLabel("Vencimiento: " + prestamo.getFechaFinPrestamo());

        fechaInicioLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        fechaVencimientoLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        boxFechas.add(fechaInicioLabel);
        boxFechas.add(Box.createRigidArea(new Dimension(0, 5))); // Espacio entre las fechas
        boxFechas.add(fechaVencimientoLabel);

        panelReserva.add(boxFechas);

        return panelReserva;
    }

    /**
     * Añade una nueva reserva al panel y actualiza la interfaz.
     *
     * @param prestamos Lista de préstamos de los que se toma la última reserva.
     */
    public void agregarReserva(ArrayList<Prestamos> prestamos) {

        JPanel panelReserva = panelLibroReservado(ultimoPrestamo(prestamos));
        panelContenedor.add(panelReserva);

        // Refrescar el contenedor y desplazar el JScrollPane al final
        panelContenedor.revalidate();
        panelContenedor.repaint();
        SwingUtilities.invokeLater(() -> {
            JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
            verticalScrollBar.setValue(verticalScrollBar.getMaximum()); // Desplazar al final
        });

    }

    /**
     * Genera la ruta de la imagen asociada al libro.
     *
     * @param tituloLibro Título del libro para construir la ruta de su imagen.
     * @return Ruta de la imagen correspondiente al título del libro.
     */
    private static String rutaImagen(String tituloLibro) {
        return "./res/" + tituloLibro + ".jpg";
    }
}

