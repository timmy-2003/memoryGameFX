package at.ac.fhcampuswien;

import javafx.scene.image.Image;

public class Memory {

    private Board board;
    private Player player1;
    private Player player2;
    private Card[] memoryCards; //alle Karten des Spiels

    public Memory(String[] images){
        this.board = new Board();

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

    public void newGame(){

    }

}
