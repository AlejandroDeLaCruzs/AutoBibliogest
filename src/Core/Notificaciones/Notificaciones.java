package Core.Notificaciones;

import Core.Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@FunctionalInterface
public interface Notificaciones {
    void notificacion(Usuario usuario);
}
