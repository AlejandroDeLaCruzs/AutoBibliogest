package GIU;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static GIU.Catalogo.crearpanelinfolibro;

public class Busquedapanel extends JPanel {
    public Busquedapanel(String autor, String titulo, String genero, Ventana ventanacontendor) {


        setLayout(new BorderLayout()); //Para que ocupe toda la pantalla

        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new GridLayout(7, 9));

        // Crear un JScrollPane que contenga el panelContenido
        JScrollPane scrollPane = new JScrollPane(panelContenido);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        p(panelContenido, autor, titulo, genero, ventanacontendor);

        // Añadir el JScrollPane al panel principal
        add(scrollPane);
        setVisible(true);
    }


    public static void p(JPanel panel, String autor, String titulo, String genero, Ventana ventanacontendor) {
        int contnodr = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("libros.txt"))) {
            String linea;
           System.out.println(titulo);
            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(",");
                boolean coincideTitulo = false;
                boolean coincideAutor = false;
                boolean coincideGenero = false;

                if (titulo.isEmpty()) {
                    coincideTitulo = true;
                }
                if (autor.isEmpty()) {
                    coincideAutor = true;
                }
                if (genero.isEmpty()) {
                    coincideGenero = true;
                }
                if (campos.length == 8) {
                    if (titulo.equals(campos[1]) && !coincideTitulo) {
                        coincideTitulo = true;
                    }
                    if (campos[3].equals(autor) && !coincideAutor) {
                        coincideAutor = true;
                    }

                    if (campos[5].equals(genero) && !coincideGenero) {
                        coincideGenero = true;
                    }
                }

                if (coincideTitulo && coincideAutor && coincideGenero && campos.length == 8) {

                    int copias = Integer.parseInt(campos[6]);
                    int aniopublicacion = Integer.parseInt(campos[4]);
                    Libro libro = new Libro(campos[0], campos[1], campos[2], campos[3], aniopublicacion, campos[5], copias, campos[7]);
                    panel.add(crearpanelinfolibro(libro, ventanacontendor));
                    contnodr++;
                }
            }
            if(contnodr == 0){
                JOptionPane.showMessageDialog(null, "no se ha encontradno ningn libor ");
                //ventanacontendor.cambiarPanel("busquedalibros");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

