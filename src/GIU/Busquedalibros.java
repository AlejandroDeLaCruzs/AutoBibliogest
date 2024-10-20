package GIU;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Busquedalibros extends JPanel {

    public Busquedalibros(Ventana ventanacontendor) {

        JLabel tituloLabel = new JLabel("Título:");
        JTextField tituloField = new JTextField(100);

        JLabel autorLabel = new JLabel("Autor:");
        JTextField autorField = new JTextField(100);

        JLabel generoLabel = new JLabel("Género:");

        JRadioButton generoLiteraturajuvenil = new JRadioButton("Literatura juvenil");
        JRadioButton generoLiteraturacontemporanea = new JRadioButton("Literatura contemporánea");
        JRadioButton generoFantasia = new JRadioButton("Fantasía");
        JRadioButton generoThriller = new JRadioButton("Thriller");

        ButtonGroup grupoGeneros = new ButtonGroup();
        grupoGeneros.add(generoLiteraturajuvenil);
        grupoGeneros.add(generoLiteraturacontemporanea);
        grupoGeneros.add(generoFantasia);
        grupoGeneros.add(generoThriller);

        JButton botonBuscar = new JButton("Buscar");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Configurar el diseño horizontal
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(tituloLabel)
                        .addComponent(autorLabel)
                        .addComponent(generoLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(tituloField)
                        .addComponent(autorField)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(generoLiteraturajuvenil)
                                .addComponent(generoLiteraturacontemporanea)
                                .addComponent(generoFantasia)
                                .addComponent(generoThriller))
                        .addComponent(botonBuscar))
        );


        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(tituloLabel)
                        .addComponent(tituloField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(autorLabel)
                        .addComponent(autorField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(generoLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(generoLiteraturajuvenil)
                        .addComponent(generoLiteraturacontemporanea)
                        .addComponent(generoFantasia)
                        .addComponent(generoThriller))
                .addComponent(botonBuscar)
        );


        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String generoSeleccionado = "";
                String titulo = tituloField.getText();
                String autor = autorField.getText();

                if (generoLiteraturajuvenil.isSelected()) {
                    generoSeleccionado = "Literatura juvenil";
                } else if (generoLiteraturacontemporanea.isSelected()) {
                    generoSeleccionado = "Literatura contemporánea";
                } else if (generoFantasia.isSelected()) {
                    generoSeleccionado = "Fantasía";
                } else if (generoThriller.isSelected()) {
                    generoSeleccionado = "Thriller";
                }

                Busquedapanel panel = new Busquedapanel(autor, titulo, generoSeleccionado, ventanacontendor);
                ventanacontendor.getPanelContenedor().add(panel, "busqueda");
                ventanacontendor.cambiarPanel("busqueda");


            }
        });

    }


}
