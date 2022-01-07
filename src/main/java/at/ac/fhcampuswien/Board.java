package at.ac.fhcampuswien;

public class Board {
    private Card[] cards;
    private boolean[] cardState; //beinhaltet informationen ob eine Karte aufgedeckt ist oder nicht (true = aufgedeckt)

    public Board(){
        cards = new Card[20];
        cardState = new boolean[20];
    }


    public Card getCard(int index){ // diese Methode liefert eine Karte zurück aus dem cards Array
        return cards[index];
    }

    public void setCard(int index, Card card){ //setzt Karten ins cards Array
        cards[index] = card;
    }

    public boolean isOpen(int index){
        return cardState[index];             // gibt den zustand der Karte zurück (aufgedeckt oder nicht)
    }

    public void setCardState(int index, boolean cardState){
        this.cardState[index] = cardState;
    }
}
