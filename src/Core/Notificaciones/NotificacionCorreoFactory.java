package Core.Notificaciones;

import Core.Usuario;

public class NotificacionCorreoFactory  extends NotificacionFactory {
    @Override
    public Notificaciones crearNotificacion(Usuario usuario) {
        return new NotificacionCorreo();
    }
}
