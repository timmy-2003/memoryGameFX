package at.ac.fhcampuswien;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Memory {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private final Card[] memoryCards;
    private Card firstSelectedCard;
    private int firstSelectedIndex;
    private Card secondSelectedCard;
    private int secondSelectedIndex;

    /**
     * @param images is a String array containing the file names of the images used
     */

    public Memory(String[] images) {
        this.board = new Board();
        player1 = new Player(new ArrayList<>(), 0); // Create two players with an empty list of collected cards and 0 points
        player2 = new Player(new ArrayList<>(), 0);


        int OneOrTwo = Utilities.randomGenerator(2); // First player is selected by random choice
        if (OneOrTwo == 1)
            currentPlayer = player1;
        else
            currentPlayer = player2;


        memoryCards = new Card[images.length]; // memoryCards is filled with cards
        for (int i = 0; i < images.length; i++) {
            Image front = new Image(Objects.requireNonNull(App.class.getResource(images[i])).toString(), 80, 120, true, true);
            Card card = new Card(front);
            memoryCards[i] = card;
        }

    }

    /**
     * newGame "shuffles" the cards and places the cards into the slots
     */

    public void newGame() {

        int OneOrTwo = Utilities.randomGenerator(2); // First player is selected by random choice
        if (OneOrTwo == 1)
            currentPlayer = player1;
        else
            currentPlayer = player2;

        ArrayList<Card> cardList = new ArrayList<>(Arrays.asList(memoryCards));

        ArrayList<Integer> slots = new ArrayList<>(); // numbers the slots
        for (int i = 0; i < board.getCardCount(); i++) {
            slots.add(i);
        }

        for (int i = 0; i < board.getCardCount() / 2; i++) {
            Card card = cardList.remove(Utilities.randomGenerator(cardList.size()));
            Integer slot1 = slots.remove(Utilities.randomGenerator(slots.size()));          // to prevent double pairs, slots are removed
            Integer slot2 = slots.remove(Utilities.randomGenerator(slots.size()));

            board.setCard(slot1, card);            // one card ís placed into two slots
            board.setCard(slot2, card);
            board.setCardState(slot1, false);
            board.setCardState(slot2, false);
        }

        player1.resetButKeepName();
        player2.resetButKeepName();

        resetFirstSelectedCard();
        resetSecondSelectedCard();


    }

    /**
     * @param p                  Player whose selected cards need to be checked
     * @param firstSelectedCard  first card that was clicked by the player
     * @param secondSelectedCard second card that was clicked by the player
     * @return true if they match, false if they don't match
     * add the matching cards two the collectedCards list of the player
     */

    public boolean checkIfMatch(Player p, Card firstSelectedCard, Card secondSelectedCard) {
        if (firstSelectedCard.equals(secondSelectedCard)) {
            p.getCollectedCards().add(firstSelectedCard);
            p.getCollectedCards().add(secondSelectedCard);
            p.setPoints(); // set points (points are calculated by dividing the collected cards by two)
            return true;
        } else {
            return false;
        }
    }

    /**
     * selectCard determines whether a button click is the first or second selected card
     *
     * @param index index of the button clicked by the player
     */

    public void selectCard(int index) {
        if (board.isOpen(index)) {
            return;
        }

        if (firstSelectedCard != null && secondSelectedCard != null) {
            return;
        }
        if (firstSelectedCard == null) {
            firstSelectedCard = board.getCard(index);  // save the selected cards in two variables
            firstSelectedIndex = index;
        } else {
            secondSelectedCard = board.getCard(index);
            secondSelectedIndex = index;
        }
        board.setCardState(index, true); // update the card state to true
    }

    /**
     * checks if the game is over by looping through the card state
     *
     * @return false as soon as it finds a card that is still face down, otherwise return true
     */

    public boolean checkIfEnd() {
        for (int i = 0; i < board.getCardCount(); i++) {
            if (!board.isOpen(i)) {
                return false;
            }
        }
        return true;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;

    }

    public void switchPlayer() { // perform a player switch
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    /**
     * checkWhoWon checks who has more points
     *
     * @return the String that is displayed in the header label at the end of the game
     */
    public String checkWhoWon() {
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
    } // cards are reset to null

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

    public Board getBoard() {
        return board;
    }


}
