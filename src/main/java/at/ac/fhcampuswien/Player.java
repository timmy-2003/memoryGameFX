package at.ac.fhcampuswien;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> collectedCards; // In der ArrayList werden alle vom Spieler gesammelten Kartenpaare gespeichert
    private int points;
    private String name;

    public Player(ArrayList<Card> collectedCards, int points) { // Konstruktor
        this.collectedCards = collectedCards;
        this.points = points;
    }

    public void setPoints() {
        this.points = this.collectedCards.size() / 2;
    } // Punktezahl berechnet sich aus Anzahl der gesammelten Karten durch zwei dividiert

    public int getPoints() {
        return points;
    }

    public ArrayList<Card> getCollectedCards() {
        return collectedCards;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public void resetButKeepName(){    // Setzt den Spieler für neues Spiel zurück
        this.collectedCards.clear();
        this.points = 0;
    }

}
