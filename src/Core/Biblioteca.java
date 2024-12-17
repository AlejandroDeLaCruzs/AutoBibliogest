package Core;

import Ficheros.FicheroGenerico;

import java.util.ArrayList;


/**
 * Clase que gestiona la biblioteca, incluyendo los usuarios, libros y préstamos.
 * Permite cargar y guardar la información de la biblioteca desde/hacia archivos.
 */
public class Biblioteca {
    private ArrayList<Usuario> usuarios;
    private ArrayList<Libro> libros;
    private ArrayList<Prestamos> prestamos;



    /**
     * Obtiene la lista de usuarios de la biblioteca.
     *
     * @return Lista de usuarios registrados en la biblioteca.
     */
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Establece la lista de usuarios de la biblioteca.
     *
     * @param usuarios Lista de usuarios a establecer.
     */
    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }


    /**
     * Obtiene la lista de libros disponibles en la biblioteca.
     *
     * @return Lista de libros en la biblioteca.
     */
    public ArrayList<Libro> getLibros() {
        return libros;
    }

    /**
     * Establece la lista de libros de la biblioteca.
     *
     * @param libros Lista de libros a establecer.
     */
    public void setLibros(ArrayList<Libro> libros) {
        this.libros = libros;
    }

    /**
     * Obtiene la lista de préstamos realizados en la biblioteca.
     *
     * @return Lista de préstamos.
     */
    public ArrayList<Prestamos> getPrestamos() {
        return prestamos;
    }

    /**
     * Establece la lista de préstamos de la biblioteca.
     *
     * @param prestamos Lista de préstamos a establecer.
     */
    public void setPrestamos(ArrayList<Prestamos> prestamos) {
        this.prestamos = prestamos;
    }

    /**
     * Constructor que inicializa la biblioteca con listas vacías para usuarios, libros y préstamos.
     */
    public Biblioteca() {
        this.usuarios = new ArrayList<>();
        this.libros = new ArrayList<>();
        this.prestamos = new ArrayList<>();
    }


    /**
     * Carga los datos de libros, usuarios y préstamos desde los archivos correspondientes.
     */
    public void iniciarBiblioteca() {
        FicheroGenerico<Libro> ficheroLibros = new FicheroGenerico<>(Libro::crearLibro);
        ficheroLibros.cargarFichero("libros.txt", libros);

        FicheroGenerico<Usuario> ficheroUsuario = new FicheroGenerico<>(Usuario::crearUsuario);
        ficheroUsuario.cargarFichero("usuarios.txt", usuarios);

        FicheroGenerico<Prestamos> ficheroPrestamos = new FicheroGenerico<>(Prestamos::crearPrestamoFichero);
        ficheroPrestamos.cargarFichero("prestamos.txt", prestamos);
    }

    /**
     * Guarda los datos de libros, usuarios y préstamos en los archivos correspondientes.
     */
    public void cerrarBiblioteca() {
        FicheroGenerico<Libro> ficheroLibros = new FicheroGenerico<>(null);
        ficheroLibros.escribirFichero("libros.txt", libros);

        FicheroGenerico<Usuario> ficheroUsuario = new FicheroGenerico<>(null);
        ficheroUsuario.escribirFichero("usuarios.txt", usuarios);

        FicheroGenerico<Prestamos> ficheroPrestamos = new FicheroGenerico<>(null);
        ficheroPrestamos.escribirFichero("prestamos.txt", prestamos);
    }

}
