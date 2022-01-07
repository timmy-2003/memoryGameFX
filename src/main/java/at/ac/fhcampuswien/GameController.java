package at.ac.fhcampuswien;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GameController {
    @FXML
    private GridPane Grid_Memory;
    private Button[] buttons;

    private Memory memory;

    @FXML
    public void initialize() {                         //diese Methode wird ausgeführt sobald das Gui geladen ist
        String[] imageArray = new String[]{"Image_01.png", "Image_02.png", "Image_03.png"};
        buttons = new Button[Grid_Memory.getRowCount()*Grid_Memory.getColumnCount()];

        memory = new Memory(imageArray);

        for (int y = 0; y < Grid_Memory.getRowCount(); y++) {  //befüllt beliebig großes Grid mit Buttons
            for (int x = 0; x < Grid_Memory.getColumnCount(); x++) {
                Button button = new Button();
                Grid_Memory.add(button, x, y);
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // button fills cell
                button.setOnAction(actionEvent -> {
                    Button eventButton = (Button) actionEvent.getSource(); //findet den Button der gedrückt wurde

                   // eventButton.setGraphic(new ImageView(front));
                }); // was passieren soll, wenn man auf den Button clickt

                buttons[x+y*Grid_Memory.getColumnCount()] = button;
            }
        }

    }

    public void refreshButton(int index){   //synachronisiert den Zustand der Karte, logik über den zustand(wann Vorderseite, wann Rückseite angezeigt werden soll)
        Card card = memory.getBoard().getCard(index);
        if(memory.getBoard().isOpen(index)){
            buttons[index].setGraphic(new ImageView(card.getFront()));
        }
        else {
            buttons[index].setGraphic(new ImageView(card.getBackground()));
        }
    }


}