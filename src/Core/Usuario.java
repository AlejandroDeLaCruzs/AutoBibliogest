package Core;

import App.VentanaPrincipal;

import javax.swing.*;
import java.io.*;

/**
 * Clase que representa a un usuario en el sistema.
 * Incluye métodos para gestionar el inicio de sesión y la creación de nuevos usuarios.
 */
public class Usuario {
    private String nombre;
    private String idUsuario;
    private String correo;
    private String contrasena;
    private String notificacion;
    public static final String Notificacion_Push = "Push";
    public static final String Notificacion_Correo = "CORREO";

    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }


    /**
     * Obtiene el nombre del usuario.
     *
     * @return el nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre el nombre que se asignará al usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return el correo electrónico del usuario.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param correo el correo electrónico que se asignará al usuario.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return la contraseña del usuario.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contrasena la contraseña que se asignará al usuario.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Obtiene el ID del usuario.
     *
     * @return el ID del usuario.
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el ID del usuario.
     *
     * @param idUsuario el ID que se asignará al usuario.
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(String nombre, String idsuario, String correo, String contrasena) {
        this.nombre = nombre;
        this.idUsuario = idsuario;
        this.correo = correo;
        this.contrasena = contrasena;
    }


    public Usuario() {
    }

    @Override
    public String toString() {
        return idUsuario + "," + contrasena + "," + correo + "," + nombre;
    }


    /**
     * Valida las credenciales de inicio de sesión.
     *
     * @param email             Correo electrónico ingresado por el usuario.
     * @param contrasenia       Contraseña ingresada por el usuario.
     * @param ventanaContenedor Contenedor principal para actualizar el usuario activo.
     * @return {@code true} si las credenciales son válidas, de lo contrario {@code false}.
     */
    //DIVIDIR RESPONASABILIDADES!!!!!!
    public static boolean esValido(String email, char[] contrasenia, VentanaPrincipal ventanaContenedor) {
        boolean usuariovalido = false;
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                if ((email.equals(campos[2]))) {
                    String contraseniaIngresada = new String(contrasenia);
                    if (contraseniaIngresada.equals(campos[1])) {
                        Usuario usuarioactivo = new Usuario(campos[0], campos[1], campos[2], campos[3]);
                        ventanaContenedor.setUsuarioActivo(usuarioactivo);
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * Añade un nuevo usuario al archivo de usuarios si no existe ya.
     */
    public void aniadirUseralArchivo() {
        if (usuarioYaCreado(this)) {
            JOptionPane.showMessageDialog(null, "El correo ya está registrado.",
                    "Usuario existente", JOptionPane.WARNING_MESSAGE);
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt", true))) {
                writer.newLine();
                writer.write(this.toString());
                JOptionPane.showMessageDialog(null, "Usuario creado con éxito.",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Verifica si un usuario ya existe en el archivo de usuarios basado en el correo.
     *
     * @param usuario Usuario a verificar.
     * @return {@code true} si el correo ya está registrado, de lo contrario {@code false}.
     */
    public static boolean usuarioYaCreado(Usuario usuario) {
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length == 4 && usuario.correo.equals(campos[2])) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}
