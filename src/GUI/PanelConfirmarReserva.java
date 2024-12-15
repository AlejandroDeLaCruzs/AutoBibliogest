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


public class PanelConfirmarReserva extends JPanel {
    public PanelConfirmarReserva(Libro libro, VentanaPrincipal ventanacontenador, Biblioteca biblioteca) {

        setLayout(null);

        //Creacion de la imagen de la portada del libro------------
        JLabel imagenLabel = new JLabel();
        ImageIcon imagenIcon = new ImageIcon(libro.getRutaimagen());
        Image imagen = imagenIcon.getImage();  // Obtener la imagen
        Image imagenEscalada = imagen.getScaledInstance(400, 400, Image.SCALE_SMOOTH);  // Escalar a 100x100
        imagenLabel.setIcon(new ImageIcon(imagenEscalada));  // Asignar la imagen escalada
        imagenLabel.setBounds(55, 100, 400, 400);
        imagenLabel.setIcon(new ImageIcon(imagenEscalada));
        add(imagenLabel);

        //Creacion del título del libro
        JLabel titulo = new JLabel(libro.getTitulo());
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setBounds(500, 110, 500, 40);
        add(titulo);

        //Creacion etiqeta usuario
        JLabel usuario = new JLabel("Correo:" + ventanacontenador.getUsuarioActivo().getCorreo());
        usuario.setBounds(500, 150, 500, 30);
        add(usuario);

        //Inicializacion fecha
        LocalDate fechaincioprestamo = LocalDate.now();
        LocalDate fechafinprestamo = fechaincioprestamo.plusWeeks(2);

        //Creacion de fecha incio reserva
        JLabel fechainicoreserva = new JLabel("Fecha incio prestamo:" + fechaincioprestamo);
        fechainicoreserva.setBounds(500, 200, 500, 20);
        add(fechainicoreserva);

        //Fecha fin de reserva
        JLabel fechafinreserva = new JLabel("Fecha fin de prestamo:" + fechafinprestamo);
        fechafinreserva.setBounds(500, 240, 500, 20);
        add(fechafinreserva);

        //Creacion buton confirmar reserva
        JButton butonconfirmreserva = new JButton("Confirmar reserva");
        butonconfirmreserva.setEnabled(true);
        butonconfirmreserva.setBounds(700, 320, 270, 50);
        add(butonconfirmreserva);

        butonconfirmreserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reservarlibro(libro, ventanacontenador.getUsuarioActivo(), biblioteca );
                JOptionPane.showMessageDialog(null, "Libro reservado con exito");
                ventanacontenador.getPanelMisReservas().agregarReserva(biblioteca.getPrestamos());
                ventanacontenador.cambiarPanel("catalogo");
            }
        });
    }
}