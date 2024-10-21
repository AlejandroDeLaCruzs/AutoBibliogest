package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PanelMisReservas extends JPanel {
    public PanelMisReservas(Ventana ventanacontenedor) {
        setLayout(new GridLayout(0, 1));

        // Crear un JScrollPane que contenga el panelContenido
        JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        try (BufferedReader reader = new BufferedReader(new FileReader("prestamos.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length == 4) {
                    if (campos[0].equals(ventanacontenedor.getUsuarioActivo().getCorreo())) {
                        System.out.println("aaaa");
                        add(panellibroreservado(campos));
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JPanel panellibroreservado(String[] datosreserva) {
        JPanel panelreserva = new JPanel();

        JLabel titulo = new JLabel(datosreserva[1]);
        panelreserva.add(titulo);

        /*JLabel imagenLabel = new JLabel();
        ImageIcon imagenIcon = new ImageIcon(libro.getRutaimagen());
        Image imagen = imagenIcon.getImage();  // Obtener la imagen
        Image imagenEscalada = imagen.getScaledInstance(190, 190, Image.SCALE_SMOOTH);  // Escalar a 100x100
        imagenLabel.setIcon(new ImageIcon(imagenEscalada));  // Asignar la imagen escalada
        imagenLabel.setBounds(55, 50, 190, 190);
        imagenLabel.setIcon(new ImageIcon(imagenEscalada));
        panelinfolibro.add(imagenLabel);*/

        //JLabel


        return panelreserva;
    }
    public static void actualizarpanelreservas(Ventana ventanacontenedor){

    }
}
