package at.ac.fhcampuswien;

public class Board {
    private Card[] cardSlots;
    private boolean[] cardState; //beinhaltet Informationen, ob eine Karte aufgedeckt ist oder nicht (true = aufgedeckt)

    public Board() {
        cardSlots = new Card[20];
        cardState = new boolean[20];
    }

    public Card getCard(int index) { // diese Methode liefert eine Karte aus dem cards Array zurück
        return cardSlots[index];
    }

    public void setCard(int index, Card card) { //setzt Karten ins cards Array
        cardSlots[index] = card;
    }

    public int getCardCount() {
        return cardSlots.length;
    }

    public boolean isOpen(int index) {
        return cardState[index];             // Gibt den Zustand der Karte zurück (aufgedeckt oder nicht)
    }

    public void setCardState(int index, boolean cardState) {
        this.cardState[index] = cardState;
    }

    public boolean getCardState(int index) {
        return cardState[index];
    }

}
