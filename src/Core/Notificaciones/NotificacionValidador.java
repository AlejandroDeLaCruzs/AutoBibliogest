package Core.Notificaciones;

import Core.Prestamos;
import Core.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NotificacionValidador {
    /**
     * Obtiene una lista de títulos de libros cuyo préstamo vence hoy para un usuario dado.
     *
     * @param usuario   el usuario para el que se validarán los préstamos
     * @param prestamos la lista completa de préstamos
     * @return una lista de títulos de libros que vencen hoy
     */
    public static List<String> obtenerLibrosConVencimientoHoy(Usuario usuario, List<Prestamos> prestamos) {
        List<Prestamos> prestamosDelUsuario = filtrarPrestamosPorUsuario(usuario, prestamos);
        return extraerTitulosConVencimientoHoy(prestamosDelUsuario);
    }

    /**
     * Filtra la lista de préstamos para obtener solo los correspondientes a un usuario específico.
     *
     * @param usuario   el usuario para el que se filtrarán los préstamos
     * @param prestamos la lista completa de préstamos
     * @return una lista de préstamos asociados al usuario
     */
    private static List<Prestamos> filtrarPrestamosPorUsuario(Usuario usuario, List<Prestamos> prestamos) {
        List<Prestamos> prestamosFiltrados = new ArrayList<>();
        for (Prestamos prestamo : prestamos) {
            if (prestamo.getCorreo().equals(usuario.getCorreo())) {
                prestamosFiltrados.add(prestamo);
            }
        }
        return prestamosFiltrados;
    }

    /**
     * Extrae los títulos de los préstamos que vencen hoy de una lista de préstamos.
     *
     * @param prestamos la lista de préstamos a evaluar
     * @return una lista de títulos de libros que vencen hoy
     */
    private static List<String> extraerTitulosConVencimientoHoy(List<Prestamos> prestamos) {
        List<String> titulosVencimientoHoy = new ArrayList<>();
        LocalDate fechaActual = LocalDate.now();
        for (Prestamos prestamo : prestamos) {
            if (prestamo.getFechaFinPrestamo().equals(fechaActual)) {
                titulosVencimientoHoy.add(prestamo.getTitulo());
            }
        }
        return titulosVencimientoHoy;
    }

}
