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

        memory.newGame();

        for (int y = 0; y < Grid_Memory.getRowCount(); y++) {  //befüllt beliebig großes Grid mit Buttons
            for (int x = 0; x < Grid_Memory.getColumnCount(); x++) {
                Button button = new Button();
                Grid_Memory.add(button, x, y);
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // button fills cell
                button.setOnAction(actionEvent -> {
                    Button eventButton = (Button) actionEvent.getSource(); //findet den Button der gedrückt wurde

                    int buttonIndex = Integer.parseInt(eventButton.getId());
                    clickOnButton(buttonIndex);

                }); // was passieren soll, wenn man auf den Button clickt
                button.setId(String.valueOf(x+y*Grid_Memory.getColumnCount()));
                buttons[x+y*Grid_Memory.getColumnCount()] = button;
                refreshButton(x+y*Grid_Memory.getColumnCount());
            }
        }

    }

    public void clickOnButton(int index){
        if (memory.getBoard().isOpen(index)){
            memory.getBoard().setCardState(index, false);
        }
        else {
            memory.getBoard().setCardState(index, true);
        }
        refreshButton(index);
    }

    public void refreshButton(int index){   //synchronisiert den Zustand der Karte, logik über den zustand(wann Vorderseite, wann Rückseite angezeigt werden soll)
        Card card = memory.getBoard().getCard(index);
        if(memory.getBoard().isOpen(index)){
            buttons[index].setGraphic(new ImageView(card.getFront()));
        }
        else {
            buttons[index].setGraphic(new ImageView(card.getBackground()));
        }
    }


}