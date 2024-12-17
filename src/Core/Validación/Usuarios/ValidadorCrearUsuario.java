package Core.Validación.Usuarios;

import Core.Usuario;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Clase encargada de validar los datos de creación de un usuario.
 */
public class ValidadorCrearUsuario {
    /**
     * Valida que los campos de texto no estén vacíos.
     *
     * @param camposTexto Array de campos de texto.
     * @return Mensaje de error si algún campo está vacío; null si todos están completos.
     */
    public static String validarCamposTexto(JTextField[] camposTexto) {
        for (JTextField campo : camposTexto) {
            if (campo.getText().isEmpty()) {
                return "Todos los campos deben estar completos.";
            }
        }
        return null;
    }

    /**
     * Valida las contraseñas ingresadas por el usuario.
     *
     * @param contrasena Contraseña ingresada.
     * @param confirmarContrasena Confirmación de la contraseña.
     * @return Mensaje de error si alguna validación falla; null si las contraseñas son válidas.
     */
    public static String validarContrasenas(String contrasena, String confirmarContrasena) {
        if (contrasena.isEmpty() || confirmarContrasena.isEmpty()) {
            return "Las contraseñas no pueden estar vacías.";
        }

        if (!contrasena.equals(confirmarContrasena)) {
            return "Las contraseñas no coinciden.";
        }

        return null;
    }

    /**
     * Verifica si un usuario ya existe en el archivo de usuarios basado en el correo.
     *
     * @param usuario Usuario a verificar.
     * @return {@code true} si el correo ya está registrado, de lo contrario {@code false}.
     */
    public static boolean usuarioYaCreado(Usuario usuario, ArrayList<Usuario> usuarios) {
        for (Usuario usuario1 : usuarios) {
            if (usuario1.getCorreo().equals(usuario.getCorreo())) {
                return true;
            }
        }
        return false;
    }
}
