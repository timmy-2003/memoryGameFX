package at.ac.fhcampuswien;

import java.util.ArrayList;

public class Player {
    private ArrayList <Card> collectedCards;
    private int points;
    private String name;

    public Player(ArrayList<Card> collectedCards, int points, String name) {
        this.collectedCards = collectedCards;
        this.points = points;
        this.name = name;
    }

    public void setPoints() {
        this.points = this.collectedCards.size()/2;
    }

    public void addCards (Player p, Card card1, Card card2){
        p.collectedCards.add(card1);
        p.collectedCards.add(card2);
    }

    public int getPoints() {
        return points;
    }

    public ArrayList<Card> getCollectedCards(){
        return collectedCards;
    }

    public String getName(){
        return name;
    }



}
