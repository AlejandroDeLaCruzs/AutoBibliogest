package GUI;

import Core.Libro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static Core.Libro.infolibros;


/**
 * CLASE CATALAGO-------
 * La clase catalgo es panel que contiene toodos los libros de la biblioteca pudiendo selecionar
 * mediante un boton cualquier libro para obtener informacion mas en detalle del libro.
 */
public class Catalogo extends JPanel {

    /**
     * El panel Catálogo contendrá a un JscrollPane que a su vez contendrá unos
     * paneles que se iran ubicando de manera horizontal y vertical. Además en esto paneles
     * se podrá ver el titulo, la portada del libro y un boton para obtener mas informacion.
     */
    Catalogo(VentanaPrincipal ventanacontenedor) {

        // Establecer color de fondo y layout
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout()); //Para que ocupe toda la pantalla

        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new GridLayout(7, 9));

        // Crear un JScrollPane que contenga el panelContenido
        JScrollPane scrollPane = new JScrollPane(panelContenido);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Llamar a la función para cargar la información de los libros
        infolibros(panelContenido, ventanacontenedor);

        // Añadir el JScrollPane al panel principal
        add(scrollPane);

    }

    /**
     * Método que crea un panel del respectivo libro. Añade el titulo del libro y su respectiva portada.
     * Además se crea un JButton para poder acceder a una información mas detallada del libro.
     *
     * @param libro             del que se quiere crear el panel
     * @param ventanacontenedor para poder cambiar de panel si pulsa el botón de más información
     *                          Método que crea un panel del respectivo libro. Añade el título del libro y su respectiva portada.
     *                          Además se crea un JButton para poder acceder a una información más detallada del libro.
     * @return panelinfolibro
     */
    public static JPanel crearpanelinfolibro(final Libro libro, VentanaPrincipal ventanacontenedor) {

        JPanel panelinfolibro = new JPanel();

        panelinfolibro.setSize(400, 400);
        panelinfolibro.setLayout(null);

        // Etiqueta para el título del libro
        JLabel titulo = new JLabel(libro.getTitulo());
        titulo.setBounds(100, 10, 300, 20);
        panelinfolibro.add(titulo);

        // Establecer el fondo del panel
        panelinfolibro.setBackground(Color.LIGHT_GRAY);

        // Establecer el tamaño preferido del panel
        panelinfolibro.setPreferredSize(new Dimension(250, 250));


        JLabel imagenLabel = new JLabel();
        ImageIcon imagenIcon = new ImageIcon(libro.getRutaimagen());
        Image imagen = imagenIcon.getImage();
        Image imagenEscalada = imagen.getScaledInstance(190, 190, Image.SCALE_SMOOTH);
        imagenLabel.setIcon(new ImageIcon(imagenEscalada));
        imagenLabel.setBounds(55, 50, 190, 190);
        imagenLabel.setIcon(new ImageIcon(imagenEscalada));
        panelinfolibro.add(imagenLabel);

        //Botón para obtener más información del libro
        JButton buttoninfodetallelibro = new JButton("Info");
        buttoninfodetallelibro.setEnabled(true);
        buttoninfodetallelibro.setBounds(350, 7, 50, 40);
        panelinfolibro.add(buttoninfodetallelibro);

        /**
         Al hacer clic se crea un nuevo panel con los detalles libro utilizando el metedo estatico
         creardetallesLibroPanel.
         Se añade el panel a la pila del paneles.
         */
        buttoninfodetallelibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DetallesLibro detallesLibroPanel = new DetallesLibro(ventanacontenedor, libro);
                ventanacontenedor.getPanelContenedor().add(detallesLibroPanel, "paneldetalle");
                ventanacontenedor.cambiarPanel("paneldetalle");

            }
        });

        return panelinfolibro;
    }
}
