package Core.Notificaciones;

import Core.Usuario;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Pop extends CrearNotificaciones {
        @Override
        public void notificacion(Usuario useractivo) {
            try (BufferedReader reader = new BufferedReader(new FileReader("prestamos.txt"))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] partes = linea.split(",");
                    if (partes.length == 9) {
                        if (partes[0].equals(useractivo.getCorreo()) && fechavencimientoproxima(partes)) {
                            JOptionPane.showMessageDialog(null, "Debe devolver el libro hoy");

                        }

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
}
