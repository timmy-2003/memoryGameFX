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

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Game_UI.fxml")); //macht GUI aus FXML file


        Scene scene = new Scene(fxmlLoader.load(), 800, 620);
        stage.setTitle("Memory");
        stage.getIcons().add(new Image (App.class.getResourceAsStream("windowIcon.png")));
        stage.setScene(scene);

        Button newGameButton = (Button) scene.lookup("#newGameButton");
        newGameButton.setFocusTraversable(false);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
