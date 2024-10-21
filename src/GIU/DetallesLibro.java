package GIU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GIU.Prestamos.haydisponibilidad;

/**
 * La clase DatellesLibro derivada de la clase JPanel creara un panel para cada libro con sus detalles.
 * Además tendra un buton para poder realizar la reserva.
 */
public class DetallesLibro extends JPanel {

    public DetallesLibro(Ventana ventanacontendor, Libro libro) {

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

        //Creacion del titulo del libro
        JLabel titulo = new JLabel(libro.getTitulo());
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setBounds(500, 110, 500, 40);
        add(titulo);


        //------
        JLabel autor = new JLabel(libro.getAutor());
        autor.setFont(new Font("Arial", Font.TYPE1_FONT, 25));
        autor.setForeground(Color.lightGray);
        autor.setBounds(500, 150, 500, 30);
        add(autor);

        //--------
        JLabel resumen = new JLabel("Libro: " + libro.getResumen());
        resumen.setBounds(500, 200, 500, 21);
        add(resumen);

        //----------
        JLabel genero = new JLabel("Genero: " + libro.getGenero());
        genero.setBounds(500, 220, 500, 21);
        add(genero);

        //----------
        JButton butonreservar = new JButton("Reservar");
        butonreservar.setBounds(800, 600, 200, 30);
        butonreservar.setForeground(Color.BLACK);
        butonreservar.setEnabled(true);
        add(butonreservar);

        //Buton
        butonreservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (haydisponibilidad(libro)) {
                    PanelConfirmreserva panelConfirmreserva = new PanelConfirmreserva(libro, ventanacontendor);
                    ventanacontendor.getPanelContenedor().add(panelConfirmreserva, "panelConfirmreserva");
                    ventanacontendor.cambiarPanel("panelConfirmreserva");
                } else {
                    JOptionPane.showMessageDialog(null, "No hay libros disponibles.");
                }
               /* PanelReservar panelreserva = new PanelReservar(libro, ventanacontendor);
                ventanacontendor.getPanelContenedor().add(panelreserva, "panelreserva");
                ventanacontendor.cambiarPanel("panelreserva");*/
            }
        });


    }
}


