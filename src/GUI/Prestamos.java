package GUI;

import java.io.*;
import java.time.LocalDate;

public class Prestamos {
    private String correo;
    private String titulo;
    private LocalDate fechainicioprestamo;
    private LocalDate fechafinprestamo;

    public Prestamos(String correo, String titulo, LocalDate fechainicioprestamo, LocalDate fechafinprestamo) {
        this.correo = correo;
        this.titulo = titulo;
        this.fechainicioprestamo = fechainicioprestamo;
        this.fechafinprestamo = fechafinprestamo;
    }

    @Override
    public String toString() {
        return this.correo + "," + this.titulo + "," + this.fechainicioprestamo + "," + this.fechafinprestamo;
    }


    /**
    @param libro del cual se quiere realizar la reserva
    @param ventanacontenedor
    En este metedo escribimos los datos de la reserva en el fichero: prestamos.txt
     */
    public static void reservarlibro(Libro libro, Ventana ventanacontenedor) {
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
    @param libro
    @return bool true si hay disponiblidad
    En este metodo controlamos si hay disponibilidad del libro del que se quiere reservar. Para ello
    consultamos el fichero prestamos.txt buscando algún préstamo de ese libro. Al final del metedo comparamos
    el número de préstamos de ese libro con la cantidad de copias que hay de ese libro para ver si hay alguna de sobra.
     */
    public static boolean haydisponibilidad(Libro libro) {
        boolean disponibildad = false;
        int copiaslibro = libro.getCantidad(); //Cantidad de copias que tiene el libro
        int contadorprestamos = 0; //Numero de copias prestadas

        //Arbrimos el archivo que contiene los préstamos
        try (BufferedReader reader = new BufferedReader(new FileReader("prestamos.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length == 4) {
                    if (libro.getTitulo().equals(campos[1])) { //Comparamos el titulo
                        contadorprestamos++;
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Comparas el número de libros prestados con el número de copias que tiene la biblioteca
        if (contadorprestamos < copiaslibro) {
            disponibildad = true;
        }
        return disponibildad;
    }

    /*
    public static LocalDate proximadisponibilidad(Libro libro, Biblioteca app) {
        LocalDate date;
        LocalDate mejorOpcion = null;

        if (libro.getCantidad() == 1) {
            for (int i = 0; i < app.getTotalprestamos().size(); i++) {
                Prestamos prestamoActual = app.getTotalprestamos().get(i);
                if (libro.equals(prestamoActual.libroprestado)) {
                    // Si coincide, devolver la fecha de finalización del préstamo
                    return prestamoActual.fechafinprestamo;
                }
            }
        } else {
            for (Prestamos prestamoActual : app.getTotalprestamos()) {
                if (libro.equals(prestamoActual.libroprestado)) {
                    LocalDate fechaFinPrestamo = prestamoActual.fechafinprestamo;

                    // Si mejorOpcion es null, la inicializamos con la primera fecha encontrada
                    if (mejorOpcion == null || fechaFinPrestamo.isBefore(mejorOpcion)) {
                        mejorOpcion = fechaFinPrestamo;
                    }
                }
            }
        }
        return mejorOpcion;

    }*/

}
