package GIU;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;
import static java.lang.System.*;

public class Prestamos {
    private Usuario nombre;
    private Libro libroprestado;
    private LocalDate fechainicioprestamo;
    private LocalDate fechafinprestamo;

    public Prestamos(Usuario nombre, Libro libroprestado, LocalDate fechainicioprestamo, LocalDate fechafinprestamo) {
        this.nombre = nombre;
        this.libroprestado = libroprestado;
        this.fechainicioprestamo = fechainicioprestamo;
        this.fechafinprestamo = fechafinprestamo;
    }

    public void reservar(Prestamos prestamo) {
       // LocalDate fechaincio = LocalDate.now();
        //LocalDate fechafin = fechaincio.plusWeeks(2);
        //Prestamos aux = new Prestamos(activo, libro, fechaincio, fechafin);
        try(BufferedWriter writer= new BufferedWriter(new FileWriter("prestamos.txt", true))){
            writer.newLine();
            writer.write(prestamo.toString());
        }
        catch(IOException e){
            e.printStackTrace();

        }
    }

    @Override
    public String toString() {
       // String usuario = this.nombre.toString();
        //this.libroprestado.mostrarInfo();

        return this.nombre + "," +libroprestado.toString()+ "," + this.fechainicioprestamo + ","+
                "," + this.fechafinprestamo;
    }

   /* public static void vistareserva(Prestamos prestamo) {

        prestamo.libroprestado.mostrarInfo();
        //mostrarInfolibro(util.libroprestado);
        out.println(prestamo.nombre);
        out.println("Inicio de prestamo: " + prestamo.fechainicioprestamo);
        out.println("Fecha fin de prestamo: " + prestamo.fechafinprestamo);

    }
*/
    public boolean haydisponibilidad(Libro aux) {
        boolean disponibildad = true;
        int cantidadlibro = aux.getCantidad();
        int contador = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader("prestamos.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null){
                String[] campos = linea.split(",");

            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        for (Prestamos acutales : app.getTotalprestamos()) {
            if (acutales.libroprestado.getTitulo().equals(aux.getTitulo())) {
                contador++;
            }
        }
        if (contador == aux.getCantidad()) {
            disponibildad = false;
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
