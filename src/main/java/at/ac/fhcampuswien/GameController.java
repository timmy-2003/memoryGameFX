package at.ac.fhcampuswien;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GameController {
    @FXML
    private GridPane Grid_Memory;

    @FXML
    public void initialize() {
        int index = Utilities.randomGenerator(3);
        String[] imageArray = new String[]{"Image_01.png", "Image_02.png", "Image_03.png"};
        Image front = new Image(App.class.getResource(imageArray[index]).toString(), 100, 30, true,false);


        for (int y = 0; y < Grid_Memory.getRowCount(); y++) {  //befüllt beliebig großes Grid mit Buttons
            for (int x = 0; x < Grid_Memory.getColumnCount(); x++) {
                Button button = new Button();
                Grid_Memory.add(button, x, y);
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // button fills cell
                button.setOnAction(actionEvent -> {
                    Button eventButton = (Button) actionEvent.getSource(); //findet den Button der gedrückt wurde

                    eventButton.setGraphic(new ImageView(front));
                }); // was passieren soll, wenn man auf den Button clickt
            }
        }

    }


}