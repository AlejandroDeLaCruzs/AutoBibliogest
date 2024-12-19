package Core.Notificaciones;

import Core.Prestamos;
import Core.Usuario;

import java.util.ArrayList;
import java.util.List;

import static Core.Notificaciones.NotificacionValidador.obtenerLibrosConVencimientoHoy;

/**
 * Clase responsable de enviar notificaciones sobre préstamos de libros vencidos.
 */
public class NotificadorDePrestamos {
    /**
     * Constructor para crear una instancia de NotificadorDePrestamos.
     * Envía notificaciones push a un usuario si tiene libros con vencimiento el día actual.
     *
     * @param usuario   El usuario al que se enviarán las notificaciones.
     * @param prestamos La lista de préstamos activos asociados con el usuario.
     */
    public NotificadorDePrestamos(Usuario usuario, ArrayList<Prestamos> prestamos) {
        // Obtiene los títulos de los libros que vencen hoy
        List<String> tituloLibrosVencenHoy = obtenerLibrosConVencimientoHoy(usuario, prestamos);

        // Enviar notificación por cada título que vence hoy
        if (!tituloLibrosVencenHoy.isEmpty()) {
            for (String titulo : tituloLibrosVencenHoy) {
                enviarNotificacion(titulo);
            }
        }
    }

    /**
     * Envía una notificación push para un libro específico.
     *
     * @param titulo El título del libro que será incluido en la notificación.
     */
    private void enviarNotificacion(String titulo) {
        NotificacionPush notificacionPush = new NotificacionPush();
        notificacionPush.notificacion(titulo);
    }

}
