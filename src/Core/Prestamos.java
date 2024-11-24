package Core;

import GUI.VentanaPrincipal;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Clase que gestiona los préstamos de libros.
 * Permite reservar libros, verificar disponibilidad, y obtener fechas de próxima disponibilidad.
 */
public class Prestamos {
    private String correo;
    private String titulo;
    private LocalDate fechainicioprestamo;
    private LocalDate fechafinprestamo;

    /**
     * Constructor de la clase Prestamos.
     *
     * @param correo             Correo del usuario que realiza el préstamo.
     * @param titulo             Título del libro prestado.
     * @param fechainicioprestamo Fecha de inicio del préstamo.
     * @param fechafinprestamo   Fecha de finalización del préstamo.
     */
    public Prestamos(String correo, String titulo, LocalDate fechainicioprestamo, LocalDate fechafinprestamo) {
        this.correo = correo;
        this.titulo = titulo;
        this.fechainicioprestamo = fechainicioprestamo;
        this.fechafinprestamo = fechafinprestamo;
    }

    /**
     * Representa el préstamo en formato CSV.
     *
     * @return Una cadena con los datos del préstamo separados por comas.
     */
    @Override
    public String toString() {
        return this.correo + "," + this.titulo + "," + this.fechainicioprestamo + "," + this.fechafinprestamo;
    }


    /**
     * @param libro             del cual se quiere realizar la reserva
     * @param ventanacontenedor En este metedo escribimos los datos de la reserva en el fichero: prestamos.txt
     */
    public static void reservarlibro(Libro libro, VentanaPrincipal ventanacontenedor) {
        LocalDate fechaincioprestamo = LocalDate.now();
        LocalDate fechafinprestamo = fechaincioprestamo.plusWeeks(2);

        Prestamos nuevoprestamo = new Prestamos(ventanacontenedor.getUsuarioActivo().getCorreo(), libro.getTitulo(), fechaincioprestamo, fechafinprestamo);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("prestamos.txt", true))) {
            writer.newLine();
            writer.write(nuevoprestamo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cuenta cuántas copias de un libro están actualmente prestadas.
     *
     * @param libro Libro del cual se desea conocer el número de préstamos.
     * @return El número de copias prestadas.
     */
    public static int contarPrestamosDeLibro(Libro libro) {
        int contadorprestamos = 0; //Numero de copias prestadas

        try (BufferedReader reader = new BufferedReader(new FileReader("prestamos.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length == 4) {
                    if (libro.getTitulo().equals(campos[1])) {
                        contadorprestamos++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contadorprestamos;
    }

    /**
     * Verifica si hay disponibilidad de copias de un libro para préstamo.
     *
     * @param libro Libro a verificar.
     * @return {@code true} si hay copias disponibles, {@code false} de lo contrario.
     */
    public static boolean haydisponibilidad(Libro libro) {
        return libro.getCantidad() > contarPrestamosDeLibro(libro);
    }

    /**
     * Comprueba si un usuario tiene reservado un libro específico.
     *
     * @param libro          Libro a verificar.
     * @param usuarioactivo  Usuario activo del sistema.
     * @return {@code true} si el usuario ya reservó el libro, {@code false} de lo contrario.
     */
    public static boolean reservado(Libro libro, Usuario usuarioactivo) {
        boolean estareservado = false;
        try(BufferedReader reader = new BufferedReader(new FileReader("prestamos.txt"))){
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length == 4) {
                    if(campos[1].equals(libro.getTitulo()) && campos[0].equals(usuarioactivo.getCorreo())){
                        return true;
                    }
                }
            }
        } catch (IOException e) {
           e.printStackTrace();
        }
        return estareservado;
    }

    /**
     * Calcula la fecha más próxima en la que un libro estará disponible.
     *
     * @param libro Libro del cual se desea conocer la próxima disponibilidad.
     * @return La fecha más cercana de disponibilidad, o {@code null} si no hay préstamos activos del libro.
     */
    public static LocalDate calcularProximaDisponibilidad(Libro libro) {
        LocalDate date;
        LocalDate mejorOpcion = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader reader = new BufferedReader(new FileReader("prestamos.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length == 4) {
                    if (campos[1].equals(libro.getTitulo())) {
                        // Parsear el string de fecha en campos[3] a LocalDate
                        LocalDate fechaDevolucion = LocalDate.parse(campos[3], formatter);

                        if (mejorOpcion == null || fechaDevolucion.isBefore(mejorOpcion)) {
                            mejorOpcion = fechaDevolucion;
                        }
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  mejorOpcion;
    }

    /**
     * Obtiene un mensaje que indica cuándo estará disponible un libro.
     *
     * @param libro Libro del cual se desea conocer la próxima disponibilidad.
     * @return Un mensaje con la fecha de disponibilidad del libro.
     */
    public static String proximadisponibilidad(Libro libro) {
        LocalDate fechaDisponibilidad = calcularProximaDisponibilidad(libro);
        return "El libro estará disponible el " + fechaDisponibilidad.toString();
    }
}
