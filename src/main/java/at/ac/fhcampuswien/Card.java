package at.ac.fhcampuswien;

import javafx.scene.image.Image;

import java.util.Objects;

public class Card {

    private final Image front;
    private static final Image rear = new Image(Objects.requireNonNull(App.class.getResourceAsStream("backOfCard.png")), 60, 100, true, true);

    public Card(Image front) {
        this.front = front;
    }

    public Image getFront() {
        return front;
    }

    public Image getRear() {
        return rear;
    }
}
