package Core.Validación.Usuarios;

import App.VentanaPrincipal;
import Core.Usuario;

import java.util.ArrayList;

/**
 * Clase encargada de validar las credenciales de inicio de sesión de un usuario.
 */
public class ValidadorInicioSesion {
    /**
     * Valida las credenciales de inicio de sesión.
     *
     * @param email             Correo electrónico ingresado por el usuario.
     * @param contrasenia       Contraseña ingresada por el usuario.
     * @param ventanaContenedor Contenedor principal para actualizar el usuario activo.
     * @return {@code true} si las credenciales son válidas, de lo contrario {@code false}.
     */
    public static boolean esValido(String email, char[] contrasenia, VentanaPrincipal ventanaContenedor, ArrayList<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            if ((email.equals(usuario.getCorreo()))) {
                String contraseniaIngresada = new String(contrasenia);
                if (contraseniaIngresada.equals(usuario.getContrasena())) {
                    Usuario usuarioactivo = new Usuario(usuario.getNombre(), usuario.getIdUsuario(), usuario.getCorreo(), usuario.getContrasena());
                    ventanaContenedor.setUsuarioActivo(usuarioactivo);
                    return true;
                }
            }
        }
        return false;
    }

}
