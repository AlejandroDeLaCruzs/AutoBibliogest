package Core;

import GUI.VentanaPrincipal;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
     * @param libro
     * @return bool true si hay disponiblidad
     * En este metodo controlamos si hay disponibilidad del libro del que se quiere reservar. Para ello
     * consultamos el fichero prestamos.txt buscando algún préstamo de ese libro. Al final del metedo comparamos
     * el número de préstamos de ese libro con la cantidad de copias que hay de ese libro para ver si hay alguna de sobra.
     */
    public static int contarPrestamosDeLibro(Libro libro) {
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
        return contadorprestamos;
    }

    public static boolean haydisponibilidad(Libro libro) {
        return libro.getCantidad() > contarPrestamosDeLibro(libro);
    }

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
                        date = LocalDate.parse(campos[3], formatter);

                        // Aquí puedes aplicar lógica adicional, como comparar o asignar a mejorOpcion
                        if (mejorOpcion == null || date.isBefore(mejorOpcion)) {
                            mejorOpcion = date;
                        }
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  mejorOpcion;
    }

    public static String proximadisponibilidad(Libro libro) {

        LocalDate fechaDisponibilidad = calcularProximaDisponibilidad(libro);
        return "El libro estará disponible el " + fechaDisponibilidad.toString();
    }
}
