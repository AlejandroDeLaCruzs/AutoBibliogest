package GIU;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelReservar extends JPanel {
     public PanelReservar(Libro libro) {

         setLayout(null);

         JLabel titulo = new JLabel(libro.getTitulo());



         JButton butonconfirmreserva = new JButton();

         butonconfirmreserva.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Prestamos prestamo = new Prestamos();
             }
         });


    }
}
