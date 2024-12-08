package Core.Notificaciones;

import Core.Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public interface Notificaciones {
    void notificacion(Usuario usuario);
}
