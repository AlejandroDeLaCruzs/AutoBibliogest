package GUI;

import App.VentanaPrincipal;
import Core.Biblioteca;
import Core.Libro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;


import static Core.Prestamos.reservarlibro;

/**
 * Panel que permite al usuario confirmar la reserva de un libro.
 * Muestra la información relevante del libro, del usuario, y las fechas de inicio y fin de la reserva.
 */
public class PanelConfirmarReserva extends JPanel {
    /**
     * Constructor de la clase PanelConfirmarReserva.
     * Configura los componentes gráficos necesarios para confirmar la reserva de un libro.
     *
     * @param libro             El libro que se desea reservar.
     * @param ventanaContenedor La ventana principal que contiene este panel.
     * @param biblioteca        La instancia de la biblioteca que gestiona los préstamos.
     */
    public PanelConfirmarReserva(Libro libro, VentanaPrincipal ventanaContenedor, Biblioteca biblioteca) {

        setLayout(null);

        //Creación de la imagen de la portada del libro
        JLabel imagenLabel = new JLabel();
        ImageIcon imagenIcon = new ImageIcon(libro.getRutaimagen());
        Image imagen = imagenIcon.getImage();  // Obtener la imagen
        Image imagenEscalada = imagen.getScaledInstance(400, 400, Image.SCALE_SMOOTH);  // Escalar a 100x100
        imagenLabel.setIcon(new ImageIcon(imagenEscalada));  // Asignar la imagen escalada
        imagenLabel.setBounds(55, 100, 400, 400);
        imagenLabel.setIcon(new ImageIcon(imagenEscalada));
        add(imagenLabel);

        // Título del libro
        JLabel titulo = new JLabel(libro.getTitulo());
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setBounds(500, 110, 500, 40);
        add(titulo);

        // Información del usuario activo
        JLabel usuario = new JLabel("Correo:" + ventanaContenedor.getUsuarioActivo().getCorreo());
        usuario.setBounds(500, 150, 500, 30);
        add(usuario);

        // Inicialización de las fechas de préstamo
        LocalDate fechaInicioPrestamo = LocalDate.now();
        LocalDate fechaFinPrestamo = fechaInicioPrestamo.plusWeeks(2);

        // Fecha de inicio de la reserva
        JLabel fechaInicioReserva = new JLabel("Fecha inicio préstamo: " + fechaInicioPrestamo);
        fechaInicioReserva.setBounds(500, 200, 500, 20);
        add(fechaInicioReserva);

        // Fecha de fin de la reserva
        JLabel fechaFinReserva = new JLabel("Fecha fin préstamo: " + fechaFinPrestamo);
        fechaFinReserva.setBounds(500, 240, 500, 20);
        add(fechaFinReserva);

        // Botón para confirmar la reserva
        JButton botonConfirmarReserva = new JButton("Confirmar reserva");
        botonConfirmarReserva.setEnabled(true);
        botonConfirmarReserva.setBounds(700, 320, 270, 50);
        add(botonConfirmarReserva);

        botonConfirmarReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reservarlibro(libro, ventanaContenedor.getUsuarioActivo(), biblioteca );
                JOptionPane.showMessageDialog(null, "Libro reservado con éxito");
                ventanaContenedor.getPanelMisReservas().agregarReserva(biblioteca.getPrestamos());
                ventanaContenedor.cambiarPanel("catalogo");
            }
        });
    }
}
