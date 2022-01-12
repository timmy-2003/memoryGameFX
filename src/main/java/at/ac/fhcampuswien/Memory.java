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

    public Memory(String[] images){
        this.board = new Board();

        player1 = new Player(new ArrayList<>(),0, "Slavica");
        player2 = new Player(new ArrayList<>(),0, "Lukas");
        currentPlayer = player1;


        memoryCards = new Card[images.length];
        for (int i = 0; i < images.length; i++){
            Image front = new Image(App.class.getResource(images[i]).toString(), 100, 30, true,false);
            Card card = new Card(front);
           memoryCards[i] = card;
        }

    }

    public Board getBoard(){           // diese Methode liefert ein Board
        return board;
    }


    public void newGame(){   //verteilt Karten auf Brett

        ArrayList<Card> cardList = new ArrayList<>();  //die Karten welche auf dem Feld landen
        for (int i = 0; i < board.getCardCount()/2; i ++){  //Hälfte weil ja jedes Objekt 2 mal auf dem Feld liegt/ Memory hat doppelte Karten
            cardList.add(memoryCards[Utilities.randomGenerator(memoryCards.length)]); // fügt eine zufällige Karte hinzu
        }

        ArrayList<Integer> slots = new ArrayList<>(); //enthält alles slotadressen
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

    public boolean checkIfMatch(Player p, Card firstSelectedCard, Card secondSelectedCard){
            if (firstSelectedCard.equals(secondSelectedCard)){
                p.getCollectedCards().add(firstSelectedCard);
                p.getCollectedCards().add(secondSelectedCard);
                p.setPoints();
                return true;
            }else{
                return false;
            }
        }

        public void selectCard(int index) { //die Logik die passiert, wenn man eine Karte anwählt
            if (board.isOpen(index)) {
                return;
            }

            if(firstSelectedCard != null && secondSelectedCard != null){
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


          /*  Versuch die Methode zu schreiben } else {
              //  if (checkIfMatch(currentPlayer, firstSelectedCard, board.getCard(index)) == true) {
              //      board.setCardState(index, true);
                    //hier weitermachen
                }

            }
        */

    public boolean checkIfEnd(){        //prüft ob Spiel zu Ende
        for (int i = 0; i < board.getCardCount(); i++) {
            if (!board.getCardState(i)){
                return false;
            }
        }
        return true;
    }

    public Player getCurrentPlayer(){
        return currentPlayer;

    }

    public Card getFirstSelectedCard(){
        return firstSelectedCard;
    }

    public Card getSecondSelectedCard(){
        return secondSelectedCard;
    }

    public void switchPlayer(){
        if (currentPlayer == player1){
            currentPlayer = player2;
        }else {
            currentPlayer = player1;
        }
    }

    public  void resetFirstSelectedCard(){
        firstSelectedCard = null;
    }

    public  void resetSecondSelectedCard(){
        secondSelectedCard = null;
    }

    public int getFirstSelectedIndex(){
        return  firstSelectedIndex;
    }

    public int getSecondSelectedIndex(){
        return secondSelectedIndex;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
