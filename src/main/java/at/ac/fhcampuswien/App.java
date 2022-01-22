package at.ac.fhcampuswien;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(App.class.getResource("Game_UI.fxml")); //macht GUI aus FXML file
        FXMLLoader fxmlLoader2 = new FXMLLoader(App.class.getResource("startscreen.fxml"));

        Scene scene1 = new Scene(fxmlLoader1.load(), 800, 640);
        Scene scene2 = new Scene(fxmlLoader2.load(), 520, 640);
        scene1.getStylesheets().add(getClass().getResource("memory.css").toExternalForm());
        scene2.getStylesheets().add(getClass().getResource("memory.css").toExternalForm());
        stage.setScene(scene2);
        Button startButton = (Button) scene2.lookup("#Play_Button");
        startButton.setOnAction(e -> {
            stage.setScene(scene1);
        });
        Button menuButton = (Button) scene1.lookup("#menuButton");
        menuButton.setOnAction(e -> stage.setScene(scene2));
        ChoiceBox<String> themeSelector = (ChoiceBox<String>) scene2.lookup("#themeSelector");
        themeSelector.getItems().add(0, "Flags");
        themeSelector.getItems().add(1, "Christmas");
        stage.setTitle("Memory");
        stage.getIcons().add(new Image (Objects.requireNonNull(App.class.getResourceAsStream("backOfCard.png"))));
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
