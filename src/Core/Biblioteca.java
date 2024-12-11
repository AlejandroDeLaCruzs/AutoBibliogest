package Core;

import Ficheros.FicheroGenerico;

import java.util.ArrayList;



public class Biblioteca {
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    private ArrayList<Usuario> usuarios;

    public ArrayList<Libro> getLibros() {
        return libros;
    }

    public void setLibros(ArrayList<Libro> libros) {
        this.libros = libros;
    }

    private ArrayList<Libro> libros;
    private ArrayList<Prestamos> prestamos;
    public Biblioteca() {
        this.usuarios = new ArrayList<>();
        this.libros = new ArrayList<>();
        this.prestamos = new ArrayList<>();
    }

    public void iniciarBiblioteca() {
        FicheroGenerico<Libro> ficheroLibros = new FicheroGenerico<>(Libro::crearLibro);
        ficheroLibros.cargarFichero("libros.txt", libros);

        FicheroGenerico<Usuario> ficheroUsuario = new FicheroGenerico<>(Usuario::crearUsuario);
        ficheroUsuario.cargarFichero("usuarios.txt", usuarios);

        FicheroGenerico<Prestamos> ficheroPrestamos = new FicheroGenerico<>(Prestamos::crearPrestamo);
        ficheroPrestamos.cargarFichero("prestamos.txt", prestamos);
    }

}
