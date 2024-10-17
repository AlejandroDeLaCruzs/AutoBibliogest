package GIU;

import java.io.*;
import java.time.LocalDate;

public class Prestamos {
    private Usuario nombre;
    private String titulo;
    private LocalDate fechainicioprestamo;
    private LocalDate fechafinprestamo;

    public Prestamos(Usuario nombre, String titulo, LocalDate fechainicioprestamo, LocalDate fechafinprestamo) {
        this.nombre = nombre;
        this.titulo = titulo;
        this.fechainicioprestamo = fechainicioprestamo;
        this.fechafinprestamo = fechafinprestamo;
    }
/*
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
*/
    @Override
    public String toString() {
       // String usuario = this.nombre.toString();
        //this.libroprestado.mostrarInfo();
        return this.nombre + "," + this.titulo + "," + this.fechainicioprestamo + ","+
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

    public void reservarlibro(Libro libro, Ventana ventanacontenedor) {

        LocalDate fechaincioprestamo = LocalDate.now();
        LocalDate fechafinprestamo = fechaincioprestamo.plusWeeks(2);

       Prestamos nuevoprestamo = new Prestamos(  ventanacontenedor.getUsuarioActivo(), libro.getTitulo(), fechainicioprestamo, fechafinprestamo);

       try(BufferedWriter writer = new BufferedWriter(new FileWriter("prestamos.txt", true))){
            writer.newLine();
            writer.write(nuevoprestamo.toString());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /*
    @param Libro
    @return bool true si hay disponiblidad
    En este metodo controlamos si hay disponibilidad del libro del que se quiere reservar. Para ello
    consultamos el fichero prestamos.txt buscando algun prestamo de ese libro.Al final del metedo comparamos
    el numero de prestamos de ese libro con la cantidad de copias que hay de ese libro para ver si hay alguna de sobra.
     */
    public static boolean haydisponibilidad(Libro aux) {
        boolean disponibildad = false;
        int copiaslibro = aux.getCantidad(); //Cantidad de copias que tiene el libro
        int contadorprestamos = 0; //Numero de copias prestadas

        //Arbrimos el archivo que contiene los prestamos
        try(BufferedReader reader = new BufferedReader(new FileReader("prestamos.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null){
                String[] campos = linea.split(",");
                if(campos[1].equals(aux.getTitulo())){ //Comparamos el titulo
                    contadorprestamos++;
                }

            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        //Comparas el numero de libros prestados con el numero de copias que tiene la biblioteca
        if (contadorprestamos < copiaslibro) {
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
