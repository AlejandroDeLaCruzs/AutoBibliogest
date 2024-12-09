package GUI;

import java.util.HashMap;
import java.util.Map;

/*
Patron Singleton 
 */
public class GeneroImagenes {
    private static GeneroImagenes instance;
    private final Map<String, String> generoImagenes;

    private GeneroImagenes() {
        generoImagenes = new HashMap<>();
        generoImagenes.put("Literatura juvenil", "./res/literaturajuvenil.png");
        generoImagenes.put("Fantasía", "./res/Fantasia.jpg");
        generoImagenes.put("Thriller", "./res/thriller.jpg");
        generoImagenes.put("Literatura contemporánea", "./res/literaturacontemporanea.jpg");
        generoImagenes.put("Default", "./res/literaturacontemporanea.jpg");
    }

    public static GeneroImagenes getInstance() {
        if (instance == null) {
            instance = new GeneroImagenes();
        }
        return instance;
    }

    public String getImagePath(String genero) {
        return generoImagenes.getOrDefault(genero, generoImagenes.get("Default"));
    }
}

