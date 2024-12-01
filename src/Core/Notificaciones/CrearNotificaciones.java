package Core.Notificaciones;

import Core.Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


abstract public class CrearNotificaciones {
    abstract public void notificacion(Usuario activo);

    public boolean fechavencimientoproxima(String[] campos) {
        boolean fechaproximavencimeinto = false;
        LocalDate fechaActual = LocalDate.now();
        // Definir el formato de la fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Convertir la cadena a LocalDate
        LocalDate fechaVencimmiento = LocalDate.parse(campos[3], formatter);

        return fechaVencimmiento.equals(fechaActual);
    }

}
