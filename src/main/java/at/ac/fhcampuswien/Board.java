package at.ac.fhcampuswien;

public class Board {
    private final Card[] cardSlots;
    private final boolean[] cardState;

    /**
     * cardSlots contains the cards, cardState contains info whether a card is open or not (true = open, false = closed)
     */

    public Board() {
        cardSlots = new Card[24];
        cardState = new boolean[24];
    }

    public Card getCard(int index) { // use this method to get a card at a specified index
        return cardSlots[index];
    }

    public void setCard(int index, Card card) { // method places a card into the array at a specified index
        cardSlots[index] = card;
    }

    public int getCardCount() {
        return cardSlots.length;
    }

    public boolean isOpen(int index) {
        return cardState[index];             // returns the card state at an index
    }

    public void setCardState(int index, boolean cardState) {
        this.cardState[index] = cardState;
    }


}
