package Core.Notificaciones;

import javax.swing.*;

public class NotificacionPush extends Notificaciones {
    @Override
    public void notificacion(String titulo) {
        JOptionPane.showMessageDialog(null, " El libro "+ titulo+" vence hoy el préstamo", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
    }
}