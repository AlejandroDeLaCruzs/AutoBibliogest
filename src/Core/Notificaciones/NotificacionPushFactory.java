package Core.Notificaciones;

import Core.Usuario;

public class NotificacionPushFactory  extends NotificacionFactory{
    @Override
    public Notificaciones crearNotificacion(Usuario usuario) {
        return new NotificacionPush();
    }
}
