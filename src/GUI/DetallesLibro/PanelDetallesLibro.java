package GUI.DetallesLibro;

import App.VentanaPrincipal;
import Core.Biblioteca;
import Core.Libro;
import GUI.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import static Core.Libro.buscarLibro;
import static Core.Libro.librosRecomendados;
import static Core.Prestamos.*;

/**
 * Clase que representa el panel de detalles de un libro en una aplicación de biblioteca.
 * Permite mostrar información detallada del libro y realizar acciones como reservarlo.
 */
public class PanelDetallesLibro extends JPanel {

    /**
     * Constructor de la clase DetallesLibro.
     *
     * @param ventanaContenedor La ventana principal de la aplicación.
     * @param libro            El libro cuyos detalles se mostrarán.
     */
    public PanelDetallesLibro(VentanaPrincipal ventanaContenedor, Libro libro, Biblioteca biblioteca) {
        inicializarPanel();
        agregarComponentesPrincipales(libro, ventanaContenedor, biblioteca);
        agregarBotonesNavegacion(ventanaContenedor, libro, biblioteca);
    }


    /**
     * Agrega los componentes principales relacionados con la información del libro.
     *
     * @param libro El libro cuyos detalles se mostrarán.
     * @param ventanaContenedor La ventana principal de la aplicación.
     * @param biblioteca La biblioteca con los datos disponibles.
     */
    private void agregarComponentesPrincipales(Libro libro, VentanaPrincipal ventanaContenedor, Biblioteca biblioteca) {
        agregarImagenLibro(libro);

        agregarEtiqueta(new JLabel(libro.getTitulo()), 670, 30, 1000, 40, new Font("Arial", Font.BOLD, 30));
        agregarEtiqueta(new JLabel(libro.getAutor()), 670, 80, 500, 30, new Font("Arial", Font.PLAIN, 15));
        agregarEtiqueta(new JLabel("Sinopsis de " + libro.getTitulo()), 420, 230, 500, 30, null);
        agregarEtiqueta(new JLabel(libro.getResumen()), 410, 250, 500, 21, null);

        add(panelGenero(libro));
        add(panelAnioPublicacion(libro));
        add(panelIdioma());
        add(panelCantidadPaginas(libro));
        add(panelLibrosreomendados(libro, ventanaContenedor,biblioteca));

        JButton botonReservar = crearBoton("Reservar", 670, 170, 270, 40);
        add(botonReservar);
        botonReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!haydisponibilidad(libro)) {
                    JOptionPane.showMessageDialog(null, "No hay libros disponibles para reservar." + proximadisponibilidad(libro));
                } else if (reservado(libro, ventanaContenedor.getUsuarioActivo())) {
                    JOptionPane.showMessageDialog(null, "Ya tienes este libro reservado.");
                } else {
                    PanelConfirmreserva panelConfirmreserva = new PanelConfirmreserva(libro, ventanaContenedor);
                    ventanaContenedor.mostrarPanel(panelConfirmreserva, "panelConfirmreserva");
                }
            }
        });

    }


    /**
     * Crea un subpanel que muestra información de un libro recomendado.
     *
     * @param libro El libro recomendado a mostrar.
     * @param ventanaContenedor La ventana principal de la aplicación.
     * @param biblioteca La biblioteca con los datos disponibles.
     * @return Un JPanel con la información del libro recomendado.
     */
    private static JPanel crearPanelLibroRecomendado(Libro libro, VentanaPrincipal ventanaContenedor, Biblioteca biblioteca) {
        JPanel panelLibroRecomendado = new JPanel();
        panelLibroRecomendado.setLayout(new BoxLayout(panelLibroRecomendado, BoxLayout.Y_AXIS));
        panelLibroRecomendado.setBackground(Color.white);
        panelLibroRecomendado.setBounds(290, 400, 400, 500);

        JLabel titulo = new JLabel(libro.getTitulo());
        titulo.setForeground(Color.LIGHT_GRAY);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelLibroRecomendado.add(titulo);

        panelLibroRecomendado.add(Box.createVerticalStrut(10));

        JButton botonlibro = crearBotonConImagen(libro.getRutaimagen(), 250, 250, 170, 170, null);
        botonlibro.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelLibroRecomendado.add(botonlibro);

        botonlibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelDetallesLibro detallesLibroPanel = new PanelDetallesLibro(ventanaContenedor, libro, biblioteca);
                ventanaContenedor.mostrarPanel(detallesLibroPanel, "paneldetalle");
            }
        });
        return panelLibroRecomendado;
    }

    /**
     * Sobrescribe el método paintComponent para dibujar líneas decorativas en el panel.
     *
     * @param g El objeto Graphics para dibujar en el panel.
     */
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
     * Agrega botones de navegación para cambiar entre los detalles de diferentes libros.
     *
     * @param ventanaContenedor La ventana principal de la aplicación.
     * @param libro El libro actual mostrado en el panel.
     * @param biblioteca La biblioteca con los datos disponibles.
     */
    private void agregarBotonesNavegacion(VentanaPrincipal ventanaContenedor, Libro libro, Biblioteca biblioteca) {
        JButton botonCerrar = crearBotonConImagen("./res/cruz.jpg", 1400, 10, 30, 30, "Cerrar");
        botonCerrar.addActionListener(new ActionListener() {
            /**
             * Gestiona la acción de cerrar el panel de detalles.
             *
             * @param e El evento generado por el botón.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaContenedor.cambiarPanel("catalogo");
            }
        });
        add(botonCerrar);

        JButton botonSiguiente = crearBotonConImagen("./res/flechaderecha.jpg", 1400, 400, 50, 50, "Siguiente libro");
        botonSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Libro siguientelibroficehro = buscarLibro(libro, true, biblioteca);
                if (siguientelibroficehro != null) {
                    PanelDetallesLibro detallesLibroPanel = new PanelDetallesLibro(ventanaContenedor, siguientelibroficehro, biblioteca);
                    ventanaContenedor.mostrarPanel(detallesLibroPanel, "paneldetalle");
                }
            }
        });
        add(botonSiguiente);


        JButton botonAnterior = crearBotonConImagen("./res/flechaizq.jpg", 10, 400, 50, 50, "Libro anterior");
        botonAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Libro anteriorlibroficehro = buscarLibro(libro, false, biblioteca);
                if (anteriorlibroficehro != null) {
                    PanelDetallesLibro detallesLibroPanel = new PanelDetallesLibro(ventanaContenedor, anteriorlibroficehro, biblioteca);
                    ventanaContenedor.mostrarPanel(detallesLibroPanel, "paneldetalle");
                }
            }
        });
        add(botonAnterior);

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

    // Método para inicializar el panel recomendado
    private JPanel inicializarPanelRecomendados() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 0, 10, 10)); // Configuración de diseño
        return panel;
    }

    private JPanel panelGenero(Libro libro) {
        PanelLibroDetalleFactory fabricaGenero = new PanelGeneroFactory();
        return fabricaGenero.crearSubPanel(libro);
    }

    private JPanel panelAnioPublicacion(Libro libro) {
        PanelLibroDetalleFactory fabricaAnio = new PanelAnioPublicacionFactory();
        return fabricaAnio.crearSubPanel(libro);
    }

    private JPanel panelIdioma() {
        PanelLibroDetalleFactory fabricaIdioma = new PanelIdiomaFactory();
        return fabricaIdioma.crearSubPanel(null); // Pasamos null porque no depende del libro actual.
    }

    private JPanel panelCantidadPaginas(Libro libro) {
        PanelLibroDetalleFactory fabricaCantidad = new PanelCantidadPaginasFactory();
        return fabricaCantidad.crearSubPanel(libro);
    }

    private JScrollPane panelLibrosreomendados(Libro libro, VentanaPrincipal ventanacontenedor, Biblioteca biblioteca) {
        // Crear el panel principal para los libros recomendados
        JPanel panelLibrosRecomendados = inicializarPanelRecomendados();

        // Agregar libros recomendados al panel
        agregarLibrosRecomendadosAlPanel(libro, panelLibrosRecomendados, ventanacontenedor, biblioteca);

        JScrollPane jScrollPane = new JScrollPane(panelLibrosRecomendados);
        jScrollPane.setBounds(300, 460, 1000, 250);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        return jScrollPane;
    }



    // Método para agregar libros recomendados al panel
    private void agregarLibrosRecomendadosAlPanel(Libro libro, JPanel panelRecomendados, VentanaPrincipal ventanaContenedor, Biblioteca biblioteca) {
        // Filtrar los libros recomendados
        List<Libro> librosRecomendados = librosRecomendados(libro, biblioteca.getLibros());

        // Crear un panel para cada libro recomendado y añadirlo al panel principal
        for (Libro libroRecomendado : librosRecomendados) {
            panelRecomendados.add(crearPanelLibroRecomendado(libroRecomendado, ventanaContenedor, biblioteca));
        }
    }


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

}