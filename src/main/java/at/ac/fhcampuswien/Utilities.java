package at.ac.fhcampuswien;

import java.util.Random;

public final class Utilities {
    public static int randomGenerator(int limit) { // returns a random number up to a limit
        Random random = new Random();
        return random.nextInt(limit);
    }
}
