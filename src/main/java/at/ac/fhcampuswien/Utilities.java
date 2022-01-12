package at.ac.fhcampuswien;

import java.util.Random;

public final class Utilities {
    public static int randomGenerator(int limit) { //Methode liefert eine Zufallszahl zurück
        Random random = new Random();
        return random.nextInt(limit);
    }
}
