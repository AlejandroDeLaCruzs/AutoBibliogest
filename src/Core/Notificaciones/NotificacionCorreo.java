package Core.Notificaciones;

import Core.Usuario;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NotificacionCorreo implements Notificaciones {
    @Override
    public void notificacion(Usuario activo) {
        System.out.println("prueba");
    }
}

