package at.ac.fhcampuswien;

import java.util.ArrayList;

public class Player {
    private final ArrayList<Card> collectedCards; // ArrayList contains the cards collected by the player
    private int points;
    private String name;


    public Player(ArrayList<Card> collectedCards, int points) {
        this.collectedCards = collectedCards;
        this.points = points;
    }

    public void setPoints() {
        this.points = this.collectedCards.size() / 2;
    } // points are calculated by dividing the amount of collected cards by 2

    public int getPoints() {
        return points;
    }

    public ArrayList<Card> getCollectedCards() {
        return collectedCards;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void resetButKeepName() {    // reset points and collectedCards, but don't reset the name (name is set by user input)
        this.collectedCards.clear();
        this.points = 0;
    }

}
