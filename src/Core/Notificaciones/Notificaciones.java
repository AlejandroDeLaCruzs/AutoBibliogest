package Core.Notificaciones;

import Core.Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


abstract public class Notificaciones {
    abstract void notificacion(Usuario usuario);

}

class NotificacionPush extends Notificaciones {
    void notificacion(Usuario usuario) {

    }
}
