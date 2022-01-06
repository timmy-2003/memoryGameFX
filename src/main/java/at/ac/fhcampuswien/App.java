package at.ac.fhcampuswien;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        int index = Utilities.randomGenerator(3);
        String[] imageArray = new String[]{"Image_01.png", "Image_02.png", "Image_03.png"};
        Image front = new Image(App.class.getResource(imageArray[index]).toString(), 100, 30, true,false);

        Card card = new Card(front);

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Game_UI.fxml")); //macht GUI aus FXML file


        Scene scene = new Scene(fxmlLoader.load(), 600, 620);
        stage.setTitle("Memory");
        stage.setScene(scene);
        GridPane grid = (GridPane) scene.lookup("#Grid_Memory"); // Sucht das Element mit der Id Grid_Memory, expliciter cast
        Button button = new Button();
        grid.add(button, 0, 0);
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // button fills cell
        button.setOnAction(actionEvent -> {
            Button eventButton = (Button) actionEvent.getSource(); //findet den Button der gedrückt wurde

            eventButton.setGraphic(new ImageView(front));
        }); // was passieren soll, wenn man auf den Button clickt

        Label label = (Label) scene.lookup("#label_PlayerOneScore");
        label.setText("Score Player Slavica: 1");

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
