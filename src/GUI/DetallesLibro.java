package GUI;

import Core.Libro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import static Core.Libro.buscarLibro;
import static Core.Libro.librosRecomendados;
import static Core.Prestamos.*;

/**
 * Clase que representa el panel de detalles de un libro en una aplicación de biblioteca.
 * Permite mostrar información detallada del libro y realizar acciones como reservarlo.
 */
public class DetallesLibro extends JPanel {
    /**
     * Mapa que asocia géneros de libros con sus rutas de imágenes correspondientes.
     */
    private static final Map<String, String> generoImagenes = new HashMap<>();

    static {
        generoImagenes.put("Literatura juvenil", "./res/literaturajuvenil.png");
        generoImagenes.put("Fantasía", "./res/Fantasia.jpg");
        generoImagenes.put("Thriller", "./res/thriller.jpg");
        generoImagenes.put("Literatura contemporánea", "./res/literaturacontemporanea.jpg");
        generoImagenes.put("Default", "./res/literaturacontemporanea.jpg"); // Imagen por defecto
    }

    /**
     * Constructor de la clase DetallesLibro.
     *
     * @param ventanacontendor La ventana principal de la aplicación.
     * @param libro            El libro cuyos detalles se mostrarán.
     */
    public DetallesLibro(VentanaPrincipal ventanacontendor, Libro libro) {
        inicializarPanel();
        agregarComponentesPrincipales(libro, ventanacontendor);
        agregarBotonesNavegacion(ventanacontendor, libro);

    }

    public static JPanel panelLibroRecomendado(Libro libro, VentanaPrincipal ventanacontenedor) {
        JPanel panellibrorecoemdado = new JPanel();
        panellibrorecoemdado.setLayout(new BoxLayout(panellibrorecoemdado, BoxLayout.Y_AXIS));
        panellibrorecoemdado.setBackground(Color.white);
        panellibrorecoemdado.setBounds(290, 400, 400, 500);

        JLabel titulo = new JLabel(libro.getTitulo());
        titulo.setForeground(Color.LIGHT_GRAY);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panellibrorecoemdado.add(titulo);

        panellibrorecoemdado.add(Box.createVerticalStrut(10));

        JButton botonlibro = crearBotonConImagen(libro.getRutaimagen(), 250, 250, 170, 170, null);
        botonlibro.setAlignmentX(Component.CENTER_ALIGNMENT);
        panellibrorecoemdado.add(botonlibro);

        botonlibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DetallesLibro detallesLibroPanel = new DetallesLibro(ventanacontenedor, libro);
                ventanacontenedor.getPanelContenedor().add(detallesLibroPanel, "paneldetalle");
                ventanacontenedor.cambiarPanel("paneldetalle");

            }
        });
        return panellibrorecoemdado;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Limpia el panel antes de dibujar
        g.setColor(Color.lightGray);   // Establece el color de la línea
        g.drawLine(290, 300, 1100, 300);
        g.drawLine(590, 320, 590, 400);
        g.drawLine(720, 320, 720, 400);
        g.drawLine(900, 320, 900, 400);
    }


    /**
     * Configura las propiedades iniciales del panel, como el diseño y el color de fondo.
     */
    private void inicializarPanel() {
        setLayout(null);
        setBackground(Color.white);
    }



    /**
     * Agrega los componentes principales relacionados con la información del libro.
     *
     * @param libro El libro cuyos detalles se mostrarán.
     */
    private void agregarComponentesPrincipales(Libro libro, VentanaPrincipal ventanacontendor) {
        agregarImagenLibro(libro);

        agregarEtiqueta(new JLabel(libro.getTitulo()), 670, 30, 1000, 40, new Font("Arial", Font.BOLD, 30));
        agregarEtiqueta(new JLabel(libro.getAutor()), 670, 80, 500, 30, new Font("Arial", Font.PLAIN, 15));
        agregarEtiqueta(new JLabel("Sinopsis de " + libro.getTitulo()), 420, 230, 500, 30, null);
        agregarEtiqueta(new JLabel(libro.getResumen()), 410, 250, 500, 21, null);

        add(panelGenero(libro));
        add(panelAnioPublicacion(libro));
        add(panelIdioma());
        add(panelCantidadPaginas(libro));
        add(panelLibrosreomendados(libro, ventanacontendor));

        JButton botonReservar = crearBoton("Reservar", 670, 170, 270, 40);
        add(botonReservar);
        botonReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!haydisponibilidad(libro)) {
                    JOptionPane.showMessageDialog(null, "No hay libros disponibles para reservar." + proximadisponibilidad(libro));
                } else if (reservado(libro, ventanacontendor.getUsuarioActivo())) {
                    JOptionPane.showMessageDialog(null, "Ya tienes este libro reservado.");
                } else {
                    PanelConfirmreserva panelConfirmreserva = new PanelConfirmreserva(libro, ventanacontendor);
                    ventanacontendor.mostrarPanel(panelConfirmreserva, "panelConfirmreserva");
                }
            }
        });

    }


    /**
     * Agrega botones de navegación para cambiar entre los detalles de diferentes libros.
     *
     * @param ventanacontendor La ventana principal de la aplicación.
     * @param libro            El libro actual mostrado en el panel.
     */
    private void agregarBotonesNavegacion(VentanaPrincipal ventanacontendor, Libro libro) {
        JButton botonCerrar = crearBotonConImagen("./res/cruz.jpg", 1400, 10, 30, 30, "Cerrar");
        botonCerrar.addActionListener(new ActionListener() {
            /**
             * Gestiona la acción de cerrar el panel de detalles.
             *
             * @param e El evento generado por el botón.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanacontendor.cambiarPanel("catalogo");
            }
        });
        add(botonCerrar);

        JButton botonFlechaDerecha = crearBotonConImagen("./res/flechaderecha.jpg", 1400, 400, 50, 50, "Siguiente libro");
        botonFlechaDerecha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Libro siguientelibroficehro = buscarLibro(libro, true);
                if (siguientelibroficehro != null) {
                    DetallesLibro detallesLibroPanel = new DetallesLibro(ventanacontendor, siguientelibroficehro);
                    ventanacontendor.mostrarPanel(detallesLibroPanel, "paneldetalle");
                }
            }
        });
        add(botonFlechaDerecha);


        JButton botonFlechaIzquierda = crearBotonConImagen("./res/flechaizq.jpg", 10, 400, 50, 50, "Libro anterior");
        botonFlechaIzquierda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Libro anteriorlibroficehro = buscarLibro(libro, false);
                if (anteriorlibroficehro != null) {
                    DetallesLibro detallesLibroPanel = new DetallesLibro(ventanacontendor, anteriorlibroficehro);
                    ventanacontendor.mostrarPanel(detallesLibroPanel, "paneldetalle");
                }
            }
        });
        add(botonFlechaIzquierda);

    }


    /**
     * Agrega la imagen del libro al panel.
     *
     * @param libro El libro cuya imagen se mostrará.
     */
    private void agregarImagenLibro(Libro libro) {
        JLabel imagenLabel = new JLabel();
        ImageIcon imagenIcon = new ImageIcon(libro.getRutaimagen());
        Image imagenEscalada = imagenIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        imagenLabel.setIcon(new ImageIcon(imagenEscalada));
        imagenLabel.setBounds(410, 10, 200, 200);
        add(imagenLabel);
    }

    /**
     * Agrega una etiqueta al panel con las propiedades especificadas.
     *
     * @param etiqueta La etiqueta a agregar.
     * @param x        Posición x del componente.
     * @param y        Posición y del componente.
     * @param width    Ancho del componente.
     * @param height   Altura del componente.
     * @param font     Fuente a usar en la etiqueta (puede ser nula).
     */
    private void agregarEtiqueta(JLabel etiqueta, int x, int y, int width, int height, Font font) {
        if (font != null) {
            etiqueta.setFont(font);
        }
        etiqueta.setBounds(x, y, width, height);
        add(etiqueta);
    }

//Singelton
    private JButton crearBoton(String texto, int x, int y, int width, int height) {
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, width, height);
        return boton;
    }

    private static JButton crearBotonConImagen(String rutaImagen, int x, int y, int width, int height, String tooltip) {
        ImageIcon icono = new ImageIcon(rutaImagen);
        Image imgEscalada = icono.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        JButton boton = new JButton(new ImageIcon(imgEscalada));
        boton.setBounds(x, y, width, height);
        boton.setToolTipText(tooltip);
        boton.setBorder(null);
        boton.setContentAreaFilled(false);
        return boton;
    }

    private JLabel getGeneroImagen(Libro libro) {
        JLabel generoImagen = new JLabel();
        ImageIcon iconGenero = new ImageIcon(getImagePathGenero(libro.getGenero()));
        Image imgGenero = iconGenero.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        generoImagen.setIcon(new ImageIcon(imgGenero));
        generoImagen.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar la imagenflecha en el subpanel
        return generoImagen;
    }

    private String getImagePathGenero(String genero) {
        return generoImagenes.getOrDefault(genero, generoImagenes.get("Default"));
    }

    private JPanel panelGenero(Libro libro) {
        JPanel generoPanel = new JPanel();
        generoPanel.setLayout(new BoxLayout(generoPanel, BoxLayout.Y_AXIS)); // Configurar BoxLayout (vertical)
        generoPanel.setBounds(390, 310, 180, 100); // Posición y tamaño del subpanel
        generoPanel.setBackground(Color.WHITE);

        JLabel genero = new JLabel("GÉNERO");
        genero.setForeground(Color.LIGHT_GRAY);
        genero.setAlignmentX(Component.CENTER_ALIGNMENT);
        generoPanel.add(genero);
        generoPanel.add(Box.createVerticalStrut(3));

        // Imagen del género
        JLabel generoImagen = getGeneroImagen(libro);
        generoPanel.add(generoImagen); // Añadir la imagenflechaderechs al subpanel

        // Espaciador entre la imagenflechaderechs y el texto
        generoPanel.add(Box.createVerticalStrut(7));

        // Texto del género
        JLabel generoTexto = new JLabel(libro.getGenero());
        generoTexto.setFont(new Font("Arial", Font.BOLD, 14));
        generoTexto.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el texto en el subpanel
        generoTexto.setPreferredSize(new Dimension(200, 40));
        generoPanel.add(generoTexto); // Añadir el texto al subpanel

        return generoPanel;
    }

    private JPanel panelAnioPublicacion(Libro libro) {
        JPanel publicadopanel = new JPanel();
        publicadopanel.setLayout(new BoxLayout(publicadopanel, BoxLayout.Y_AXIS));
        publicadopanel.setBackground(Color.white);
        publicadopanel.setBounds(610, 310, 90, 100);

        JLabel etiquetapublicado = new JLabel("PUBLICADO");
        etiquetapublicado.setForeground(Color.LIGHT_GRAY);
        etiquetapublicado.setAlignmentX(Component.CENTER_ALIGNMENT);
        publicadopanel.add(etiquetapublicado);

        publicadopanel.add(Box.createVerticalStrut(10));

        JLabel aniopublicado = new JLabel((libro.getAniopublicacion()));
        aniopublicado.setFont(new Font("Arial", Font.BOLD, 20));
        aniopublicado.setAlignmentX(Component.CENTER_ALIGNMENT);
        publicadopanel.add(aniopublicado);

        return publicadopanel;
    }

    private JPanel panelIdioma() {
        JPanel paneldioma = new JPanel();
        paneldioma.setLayout(new BoxLayout(paneldioma, BoxLayout.Y_AXIS));
        paneldioma.setBackground(Color.white);
        paneldioma.setBounds(740, 310, 130, 100);

        JLabel etiquetaidioma = new JLabel("IDIOMA");
        etiquetaidioma.setForeground(Color.LIGHT_GRAY);
        etiquetaidioma.setAlignmentX(Component.CENTER_ALIGNMENT);
        paneldioma.add(etiquetaidioma);

        paneldioma.add(Box.createVerticalStrut(10));

        JLabel idiomaabreviatura = new JLabel("Castellano");
        idiomaabreviatura.setFont(new Font("Arial", Font.BOLD, 20));
        idiomaabreviatura.setAlignmentX(Component.CENTER_ALIGNMENT);
        paneldioma.add(idiomaabreviatura);


        return paneldioma;
    }

    private JPanel panelCantidadPaginas(Libro libro) {
        JPanel panelcantidadpags = new JPanel();
        panelcantidadpags.setLayout(new BoxLayout(panelcantidadpags, BoxLayout.Y_AXIS));
        panelcantidadpags.setBackground(Color.white);
        panelcantidadpags.setBounds(920, 310, 90, 100);

        JLabel etiquetacantpags = new JLabel("EXTENSIÓN");
        etiquetacantpags.setForeground(Color.LIGHT_GRAY);
        etiquetacantpags.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelcantidadpags.add(etiquetacantpags);

        panelcantidadpags.add(Box.createVerticalStrut(6));

        JLabel cantidadpags = new JLabel(libro.getCantidadpaginas().toString());
        cantidadpags.setFont(new Font("Arial", Font.BOLD, 20));
        cantidadpags.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelcantidadpags.add(cantidadpags);

        return panelcantidadpags;
    }

    private JScrollPane panelLibrosreomendados(Libro libro, VentanaPrincipal ventanacontenedor) {
        JPanel panellibrosrecomendados = new JPanel();
        //panellibrosrecomendados.setBounds(100, 500, 700, 500);
        panellibrosrecomendados.setLayout(new GridLayout(1, 0, 10, 10));
        librosRecomendados(libro, panellibrosrecomendados, ventanacontenedor);
        JScrollPane jScrollPane = new JScrollPane(panellibrosrecomendados);
        jScrollPane.setBounds(300, 460, 1000, 250);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        return jScrollPane;
    }


}