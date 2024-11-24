package GUI;

import Core.Libro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static Core.Busquedalibros.busquedalibrofichero;

public class PanelBusquedalibros extends JPanel {

    private Map<String, JRadioButton> generoBotones;

    public PanelBusquedalibros(VentanaPrincipal ventanacontendor) {

        JLabel tituloLabel = new JLabel("Título:");
        JTextField tituloField = new JTextField(100);

        JLabel autorLabel = new JLabel("Autor:");
        JTextField autorField = new JTextField(100);

        JLabel generoLabel = new JLabel("Género:");

        // Inicializamos los botones y los asociamos a géneros
        generoBotones = new HashMap<>();
        generoBotones.put("Literatura juvenil", new JRadioButton("Literatura juvenil"));
        generoBotones.put("Literatura contemporánea", new JRadioButton("Literatura contemporánea"));
        generoBotones.put("Fantasía", new JRadioButton("Fantasía"));
        generoBotones.put("Thriller", new JRadioButton("Thriller"));


        JButton botonBuscar = new JButton("Buscar");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Configuración de diseño dinámico para los géneros
        GroupLayout.SequentialGroup generoGroupHorizontal = layout.createSequentialGroup();
        for (JRadioButton boton : generoBotones.values()) {
            generoGroupHorizontal.addComponent(boton);
        }

        GroupLayout.ParallelGroup generoGroupVertical = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
        for (JRadioButton boton : generoBotones.values()) {
            generoGroupVertical.addComponent(boton);
        }


        // Configurar el diseño horizontal
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(tituloLabel)
                        .addComponent(autorLabel)
                        .addComponent(generoLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(tituloField)
                        .addComponent(autorField)
                        .addGroup(generoGroupHorizontal)
                        .addComponent(botonBuscar))
        );

        // Configurar el diseño vertical
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(tituloLabel)
                        .addComponent(tituloField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(autorLabel)
                        .addComponent(autorField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(generoLabel))
                .addGroup(generoGroupVertical)
                .addComponent(botonBuscar)
        );


        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] generoSeleccionado = generosSeleccionados();
                String titulo = tituloField.getText();
                String autor = autorField.getText();

                ArrayList<Libro> librosencontrados = busquedalibrofichero(autor, titulo, generoSeleccionado);
                if(librosencontrados != null){
                    Busquedapanel panel = new Busquedapanel(librosencontrados, ventanacontendor);
                    ventanacontendor.getPanelContenedor().add(panel, "busqueda");
                    ventanacontendor.cambiarPanel("busqueda");
                }
                else {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado ningun libro");
                }

            }
        });

    }


    public String[] generosSeleccionados() {
        ArrayList<String> seleccionados = new ArrayList<>();
        for (Map.Entry<String, JRadioButton> entry : generoBotones.entrySet()) {
            if (entry.getValue().isSelected()) {
                seleccionados.add(entry.getKey());
            }
        }
        return seleccionados.toArray(new String[0]);
    }
}
