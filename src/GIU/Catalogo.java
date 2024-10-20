package GIU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/*
 CLASE CATALAGO-------
 La clase catalgo es panel que contiene toodos los libros de la biblioteca pudiendo selecionar
 mediante un boton cualquier libro para obtener informacion mas en detalle del libro.
 */
public class Catalogo extends JPanel {

    /*
    El panel Catálogo contendrá a un JscrollPane que a su vez contendrá unos
    paneles que se iran ubicando de manera horizontal y vertical. Además en esto paneles
    se podrá ver el titulo, la portada del libro y un boton para obtener mas informacion.
     */
    Catalogo(Ventana ventanacontenedor) {

        // Establecer color de fondo y layout
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout()); //Para que ocupe toda la pantalla

        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new GridLayout(7, 9));

        // Crear un JScrollPane que contenga el panelContenido
        JScrollPane scrollPane = new JScrollPane(panelContenido);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Llamar a la función para cargar la información de los libros
        infolibros(panelContenido,ventanacontenedor);


        // Añadir el JScrollPane al panel principal
        add(scrollPane);


    }

    /*
    Metodo que lee el fichero y llama al metedo para crear un panel con los datos del libro
    y los añade al PanelContendenor.
     */
    public void infolibros(JPanel panel, Ventana ventanacontenedor) {
        try (BufferedReader reader = new BufferedReader(new FileReader("libros.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 8) {
                    int copias = Integer.parseInt(partes[6]);
                    int aniopublicacion = Integer.parseInt(partes[4]);
                    Libro libro = new Libro(partes[0], partes[1], partes[2], partes[3], aniopublicacion, partes[5], copias, partes[7]);
                    panel.add(crearpanelinfolibro(libro, ventanacontenedor));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /*
    @Return JPanel
    @param libro del que se quiere crear el panel
    @param ventana para poder cambiar de panel si pulsa el boton de mas informacion
    Metodo que crea un panel del respectivo libro. Añade el titulo del libro y su respectiva portada.
    Ademas se crea un JButton para poder acceeder a una informacion mas detallada del libro.
     */
    public static JPanel crearpanelinfolibro(final Libro libro, Ventana ventanacontenedor) {
        JPanel panelinfolibro = new JPanel();

        panelinfolibro.setSize(400, 400);
        panelinfolibro.setLayout(null); // Usar un diseño nulo para personalizar las posiciones

        // Etiqueta para el título del libro
        JLabel titulo = new JLabel(libro.getTitulo());
        titulo.setBounds(100, 10, 300, 20); // Establecer la posición y el tamaño del título
        panelinfolibro.add(titulo);
        // Establecer el fondo del panel
        panelinfolibro.setBackground(Color.LIGHT_GRAY);
        // Establecer el tamaño preferido del panel
        panelinfolibro.setPreferredSize(new Dimension(250, 250));



        JLabel imagenLabel = new JLabel();
        ImageIcon imagenIcon = new ImageIcon(libro.getRutaimagen());
        Image imagen = imagenIcon.getImage();  // Obtener la imagen
        Image imagenEscalada = imagen.getScaledInstance(190, 190, Image.SCALE_SMOOTH);  // Escalar a 100x100
        imagenLabel.setIcon(new ImageIcon(imagenEscalada));  // Asignar la imagen escalada
        imagenLabel.setBounds(55, 50, 190, 190);
       imagenLabel.setIcon(new ImageIcon(imagenEscalada));
       panelinfolibro.add(imagenLabel);
/*
        JLabel imagenportada = new JLabel(portada);
        imagenportada.setPreferredSize(new Dimension(80, 80));
        imagenportada.setBounds(150, 40, 100, 100);

        panelinfolibro.add(imagenportada);*/


        //Buton para obtner mas infotmacion dek libro
        JButton buttoninfodetallelibro = new JButton("Info");
        buttoninfodetallelibro.setEnabled(true);
        buttoninfodetallelibro.setBounds(350, 7, 50, 40);
        panelinfolibro.add(buttoninfodetallelibro);

        /*
         Al haceer clic se crea un nuevo panel con los detallews libro utilizando el metedo estatico
         creardetallesLibroPanel
         Se añade el panel al
         @param e El evento de acción generado cuando el usuario hace clic en el botón.
        */
        buttoninfodetallelibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DetallesLibro detallesLibroPanel = new DetallesLibro  (ventanacontenedor, libro);
                ventanacontenedor.getPanelContenedor().add(detallesLibroPanel, "paneldetalle");
                ventanacontenedor.cambiarPanel("paneldetalle");

            }
        });


        return panelinfolibro;


    }
}