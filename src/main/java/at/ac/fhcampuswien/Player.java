package at.ac.fhcampuswien;

import java.util.ArrayList;

public class Player {
    private ArrayList <Card> collectedCards;
    private int points;


    public Player(ArrayList<Card> collectedCards, int points) {
        this.collectedCards = collectedCards;
        this.points = points;

    }

    public void setPoints() {
        this.points = this.collectedCards.size()/2;
    } //aktualisiert den Punktestand indem es die Anzahl an gesammelten Karten durch 2 dividiert

    public void addCards (Player p, Card card1, Card card2){
        p.collectedCards.add(card1);
        p.collectedCards.add(card2);
    }  //fügt zwei gerade gesammelte Karten zur Arraylist hinzu

    public int getPoints() {
        return points;
    }

    public ArrayList<Card> getCollectedCards() {
        return collectedCards;
    }


}
