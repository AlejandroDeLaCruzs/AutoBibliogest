package GUI;

import App.VentanaPrincipal;

import javax.swing.*;

/**
 * Clase que representa una pantalla de carga inicial que se muestra al arrancar la aplicación.
 * La pantalla solo aparece cuando el usuario inicia la aplicación y desaparece una vez el usuario
 * selecciona la sección en la que desea entrar.
 */
public class PantallaCarga extends JPanel {
    /**
     * Constructor de la clase PantallaCarga.
     * Muestra una imagen de pantalla de carga al iniciar la aplicación.
     * Esta pantalla desaparece después de que el usuario seleccione una sección.
     *
     * @param ventanaContenedor La ventana principal que contiene esta pantalla de carga.
     */
    public PantallaCarga(VentanaPrincipal ventanaContenedor) {
        ImageIcon imagpantallacarga = new ImageIcon("./res/biblioteca.jpg");
        JLabel imagencarga = new JLabel(imagpantallacarga);
        add(imagencarga);
    }

}
