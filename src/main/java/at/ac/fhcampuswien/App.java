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


        //Card card = new Card(front);

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Game_UI.fxml")); //macht GUI aus FXML file


        Scene scene = new Scene(fxmlLoader.load(), 800, 620);
        stage.setTitle("Memory");
        stage.setScene(scene);
        //GridPane grid = (GridPane) scene.lookup("#Grid_Memory"); // Sucht das Element mit der Id Grid_Memory, expliciter cast

        Label label1 = (Label) scene.lookup("#label_PlayerOneScore");
        label1.setText("Player1 Score: " );

        Label label2 = (Label) scene.lookup("#label_PlayerTwoScore");
        label2.setText("Player2 Score:" );

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
