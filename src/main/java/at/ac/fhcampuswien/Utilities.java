package at.ac.fhcampuswien;
import java.util.Random;

public final class Utilities {
    public static int randomGenerator(int limit) {
        Random random = new Random();
        int zahl = random.nextInt(limit);
        return zahl;
}
}
