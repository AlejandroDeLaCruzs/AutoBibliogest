package Core;

import App.VentanaPrincipal;

import javax.swing.*;
import java.io.*;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;

import static Core.Validación.Usuarios.ValidadorCrearUsuario.usuarioYaCreado;


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


    public static Usuario crearUsuario(String[] partes) {
        return new Usuario(partes[0], partes[1], partes[2], partes[3]);
    }

    @Override
    public String toString() {
        return idUsuario + "," + contrasena + "," + correo + "," + nombre;
    }

    public Usuario() {
    }


    /**
     * Añade un nuevo usuario al archivo de usuarios si no existe ya.
     */
    public void aniadirUseralArchivo(ArrayList<Usuario> usuarios) {
        if (usuarioYaCreado(this, usuarios)) {
            JOptionPane.showMessageDialog(null, "El correo ya está registrado.",
                    "Usuario existente", JOptionPane.WARNING_MESSAGE);
        } else {
            usuarios.add(this);
            JOptionPane.showMessageDialog(null, "Usuario creado con éxito.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

