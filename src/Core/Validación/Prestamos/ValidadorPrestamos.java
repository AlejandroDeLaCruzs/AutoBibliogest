package Core.Validación.Prestamos;

import Core.Libro;
import Core.Prestamos;
import Core.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * La clase {@code ValidadorPrestamos} proporciona métodos para validar las condiciones de préstamo de libros.
 * Incluye validaciones como comprobar si un libro ya ha sido reservado por un usuario,
 * contar cuántas copias de un libro están prestadas y calcular la próxima fecha en la que un libro estará disponible.
 * Además, verifica la disponibilidad de copias de un libro para su préstamo.
 */
public class ValidadorPrestamos {
    /**
     * Comprueba si un usuario tiene reservado un libro específico.
     *
     * @param libro         Libro a verificar.
     * @param usuarioactivo Usuario activo del sistema.
     * @return {@code true} si el usuario ya reservó el libro, {@code false} de lo contrario.
     */
    public static boolean reservado(Libro libro, Usuario usuarioactivo, ArrayList<Prestamos> prestamos) {
        boolean estareservado = false;
        for(Prestamos prestamo: prestamos) {
            if (prestamo.getTitulo().equals(libro.getTitulo()) && prestamo.getCorreo().equals(usuarioactivo.getCorreo())) {
                return true;
            }
        }
        return estareservado;
    }


    /**
     * Cuenta cuántas copias de un libro están actualmente prestadas.
     *
     * @param libro Libro del cual se desea conocer el número de préstamos.
     * @return El número de copias prestadas.
     */
    public static int contarPrestamosDeLibro(Libro libro, ArrayList<Prestamos> prestamos) {
        int contadorprestamos = 0; //Numero de copias prestadas
        LocalDate fechaActual = LocalDate.now();
        for(Prestamos prestamo: prestamos) {
                if (prestamo.getTitulo().equals(libro.getTitulo()) && fechaActual.isBefore(prestamo.getFechaFinPrestamo())) {
                    contadorprestamos++;
                }
        }
        return contadorprestamos;
    }

    /**
     * Calcula la fecha más próxima en la que un libro estará disponible.
     *
     * @param libro Libro del cual se desea conocer la próxima disponibilidad.
     * @return La fecha más cercana de disponibilidad, o {@code null} si no hay préstamos activos del libro.
     */
    public static LocalDate calcularProximaDisponibilidad(Libro libro, ArrayList<Prestamos> prestamos) {
        LocalDate mejorOpcion = null;
        for (Prestamos prestamo : prestamos) {
            if (prestamo.getTitulo().equals(libro.getTitulo())) {
                // Parsear el string de fecha en campos[3] a LocalDate
                LocalDate fechaDevolucion = prestamo.getFechaFinPrestamo();

                if (mejorOpcion == null || fechaDevolucion.isBefore(mejorOpcion)) {
                    mejorOpcion = fechaDevolucion;
                }
            }
        }
        return mejorOpcion;
    }

    /**
     * Verifica si hay disponibilidad de copias de un libro para préstamo.
     *
     * @param libro Libro a verificar.
     * @return {@code true} si hay copias disponibles, {@code false} de lo contrario.
     */
    public static boolean haydisponibilidad(Libro libro, ArrayList<Prestamos> prestamos) {
        return libro.getCantidad() > contarPrestamosDeLibro(libro, prestamos);
    }
}
