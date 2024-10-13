package GIU;

import javax.swing.*;

public class PantallaCarga extends JPanel {
    public PantallaCarga(Ventana ventanacontendor) {
            ImageIcon imagpantallacarga = new ImageIcon("/Users/alejandrodelacruz/Downloads/biblioteca.jpg");
            JLabel imagencarga = new JLabel(imagpantallacarga);
            add(imagencarga);

       /* int panelAncho = getWidth();
        int panelAlto = getHeight();

        // Dibujar la imagen escalada al tamaño del panel
        g.drawImage(imagen, 0, 0, panelAncho, panelAlto, this);
            ;*/
    }
}
