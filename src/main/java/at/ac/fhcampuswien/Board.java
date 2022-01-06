package at.ac.fhcampuswien;

public class Board {
    private Card[] cards;

    public Board(){
        cards = new Card[20];
    }


    public Card getCard(int index){ // diese Methode liefert eine Karte zurück
        return cards[index];
    }

    public void setCard(int index, Card card){

    }
}
