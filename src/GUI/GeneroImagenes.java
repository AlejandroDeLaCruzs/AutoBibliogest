package GUI;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que implementa el patrón Singleton para gestionar las rutas de imágenes asociadas a diferentes géneros literarios.
 */
public class GeneroImagenes {
    /**
     * Instancia única de la clase GeneroImagenes.
     */
    private static GeneroImagenes instance;

    /**
     * Mapa que asocia los géneros literarios con las rutas de las imágenes correspondientes.
     */
    private final Map<String, String> generoImagenes;

    /**
     * Constructor privado que inicializa el mapa con los géneros literarios y sus imágenes asociadas.
     */
    private GeneroImagenes() {
        generoImagenes = new HashMap<>();
        generoImagenes.put("Literatura juvenil", "./res/literaturajuvenil.png");
        generoImagenes.put("Fantasía", "./res/Fantasia.jpg");
        generoImagenes.put("Thriller", "./res/thriller.jpg");
        generoImagenes.put("Literatura contemporánea", "./res/literaturacontemporanea.jpg");
        generoImagenes.put("Default", "./res/literaturacontemporanea.jpg");
    }

    /**
     * Obtiene la instancia única de la clase GeneroImagenes.
     *
     * @return La instancia única de GeneroImagenes.
     */
    public static GeneroImagenes getInstance() {
        if (instance == null) {
            instance = new GeneroImagenes();
        }
        return instance;
    }

    /**
     * Obtiene la ruta de la imagen asociada a un género literario específico.
     * Si el género no está definido, devuelve la ruta de la imagen por defecto.
     *
     * @param genero El género literario para el que se desea obtener la imagen.
     * @return La ruta de la imagen asociada al género, o la ruta de la imagen por defecto si no existe.
     */
    public String getImagePath(String genero) {
        return generoImagenes.getOrDefault(genero, generoImagenes.get("Default"));
    }
}

