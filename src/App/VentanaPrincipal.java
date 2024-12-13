package App;

import Core.Biblioteca;
import Core.Usuario;
import GUI.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ventana principal de la aplicación AutoBibliogest, que gestiona la interfaz gráfica del usuario
 * y permite la navegación entre distintos paneles (catálogo, búsqueda, reservas, etc.).
 */
public class VentanaPrincipal extends JFrame {

    private Usuario usuarioActivo;
    private CardLayout cardLayout;
    private JPanel panelContenedor;
    private JMenuBar barraMenu;
    private Biblioteca biblioteca;

    /**
     * Constructor de la clase VentanaPrincipal. Configura la interfaz gráfica, la barra de menú
     * y los paneles de la aplicación.
     */
    public VentanaPrincipal() {
        biblioteca = new Biblioteca();
        biblioteca.iniciarBiblioteca();

        // Configuración del JFrame
        setTitle("AutoBibliogest");
        setSize(400, 400);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializar CardLayout y panel contenedor
        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);
        usuarioActivo = new Usuario();

        inicializarPaneles();
        inicializarMenu();

        cardLayout.show(panelContenedor, "panelInicio");

        this.add(panelContenedor);

        // Hacer visible el JFrame
        setVisible(true);

    }

    /**
     * Hace visible la barra de menú en la ventana principal.
     */
    public void hacerVisibleMenu() {
        setJMenuBar(barraMenu);
    }

    /**
     * Cambia el panel activo (visible) en el contenedor.
     *
     * @param nombrePanel El nombre del panel a mostrar.
     */
    public void cambiarPanel(String nombrePanel) {
        cardLayout.show(panelContenedor, nombrePanel);
    }

    /**
     * Muestra un panel específico en el contenedor y cambia a él.
     *
     * @param panel          El panel a mostrar.
     * @param nombreDelPanel Nombre del panel para agregarlo al contenedor.
     */
    public void mostrarPanel(JPanel panel, String nombreDelPanel) {
        panelContenedor.add(panel, nombreDelPanel);
        cambiarPanel(nombreDelPanel);
    }

    /**
     * Agrega un panel al contenedor sin cambiar a él.
     *
     * @param panel          El panel a agregar.
     * @param nombreDelPanel Nombre del panel para agregarlo al contenedor.
     */
    public void agregarPanel(JPanel panel, String nombreDelPanel) {
        panelContenedor.add(panel, nombreDelPanel);
    }

    /**
     * Obtiene el usuario activo en la aplicación.
     *
     * @return El objeto Usuario que representa al usuario actualmente activo.
     */
    public Usuario getUsuarioActivo() {
        return usuarioActivo;
    }

    /**
     * Establece el usuario activo en la aplicación.
     *
     * @param usuarioActivo El objeto Usuario que representa al usuario a establecer como activo.
     */
    public void setUsuarioActivo(Usuario usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
    }


    /**
     * Obtiene el panel de "Mis Reservas" si está presente en el contenedor.
     *
     * @return El panel de "Mis Reservas", o null si no se encuentra.
     */
    public PanelMisReservas getPanelMisReservas() {
        for (Component comp : panelContenedor.getComponents()) {
            if (comp instanceof PanelMisReservas) {
                return (PanelMisReservas) comp;
            }
        }
        return null; // Si no encuentra el panel
    }

    /**
     * Inicializa y agrega los paneles principales al contenedor.
     */
    private void inicializarPaneles() {
        panelContenedor.add(new PanelInicio(this, biblioteca), "panelInicio");
        panelContenedor.add(new PanelCrearUsuario(this, biblioteca), "panelCrearUsuario");
        panelContenedor.add(new PanelCatalogoLibros(this, biblioteca), "catalogo");
        panelContenedor.add(new PanelBusquedalibros(this, biblioteca), "busquedalibros");
        panelContenedor.add(new PantallaCarga(this), "pantallacarga");
    }


    /**
     * Configura la barra de menú y los listeners de los elementos del menú.
     */
    private void inicializarMenu() {
        JMenuItem menuSalir = new JMenuItem("SALIR");
        JMenuItem menuItemCatalogo = new JMenuItem("CATÁLOGO");
        JMenuItem menuItemBusqueda = new JMenuItem("BÚSQUEDA");
        JMenuItem menuItemMisReservas = new JMenuItem("MIS RESERVAS");

        barraMenu = new JMenuBar();
        barraMenu.add(menuItemCatalogo);
        barraMenu.add(menuItemBusqueda);
        barraMenu.add(menuItemMisReservas);
        barraMenu.add(menuSalir);

        // Action listeners para los ítems del menú
        menuSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                biblioteca.cerrarBiblioteca();
                dispose();
            }
        });

        menuItemCatalogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel("catalogo");
            }
        });

        menuItemBusqueda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel("busquedalibros");
            }
        });

        menuItemMisReservas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel("panelMisReservas");
            }
        });
    }

}

