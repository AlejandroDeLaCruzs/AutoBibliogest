package Core.Notificaciones;

import Core.Usuario;

public abstract class NotificacionFactory {
    // Método estático que devuelve una instancia de Notificaciones
    /*public static Notificaciones obtenerNotificacion(Usuario usuario) {
        switch (usuario.getNotificacion()) {
            case Usuario.Notificacion_Push:
                return new NotificacionPush();
            case Usuario.Notificacion_Correo:
               return new NotificacionCorreo();
            default:
                throw new IllegalArgumentException("Tipo de notificación no soportado: ");
        }
    }*/
    public abstract Notificaciones crearNotificacion(Usuario usuario);
}
