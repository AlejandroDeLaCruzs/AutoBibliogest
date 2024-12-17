package Core;

import java.util.ArrayList;

/**
 * Clase que gestiona las reservas de libros realizadas por los usuarios.
 * Proporciona métodos para mostrar las reservas de un usuario y leer la última línea del archivo de préstamos.
 */
abstract public class Reservas {

    /**
     * Obtiene las reservas realizadas por un usuario específico.
     *
     * @param prestamos Lista de todos los préstamos.
     * @param usuarioActivo El usuario cuya información de reservas se desea obtener.
     * @return Lista de préstamos realizados por el usuario.
     */
    public static ArrayList<Prestamos> misPrestamos(ArrayList<Prestamos> prestamos, Usuario usuarioActivo) {
        ArrayList<Prestamos> misPrestamos = new ArrayList<>();
        for (Prestamos prestamo : prestamos) {
            if (prestamo.getCorreo().equals(usuarioActivo.getCorreo())) {
                misPrestamos.add(prestamo);
            }
        }
        return misPrestamos;
    }

    /**
     * Lee y retorna la última línea del archivo `prestamos.txt` como un arreglo de campos.
     *
     * @return Un arreglo de cadenas con los campos de la última línea, o {@code null} si no hay líneas.
     */
    public static Prestamos ultimoPrestamo(ArrayList<Prestamos> prestamos) {
        return prestamos.getLast();
    }
}