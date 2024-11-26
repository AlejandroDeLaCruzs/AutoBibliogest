package Core;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
abstract public class Notificaciones {
    abstract public void crearnotificacion(Usuario activo);
    public boolean fechavencimientoproxima(String[] campos){
        boolean fechaproximavencimeinto = false;
        LocalDate fechaActual = LocalDate.now();
        // Definir el formato de la fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Convertir la cadena a LocalDate
        LocalDate fecha = LocalDate.parse(campos[3], formatter);
        if(campos[2].equals(fechaActual)){


        }
    }

}

class NotificacionEmail extends Notificaciones{
    @Override
    public void crearnotificacion(Usuario useractivo) {
        try(BufferedReader reader = new BufferedReader(new FileReader("prestamos.txt"))){
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 9) {
                    if(partes[0].equals(useractivo.getCorreo()) && fechavencimientoproxima(partes)){
                        JOptionPane.showMessageDialog(null, "Debe devolver el libro hoy");

                    }

                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}*/
