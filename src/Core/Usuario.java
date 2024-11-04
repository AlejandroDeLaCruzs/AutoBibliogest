package Core;

import GUI.VentanaPrincipal;

import javax.swing.*;
import java.io.*;

public class Usuario {
    private String nombre;
    private String idsuario;
    private String correo;
    private String contrasena;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getIdsuario() {
        return idsuario;
    }

    public void setIdsuario(String idsuario) {
        this.idsuario = idsuario;
    }

    public Usuario(String nombre, String idsuario, String correo, String contrasena) {
        this.nombre = nombre;
        this.idsuario = idsuario;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public Usuario() {

    }

    @Override
    public String toString() {
        return idsuario + "," + contrasena + "," + correo + "," + nombre;
    }


    //VALIDACION DE DATOS DE INCIO DE SESION---------
    public static boolean esvaldio(String email, String contraosenia, VentanaPrincipal ventanacontador) {
        boolean usuariovalido = false;
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            // Leer línea por línea
            while ((linea = br.readLine()) != null) {
                // Separar los campos por comas usando split
                String[] campos = linea.split(",");

                // Procesar cada campo (en este caso solo imprimirlo)
                if ((email.equals(campos[0])) && contraosenia.equals(campos[1])) {
                    Usuario usuarioactivo = new Usuario(campos[0], campos[1], campos[2], campos[3]);
                    ventanacontador.setUsuarioActivo(usuarioactivo);
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    //Metodo para añadir un usuario nuevo al archivo usuario.txt------------
    public void aniadiruseralarchivo() {
        if(usuarioyacreado(this)){
            JOptionPane.showMessageDialog(null, "EL usuario esta usado");

        }
        else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt", true))) {
                writer.newLine();
                writer.write(this.toString());
                JOptionPane.showMessageDialog(null, "Usuario creado con éxito.");
                System.out.println("Datos de préstamos guardados en " + "usarios.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Metedo para detectar si el usuario ya tiene cuanta y esta intentado volver a crearse una cuenta con el mismo correo
    public static boolean usuarioyacreado(Usuario usuario){
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            // Leer línea por línea
            while ((linea = br.readLine()) != null) {
                // Separar los campos por comas usando split
                String[] campos = linea.split(",");

                // Procesar cada campo (en este caso solo imprimirlo)
               if(usuario.correo.equals(campos[1])){
                   return true;
               }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}
