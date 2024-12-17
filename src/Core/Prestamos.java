package Core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static Core.Validación.Prestamos.ValidadorPrestamos.calcularProximaDisponibilidad;

/**
 * Clase que gestiona los préstamos de libros.
 * Permite reservar libros, verificar disponibilidad, y obtener fechas de próxima disponibilidad.
 */
public class Prestamos {
    private String titulo;
    private String correo;
    private LocalDate fechaInicioPrestamo;
    private LocalDate fechaFinPrestamo;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFechaInicioPrestamo() {
        return fechaInicioPrestamo;
    }

    public void setFechaInicioPrestamo(LocalDate fechaInicioPrestamo) {
        this.fechaInicioPrestamo = fechaInicioPrestamo;
    }

    public LocalDate getFechaFinPrestamo() {
        return fechaFinPrestamo;
    }

    public void setFechaFinPrestamo(LocalDate fechaFinPrestamo) {
        this.fechaFinPrestamo = fechaFinPrestamo;
    }



    /**
     * Constructor de la clase Prestamos.
     *
     * @param correo              Correo del usuario que realiza el préstamo.
     * @param titulo              Título del libro prestado.
     * @param fechainicioprestamo Fecha de inicio del préstamo.
     * @param fechafinprestamo    Fecha de finalización del préstamo.
     */
    public Prestamos(String correo, String titulo, LocalDate fechainicioprestamo, LocalDate fechafinprestamo) {
        this.correo = correo;
        this.titulo = titulo;
        this.fechaInicioPrestamo = fechainicioprestamo;
        this.fechaFinPrestamo = fechafinprestamo;
    }

    /**
     * Método estático para crear un préstamo a partir de los campos leídos de un archivo.
     *
     * @param campos Arreglo de cadenas con los datos del préstamo.
     * @return Un objeto Prestamos con la información de los campos.
     */
    public static Prestamos crearPrestamoFichero(String[] campos) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaInicio = LocalDate.parse(campos[2], formatter);
        LocalDate fechaDevolucion = LocalDate.parse(campos[3], formatter);

        return new Prestamos (campos[0], campos[1], fechaInicio, fechaDevolucion);
    }
    /**
     * Representa el préstamo en formato CSV.
     *
     * @return Una cadena con los datos del préstamo separados por comas.
     */
    @Override
    public String toString() {
        return this.correo + "," + this.titulo + "," + this.fechaInicioPrestamo + "," + this.fechaFinPrestamo;
    }


    /**
     * Reserva un libro para un usuario activo.
     *
     * @param libro             El libro que se quiere reservar.
     * @param usuarioActivo     El usuario que realiza la reserva.
     * @param biblioteca        La biblioteca en la que se realiza la reserva.
     */
    public static void reservarlibro(Libro libro, Usuario usuarioActivo, Biblioteca biblioteca) {

        biblioteca.getPrestamos().add(crearPrestamo(usuarioActivo, libro));
    }

    private static Prestamos crearPrestamo(Usuario usuarioActivo, Libro libro) {
        LocalDate fechaincioprestamo = LocalDate.now();
        LocalDate fechafinprestamo = fechaincioprestamo.plusWeeks(2);
        return new Prestamos(usuarioActivo.getCorreo(), libro.getTitulo(), fechaincioprestamo, fechafinprestamo);

    }

    /**
     * Obtiene el mensaje que indica la próxima fecha de disponibilidad de un libro.
     *
     * @param libro    El libro cuya disponibilidad se desea conocer.
     * @param prestamos Lista de los préstamos existentes de libros.
     * @return Un mensaje que indica la fecha de disponibilidad del libro.
     */
    public static String proximaDisponibilidad(Libro libro, ArrayList<Prestamos> prestamos) {
        LocalDate fechaDisponibilidad = calcularProximaDisponibilidad(libro, prestamos);
        return "El libro estará disponible el " + fechaDisponibilidad.toString();
    }
}
