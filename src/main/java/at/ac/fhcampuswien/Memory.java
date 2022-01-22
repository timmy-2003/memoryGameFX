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
        player1 = new Player(new ArrayList<>(), 0); // Zwei Spieler anlegen
        player2 = new Player(new ArrayList<>(), 0);

        int OneOrTwo = Utilities.randomGenerator(2); // Zufall bestimmt, wer zuerst drankommt
        if (OneOrTwo == 1)
            currentPlayer = player1;
        else
            currentPlayer = player2;


        memoryCards = new Card[images.length]; // memoryCards (alle Karten des Spiels) wird mit Karten befüllt
        for (int i = 0; i < images.length; i++) {
            Image front = new Image(App.class.getResource(images[i]).toString(), 80, 120, true, true);
            Card card = new Card(front);
            memoryCards[i] = card;
        }

    }

    public void newGame() {   //verteilt Karten auf Brett

        int OneOrTwo = Utilities.randomGenerator(2); // Zufall bestimmt, wer zuerst drankommt
        if (OneOrTwo == 1)
            currentPlayer = player1;
        else
            currentPlayer = player2;

        ArrayList<Card> cardList = new ArrayList<>();  //die Karten welche auf dem Feld landen
        for (int i = 0; i < memoryCards.length; i++) {
            cardList.add(memoryCards[i]);
        }

        ArrayList<Integer> slots = new ArrayList<>(); //enthält alle Slot-"Adressen"
        for (int i = 0; i < board.getCardCount(); i++) {
            slots.add(i);
        }

        for (int i = 0; i < board.getCardCount()/2; i++) {
            Card card = cardList.remove(Utilities.randomGenerator(cardList.size()));
            Integer slot1 = slots.remove(Utilities.randomGenerator(slots.size()));          //dieser Eintrag wird aus der Liste gelöscht, dadurch kann er nicht doppelt vorkommen
            Integer slot2 = slots.remove(Utilities.randomGenerator(slots.size()));

            board.setCard(slot1, card);            //Eine Karte wird auf 2 Stellen des Bretts gesetzt
            board.setCard(slot2, card);
            board.setCardState(slot1, false);
            board.setCardState(slot2, false);
        }

        player1.resetButKeepName();
        player2.resetButKeepName();

        resetFirstSelectedCard();
        resetSecondSelectedCard();


    }

    public boolean checkIfMatch(Player p, Card firstSelectedCard, Card secondSelectedCard) { //Fügt bei einem Match die gesammelten Karten zum Player hinzu, aktualisiert die Punkte
        // kommt es nicht zu einem Match, wird false zurückgeliefert
        if (firstSelectedCard.equals(secondSelectedCard)) {
            p.getCollectedCards().add(firstSelectedCard);
            p.getCollectedCards().add(secondSelectedCard);
            p.setPoints(); // Punkte werden anhand der Anzahl der gesammelten Karten berechnet, daher müssen zuerst die Karten hinzugefügt, und erst dann die Punkte aktualisiert werden
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
            firstSelectedCard = board.getCard(index);  //Speichert die vom Spieler ausgewählten Karten in zwei Variablen
            firstSelectedIndex = index;
            board.setCardState(index, true); // Kartenzustand wird aktualisiert, da die Karte ja jetzt aufgedeckt ist --> Das ist notwendig, damit die Methode refreshButton die Karte "umdrehen" kann
        } else {
            secondSelectedCard = board.getCard(index);
            secondSelectedIndex = index;
            board.setCardState(index, true);
        }
    }

    public boolean checkIfEnd() {        // Überprüft, ob das Spiel aus ist, indem die Kartenzustände (true = aufgedeckt, false = zugedeckt) kontrolliert werden
        for (int i = 0; i < board.getCardCount(); i++) {
            if (!board.isOpen(i)) {
                return false;
            }
        }
        return true;
    }

    public Player getCurrentPlayer() { // Liefert den aktuellen Spieler zurück
        return currentPlayer;

    }

    public void switchPlayer() { //Methode führt einen Spielerwechsel aus
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    public String checkWhoWon() { // Methode liefert den Text zurück, der am Ende des Spiels angezeigt werden soll (Gewinner oder Gleichstand)
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
    }           //Diverse getter-Methoden für den Controller

    public Player getPlayer2() {
        return player2;
    }

    public Card getFirstSelectedCard() {
        return firstSelectedCard;
    }

    public Card getSecondSelectedCard() {
        return secondSelectedCard;
    }

    public Board getBoard() {
        return board;
    }
}
