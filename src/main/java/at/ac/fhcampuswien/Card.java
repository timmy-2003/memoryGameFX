package at.ac.fhcampuswien;

import javafx.scene.image.Image;

public class Card {

    private Image front;
    private static Image background = new Image(App.class.getResourceAsStream("background.png"), 100, 30, false, false); //Kartenrückseite

    public Card(Image front) {
        this.front = front;
    }

    public Image getFront() {
        return front;
    }

    public Image getBackground() {
        return background;
    }
}
