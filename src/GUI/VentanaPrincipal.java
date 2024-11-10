package GUI;

import Core.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {

    private Usuario UsuarioActivo;
    private CardLayout cardLayout;
    private JPanel panelContenedor;
    private JMenuBar barraMenu;



    public VentanaPrincipal() {
        // Configuración del JFrame
        setTitle("AutoBibliogest");
        setSize(400, 400);


        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        // Inicializar CardLayout y panel contenedor
        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);
        UsuarioActivo = new Usuario();


        // Crear los paneles
        PanelInicio panelInicio = new PanelInicio(this);  // El primer panel
        PanelCrearUsuario panelCrearUsuario = new PanelCrearUsuario(this);  // El segundo panel
        Catalogo catalogo = new Catalogo(this); //EL tercer panel
        PanelBusquedalibros busquedalibros = new PanelBusquedalibros(this);
        PantallaCarga pantallaCarga = new PantallaCarga(this);
        //PanelMisReservas misReservas = new PanelMisReservas(this);


        // Añadir los paneles al panel contenedor con un nombre para cada uno
        panelContenedor.add(panelInicio, "panelInicio");
        panelContenedor.add(panelCrearUsuario, "panelCrearUsuario");
        panelContenedor.add(catalogo, "catalogo");
        panelContenedor.add(busquedalibros, "busquedalibros");
        panelContenedor.add(pantallaCarga, "pantallacarga");
       // panelContenedor.add(misReservas, "panelMisreservas");


        //Menu con las diferentes opciones
        JMenuItem menuSalir = new JMenuItem("SALIR");
        JMenuItem menuItemcatalogo = new JMenuItem("CATALOGO");
        JMenuItem menuItembusqueda = new JMenuItem("BUSQUEDA");
        JMenuItem menuItemmisreservas = new JMenuItem("MIS RESERVAS");

        barraMenu = new JMenuBar();

        //Añadimos al menu todas las diferentes items(opciones)
        barraMenu.add(menuItemcatalogo);
        barraMenu.add(menuItembusqueda);
        barraMenu.add(menuItembusqueda);
        barraMenu.add(menuItemmisreservas);
        barraMenu.add(menuSalir);

        //Añadimos el menu a la ventana
       // setJMenuBar(barraMenu);


        // Mostrar el primer panel al iniciar
        cardLayout.show(panelContenedor, "panelInicio");

        // Añadir el panel contenedor al JFrame
        this.add(panelContenedor);

        // Hacer visible el JFrame
        setVisible(true);


        menuSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        menuItemcatalogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel("catalogo");
            }
        });

        menuItembusqueda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel("busquedalibros");
            }
        });


        menuItemmisreservas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPanel("panelMisreservas");

            }
        });

    }
    public void hacervisblemenu() {
        setJMenuBar(barraMenu);
    }

    //Métedo público para cambiar de panel
    public void cambiarPanel(String nombrePanel) {
        cardLayout.show(panelContenedor, nombrePanel);
    }

    //Metedos get y set
    public Usuario getUsuarioActivo() {
        return UsuarioActivo;
    }

    public void setUsuarioActivo(Usuario usuarioActivo) {
        UsuarioActivo = usuarioActivo;
    }


    public JPanel getPanelContenedor() {
        return panelContenedor;
    }

    public void setPanelContenedor(JPanel panelContenedor) {
        this.panelContenedor = panelContenedor;
    }

    public PanelMisReservas getPanelMisReservas() {
        for (Component comp : panelContenedor.getComponents()) {
            if (comp instanceof PanelMisReservas) {
                return (PanelMisReservas) comp;
            }
        }
        return null; // Si no encuentra el panel
    }


}

