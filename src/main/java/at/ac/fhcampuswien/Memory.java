package at.ac.fhcampuswien;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Memory {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Card[] memoryCards; //alle Karten des Spiels
    private Card firstSelectedCard;
    private int firstSelectedIndex;
    private Card secondSelectedCard;
    private int secondSelectedIndex;

    public Memory(String[] images) {
        this.board = new Board();

        player1 = new Player(new ArrayList<>(), 0, "Timmy");
        player2 = new Player(new ArrayList<>(), 0, "Patrik");

        int OneOrTwo = Utilities.randomGenerator(2); // Zufall bestimmt, wer zuerst drankommt
        if (OneOrTwo == 1)
            currentPlayer = player1;
        else
            currentPlayer = player2;


        memoryCards = new Card[images.length];
        for (int i = 0; i < images.length; i++) {
            Image front = new Image(App.class.getResource(images[i]).toString(), 100, 30, true, false);
            Card card = new Card(front);
            memoryCards[i] = card;
        }

    }

    public void newGame() {   //verteilt Karten auf Brett

        ArrayList<Card> cardList = new ArrayList<>();  //die Karten welche auf dem Feld landen
        for (int i = 0; i < board.getCardCount() / 2; i++) {  //Hälfte weil ja jedes Objekt 2 mal auf dem Feld liegt/ Memory hat doppelte Karten
            cardList.add(memoryCards[Utilities.randomGenerator(memoryCards.length)]); // fügt eine zufällige Karte hinzu
        }

        ArrayList<Integer> slots = new ArrayList<>(); //enthält alles Slot-Addressen
        for (int i = 0; i < board.getCardCount(); i++) {
            slots.add(i);
        }

        for (int i = 0; i < cardList.size(); i++) {
            Card card = cardList.get(i);
            Integer slot1 = slots.remove(Utilities.randomGenerator(slots.size()));          //dieser Eintrag wird aus der Liste gelöscht, dadurch kann er nicht doppelt vorkommen
            Integer slot2 = slots.remove(Utilities.randomGenerator(slots.size()));

            board.setCard(slot1, card);            //Eine Karte wird auf 2 Stellen des Bretts gesetzt
            board.setCard(slot2, card);
        }


    }

    public boolean checkIfMatch(Player p, Card firstSelectedCard, Card secondSelectedCard) { //Fügt bei einem Match die gesammelten Karten zum Player hinzu, aktualisiert die Punkte
        // kommt es nicht zu einem Match, wird false zurückgeliefert
        if (firstSelectedCard.equals(secondSelectedCard)) {
            p.getCollectedCards().add(firstSelectedCard);
            p.getCollectedCards().add(secondSelectedCard);
            p.setPoints();
            return true;
        } else {
            return false;
        }
    }

    public void selectCard(int index) { //die Logik die passiert, wenn man eine Karte anwählt
        if (board.isOpen(index)) {
            return;
        }

        if (firstSelectedCard != null && secondSelectedCard != null) {
            return;
        }
        if (firstSelectedCard == null) {
            firstSelectedCard = board.getCard(index);
            firstSelectedIndex = index;
            board.setCardState(index, true);
        } else {
            secondSelectedCard = board.getCard(index);
            secondSelectedIndex = index;
            board.setCardState(index, true);
        }
    }

    public boolean checkIfEnd() {        // Überprüft, ob das Spiel aus ist, indem die Kartenzustände (true = aufgedeckt, false = zugedeckt) kontrolliert werden
        for (int i = 0; i < board.getCardCount(); i++) {
            if (!board.getCardState(i)) {
                return false;
            }
        }
        return true;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;

    }

    public void switchPlayer() { //Methode führt einen Spielerwechsel aus
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    public String checkWhoWon() { //Methode liefert den Text zurück, der am Ende des Spiels angezeigt werden soll (Gewinner oder Gleichstand)
        if (player1.getPoints() > player2.getPoints()) {
            return player1.getName() + " has won the game!";
        }
        if (player2.getPoints() > player1.getPoints()) {
            return player2.getName() + " has won the game!";
        } else {
            return "Nobody wins! ";
        }
    }

    public void resetFirstSelectedCard() {
        firstSelectedCard = null;
    } // Karten werden auf null zurückgesetzt, damit der Speicher für die Karten wieder verwendet werden kann

    public void resetSecondSelectedCard() {
        secondSelectedCard = null;
    }

    public int getFirstSelectedIndex() {
        return firstSelectedIndex;
    }

    public int getSecondSelectedIndex() {
        return secondSelectedIndex;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Card getFirstSelectedCard() {
        return firstSelectedCard;
    }

    public Card getSecondSelectedCard() {
        return secondSelectedCard;
    }

    public Board getBoard() {           // diese Methode liefert ein Board
        return board;
    }
}
