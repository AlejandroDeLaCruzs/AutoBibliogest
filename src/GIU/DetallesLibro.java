package GIU;

import javax.swing.*;
import java.awt.*;

public class DetallesLibro extends JPanel {

    public DetallesLibro(Ventana ventanacontendor) {





   }

    public static JPanel crearDetallesLibroPanel (Libro libro,Ventana ventanacontendor) {
        JPanel paneldetalles = new JPanel();
        paneldetalles.setBackground(Color.WHITE);

        paneldetalles.setLayout(null);

        JLabel imagenLabel = new JLabel();
        ImageIcon imagenIcon = new ImageIcon(libro.getRutaimagen());
        Image imagen = imagenIcon.getImage();  // Obtener la imagen
        Image imagenEscalada = imagen.getScaledInstance(400, 400, Image.SCALE_SMOOTH);  // Escalar a 100x100
        imagenLabel.setIcon(new ImageIcon(imagenEscalada));  // Asignar la imagen escalada
        imagenLabel.setBounds(55, 100, 400, 400);
        imagenLabel.setIcon(new ImageIcon(imagenEscalada));
        paneldetalles.add(imagenLabel);


       JLabel titulo = new JLabel(libro.getTitulo());
       titulo.setFont(new Font("Arial", Font.BOLD, 30));
       titulo.setBounds(500, 110, 500, 21);
       paneldetalles.add(titulo);


       JLabel autor = new JLabel(libro.getAutor());
        autor.setFont(new Font("Arial", Font.TYPE1_FONT, 25));
        autor.setForeground(Color.lightGray);
        autor.setBounds(500, 150, 500, 21);
        paneldetalles.add(autor);




        JButton butonreservar = new JButton("Reservar");






        return paneldetalles;

    }
}
