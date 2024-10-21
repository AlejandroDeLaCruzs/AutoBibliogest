package GUI;

import javax.swing.*;

public class PantallaCarga extends JPanel {
    public PantallaCarga(Ventana ventanacontendor) {
        ImageIcon imagpantallacarga = new ImageIcon("/Users/alejandrodelacruz/Downloads/biblioteca.jpg");
        JLabel imagencarga = new JLabel(imagpantallacarga);
        add(imagencarga);
    }
}
