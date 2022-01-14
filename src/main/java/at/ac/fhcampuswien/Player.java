package at.ac.fhcampuswien;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> collectedCards; // In der ArrayList werden alle vom Spieler gesammelten Kartenpaare gespeichert
    private int points;
    private String name;

    public Player(ArrayList<Card> collectedCards, int points, String name) { // Konstruktor
        this.collectedCards = collectedCards;
        this.points = points;
        this.name = name;
    }

    public void setPoints() {
        this.points = this.collectedCards.size() / 2;
    } // Punktezahl berechnet sich aus Anzahl der gesammelten Karten durch zwei dividiert

    /*public void addCards (Player p, Card card1, Card card2){
        p.collectedCards.add(card1); //Diese Methode ist eigentlich unnötig, man könnte sie aber auch verwenden
        p.collectedCards.add(card2);
    }*/

    public int getPoints() {
        return points;
    }

    public ArrayList<Card> getCollectedCards() {
        return collectedCards;
    }

    public String getName() {
        return name;
    }
}
